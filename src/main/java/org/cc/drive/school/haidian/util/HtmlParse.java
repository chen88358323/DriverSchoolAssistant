package org.cc.drive.school.haidian.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Q 验证码验证
 *
 * 参考链接:
 * http://segmentfault.com/q/1010000000686720
 * http://blog.csdn.net/problc/article/details/5794460
 * http://blog.csdn.net/problc/article/details/5800093
 * http://www.cnblogs.com/nayitian/p/3282862.html
 * Created by chichen.cc on 2015/12/18.
 */
public class HtmlParse {
    private static final Logger ll = LoggerFactory.getLogger(HtmlParse.class);
    public static void main(String asd[]){
        HtmlParse hp=new HtmlParse();
        Document doc=getDocByFile();
        hp.getParams( doc);
    }
/***
 *
 * __EVENTVALIDATION	/wEdAAZaf2taKagAg6HWTLIxdYPeY3plgk0YBAefRz3MyBlTcHY2+Mc6SrnAqio3oCKbxYZxHHBh6T/0qvM7nnT1C8JEQiUagUcDcu68gyetszRkSY7bzJhCrs4nlwf1kSE7uBAJxoT9CZZ743NHu18iOdE2UuT6TomwmxMoPXiVpaLNYg==
 __VIEWSTATE	/wEPDwUJMjkyMDI4NzYyD2QWAgIDD2QWAgINDxYCHglpbm5lcmh0bWwFAjQzZGRoVhn26l58vyR8WrMu/I1KHrM24pSaQO3KNp/XOnQ6Jw==
 __VIEWSTATEGENERATOR	C2EE9ABB
 BtnLogin	登  录
 txtIMGCode	Z7HH
 txtPassword	1020
 txtUserName	120103198510206710
 *
 * */
    public static Map<String ,String> getParams( Document doc){
        String[] arrStr=new String[]{"__EVENTVALIDATION","__VIEWSTATE","__VIEWSTATEGENERATOR"};
        Map<String,String> res =new HashMap<String,String>();
//        Document doc = getDocByFile();
        for (int i = 0; i < arrStr.length; i++) {
            res.put(arrStr[i], getVal(doc, arrStr[i]));
        }
        res.put("BtnLogin","登  录");
        res.put("txtUserName","120103198510206710");
        res.put("txtPassword","1020");
        return res;
    }

    private static  Document getDocByFile(){
        File input = new File("D:\\temp\\del\\input.html");
        Document doc=null;
        try {
             doc = Jsoup.parse(input, "UTF-8", "http://example.com/");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return doc;
    }

    public static Document getDocByStr(String html){
        Document doc = Jsoup.parse(html);
        return doc;
    }


    private static String getVal(Document doc,String ids){
        Element con=doc.getElementById(ids);
        String res=con.attr("value");
        return res;

    }
}
