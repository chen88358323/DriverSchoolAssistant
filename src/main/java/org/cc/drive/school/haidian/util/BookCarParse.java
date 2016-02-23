package org.cc.drive.school.haidian.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Q 验证码验证
 *
 * 参考链接:
 * http://haijia.bjxueche.net/ych2.aspx
 *
 * http://haijia.bjxueche.net/Han/ServiceBooking.asmx/GetCars
 * {"yyrq":"20160122","yysd":"812","xllxID":"2","pageSize":35,"pageNum":1}
 *
 *
 * book
 *http://haijia.bjxueche.net/Han/ServiceBooking.asmx/BookingCar
 * {"yyrq":"20160122","xnsd":"15","cnbh":"05206","imgCode":"","KMID":"2"}
 *
 * Created by chichen.cc on 2015/12/18.
 */
public class BookCarParse {
    private static String detailcars="http://haijia.bjxueche.net/Han/ServiceBooking.asmx/GetCars";
    private static String bookCarsUrl="http://haijia.bjxueche.net/Han/ServiceBooking.asmx/BookingCar";
    private static final Logger ll = LoggerFactory.getLogger(BookCarParse.class);
    public static void main(String asd[]){
        BookCarParse hp=new BookCarParse();
        hp.parseHtml();

    }
    public  void parseHtml( ){
        Document doc = getDocByFile();
        parseCarsByDay(doc);
    }

    private Document getDocByFile(){
        File input = new File("C:\\Users\\chichen.cc\\Desktop\\yueche\\ych2.aspx.htm");
        Document doc=null;
        try {
             doc = Jsoup.parse(input, "UTF-8", "http://haijia.bjxueche.net/ych2.aspx");
        } catch (IOException e) {
            e.printStackTrace();
        }
//        ll.info("doc is "+doc.toString());
        return doc;
    }
    private Document getDocByStr(String html){
        Document doc = Jsoup.parse(html);
        return doc;
    }

    public String parseCarsByDay(Document doc){
        String res=null;
        int dayNum=DateUtil.dayForWeek(new Date());
        dayNum=6;//4 test
        if(dayNum==0||dayNum==6){//当天六日 处理下周
            String next=DateUtil.get6days();
            next="2016-01-22";//4 test
            next=next.replace("-","");
            getCarNums(doc,next);
        }else{

        }

        return res;

    }
    private List<String> getCarNums(Document doc,String day){
        ll.info("=================start parse ====================");
        Element table = doc.select("table").get(0); //select the first table.
        Elements rows = table.select("td");
        List<String> resl=new LinkedList<String>();
        for (int i = 1; i < rows.size(); i++) { //first row is the col names so skip it.
            Element row = rows.get(i);
            String yyrq=row.attr("yyrq");
            if(day.equals(yyrq)){
                if("15".equals(row.attr("yysd"))||"812".equals(row.attr("yysd"))){
                    String res=row.text().trim();
                    if(!"无".equals(res)){
                        resl.add(res);
                        ll.info("the "+ i+ " is "+row.toString()+" val is "+row.text().trim());
                    }
                }
            }
        }
        return resl;
    }
    private String getVal(Document doc,String ids){
        Element con=doc.getElementById(ids);
        String res=con.attr("value");
        return res;

    }
}
