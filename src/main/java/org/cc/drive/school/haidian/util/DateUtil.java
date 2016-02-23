package org.cc.drive.school.haidian.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by chichen.cc on 2016/1/14.
 * 目前不实现负责的规则，只是得到6天后的数据
 */
public class DateUtil {
    private static final Logger ll = LoggerFactory.getLogger(DateUtil.class);
    /****
     * 更具当前时间获取
     * 本周某天时间
     * 比如获取周六周日时间
     * **/
    public static String getNowWeekMonday(Date date,int zhouji) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        cal.add(Calendar.DAY_OF_MONTH, -1); //解决周日会出现 并到下一周的情况
//        cal.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
        cal.set(Calendar.DAY_OF_WEEK, zhouji);
        SimpleDateFormat formatter = new SimpleDateFormat ("yyyy-MM-dd");
        String ctime = formatter.format(cal.getTime());
        return ctime;
    }

    public static String get6days(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String mdate = "";
        Date d = new Date();
        long myTime = (d.getTime() / 1000) + 6 * 24 * 60 * 60;
        d.setTime(myTime * 1000);
        mdate = format.format(d);
        ll.info("6天后是" + mdate + " " + dayForWeek(d));
        mdate=mdate.replaceAll("-","");
        return mdate;
    }
    public static String get6days(Date d){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String mdate = "";
        long myTime = (d.getTime() / 1000) + 6 * 24 * 60 * 60;
        d.setTime(myTime * 1000);
        mdate = format.format(d);
        ll.info("6天后是"+mdate+" "+dayForWeek(d));
        mdate=mdate.replaceAll("-", "");
        return mdate;
    }
    //获取星期几 注意周日 至 周六是0-6
    public static int dayForWeek(Date tmpDate)  {
        Calendar cal = new GregorianCalendar();
        cal.set(tmpDate.getYear(), tmpDate.getMonth(), tmpDate.getDay());
        return cal.get(Calendar.DAY_OF_WEEK);
    }
    public static void main(String asd[]){
//        ll.info(getNowWeekMonday(new Date(),7));
//        ll.info(getNowWeekMonday(new Date(),1));


        ll.info(get6days());
    }
}
