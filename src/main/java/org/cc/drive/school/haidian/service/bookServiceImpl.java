package org.cc.drive.school.haidian.service;

import org.apache.http.client.CookieStore;
import org.cc.drive.school.haidian.bean.BookCarBean;
import org.cc.drive.school.haidian.util.DateUtil;
import org.cc.drive.school.haidian.util.JsonUtil;
import org.cc.drive.school.haidian.util.PostUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by chichen.cc on 2016/2/23.
 */
@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private CarService carService;

    @Autowired
    ThreadPoolTaskExecutor taskExecutor;
    private int keep_alive_time=60;
    //定义缓冲池
    private static Buffer buffer = new Buffer();

    private static int cpus=Runtime.getRuntime().availableProcessors();

    public String bookcar(CookieStore cs ,BookCarBean bcb) {
        if(bcb==null)
            bcb=getDefaultBean();

        String json =carService.queryCars(cs , bcb);
        List<String> cars=carService.getCarList(json);


        if( taskExecutor!=null){
            taskExecutor.execute(new ProducerTask(cars,bcb));

            taskExecutor.execute(new ConsumerTask(cs,bcb.getName()));
        }
        return null;
    }

    public String bookcar(CookieStore cs  ) {
        return bookcar(cs,null);
    }

    private BookCarBean getDefaultBean(){
        BookCarBean bcb=new BookCarBean();
        bcb.setDate(DateUtil.get6days());
        bcb.setTimeInterval("15");
        bcb.setName("cc");
        return bcb;
    }

    //生产者任务类
    private static class ProducerTask implements Runnable {
        private static final Logger ll = LoggerFactory.getLogger(ProducerTask.class);
        /******
         * datestr 为时间 默认为当前运行时间+6天
         * timeline 为约车时间段 812 或者15
         * **/
        ProducerTask(List<String> carlists,BookCarBean b){
            carlist=carlists;
            bcb=b;
        }
        private List<String> carlist;
        private BookCarBean bcb;
        public void run() {
            //拼接提交的json信息
            if(carlist!=null&&carlist.size()>0){
                for (int i = 0; i < carlist.size(); i++) {
                    String msg = "{\"yyrq\":\""+bcb.getDate()+"\",\"xnsd\":\""+bcb.getTimeInterval()+"\",\"cnbh\":\""+carlist.get(i)+"\",\"imgCode\":\"\",\"KMID\":\"2\"}";
                    ll.info("the "+i+msg);
                    buffer.write(msg);
                }
                ll.info("buffer size "+carlist.size());
            }
        }
    }

    //消费者任务类
    private static class ConsumerTask implements Runnable {
        private static final Logger ll = LoggerFactory.getLogger(ConsumerTask.class);
        ConsumerTask(CookieStore ck,String threadname){
            this.cookie=ck;
            threadName=threadname;
        }
        private String threadName;
        String bookCarUrl="http://haijia.bjxueche.net/Han/ServiceBooking.asmx/BookingCar";
        private CookieStore cookie;
        public void run() {
            while (true) {
                //从缓存区读取整数
                String json=buffer.read();
                ll.info(threadName+"消费者读取 " + json);
                String res= PostUtil.postJsonData(bookCarUrl, json, cookie);
                ll.info(threadName+"返回结果"+res);
                boolean tag = JsonUtil.parseBookRes(res);
                if(tag){
                    ll.info(threadName+"约车成功:"+res);
                    //线程休眠随机时间
                    break;

                }
                try {
                    Thread.sleep( 50000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }
    //定义缓存区，用FIFO队列存取数据
    private static class Buffer {

        //缓存区容量
        private static final int CAPACITY = 100;

        //用LinkedList定义FIFO链队
        private java.util.LinkedList<String> queue = new java.util.LinkedList<String>();

        //定义互斥锁
        private static Lock lock = new ReentrantLock();

        //条件：缓存区非空
        private static Condition notEmpty = lock.newCondition();
        //条件：缓存区已满
        private static Condition notFull = lock.newCondition();

        //从缓存区读取整数
        public void write(String v) {
            lock.lock();
            try {
                while (queue.size() == CAPACITY) {
                    System.out.println("等待缓存区未满");
                    notFull.await();
                }

                //缓存区未满条件被唤醒之后，向队列添加
                queue.offer(v);
                //向缓存区非空条件发送信号
                notEmpty.signal();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        @SuppressWarnings("finally")
        public String read() {
            String value = null;
            lock.lock();
            try {
                //当缓存区为空时候：等待非空条件
                while (queue.isEmpty()) {
                    System.out.println("\t\t\t等待唤醒非空条件");
                    notEmpty.await();
                }

                //读取并删除数据
                value = queue.remove();
                //向缓存区未满条件发送信号
                notFull.signal();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            } finally {
                lock.unlock();
                return value;
            }
        }
    }
}
