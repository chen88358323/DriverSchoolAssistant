package org.cc.drive.school.haidian.service;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.cc.drive.school.haidian.util.AspriseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by chichen.cc on 2016/2/22.
 */
@Service
public class CookiesServiceImpl implements  CookiesService{
    private static final Logger ll = LoggerFactory.getLogger(CookiesServiceImpl.class);

    public  String getLoginCookies()  throws IOException {
        String res=null;
        // 第一步：先下载验证码到本地
        String url = "http://haijia.bjxueche.net/tools/CreateCode.ashx?key=ImgCode&random=0.006666118821571199";

        HttpClient httpclient = new DefaultHttpClient();
        HttpGet httpget = new HttpGet(url);
        String f=getFileName();
        File file = new File(f);
        if (file.exists()) {
            file.delete();
        }

        HttpResponse response = httpclient.execute(httpget);
        List<Cookie> cookies = ((AbstractHttpClient)httpclient).getCookieStore().getCookies();
        //打印
        showCookies(cookies,"");
        HttpEntity entity = response.getEntity();
        InputStream in = entity.getContent();
        try {
            FileOutputStream fout = new FileOutputStream(file);
            int l = -1;
            byte[] tmp = new byte[2048];
            while ((l = in.read(tmp)) != -1) {
                fout.write(tmp);
            }
            fout.close();
        } finally {
            in.close();
        }

        httpget.releaseConnection();
//        ll.info("imgCode:" + RegeitTest.testReadGIF(destfilename));
        ll.info("imgCode:" + AspriseUtil.recognize(f));
        return res;
    }

    private String getFileName(){
        String f="";
        long name=System.currentTimeMillis();
        f=System.getProperty("java.io.tmpdir")+name+".gif";
        ll.info("name "+f);
        return f;
    }

    /***
     Cookie:
     CNZZDATA5234107=cnzz_eid%3D1288870569-1450422870-http%253A%252F%252Fhaijia.bjxueche.net%252F%26ntime%3D1453192487;
     ASP.NET_SessionId=ai3moasuhpnl51vuynudlj2d;
     ImgCode=rbtEkYdA3Pw=

     * **/
    public void showCookies(List<Cookie> cl,String name){
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
}
