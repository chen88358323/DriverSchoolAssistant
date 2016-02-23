package org.cc.drive.school.haidian.util;

import org.apache.http.Header;
import org.apache.http.cookie.Cookie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by chichen.cc on 2016/2/23.
 */
public class HttpUtil {
    private static final Logger ll = LoggerFactory.getLogger(HttpUtil.class);

    /***
     Cookie:
     CNZZDATA5234107=cnzz_eid%3D1288870569-1450422870-http%253A%252F%252Fhaijia.bjxueche.net%252F%26ntime%3D1453192487;
     ASP.NET_SessionId=ai3moasuhpnl51vuynudlj2d;
     ImgCode=rbtEkYdA3Pw=

     * **/
    public static void showCookies(List<Cookie> cl,String name){
        ll.info("*******************"+name+" start *******************");
        if(cl!=null&&cl.size()>0){
            for (int i = 0; i <cl.size() ; i++) {
                Cookie c=cl.get(i);
                ll.info("the " + i + " cookie kv is:" + c.getName() + "||" + c.getValue());
            }
        }else{
            ll.info("no cookies!");
        }
        ll.info("*******************"+name+" end *******************");

    }

    public static void showHeaderes(Header[] hes,String name){
        ll.info("*******************"+name+" start *******************");
        if(hes!=null&&hes.length>0){
            for (int i = 0; i <hes.length ; i++) {
                Header c=hes[i];
                ll.info("the " + i + " header kv is:" + c.getName() + "   " + c.getValue());
            }
        }else{
            ll.info("no Headeres!");
        }
        ll.info("*******************"+name+" end *******************");

    }
    public static boolean queryCookies(List<Cookie> cl,String name){
        boolean res=false;
        if(cl!=null&&cl.size()>0){
            for (int i = 0; i <cl.size() ; i++) {
                Cookie c=cl.get(i);
                if(name.equals(c.getName())){
                    ll.info("*********LOGON :"+c.getValue());
                    res=true;
                }

            }
        }else{
            ll.info("no cookies!");
        }
        return  res;
    }
}
