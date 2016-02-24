package org.cc.drive.school.haidian.service;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.cc.drive.school.haidian.util.orc.asprise.AspriseUtil;
import org.cc.drive.school.haidian.util.FileUtil;
import org.cc.drive.school.haidian.util.HttpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.List;

/**
 * Created by chichen.cc on 2016/2/23.
 */
@Service
public class YZMServiceImpl implements YZMService {
    private static final Logger ll = LoggerFactory.getLogger(YZMServiceImpl.class);


    public  String parseYZM()  throws IOException {
        String res=null;
        // 第一步：先下载验证码到本地
        String url = "http://haijia.bjxueche.net/tools/CreateCode.ashx?key=ImgCode&random=0.006666118821571199";

        HttpClient httpclient = new DefaultHttpClient();
        HttpGet httpget = new HttpGet(url);
        String f= FileUtil.getFileName();
        ll.info("f is :"+f);
        File file = new File(f);
        if (file.exists()) {
            file.delete();
        }

        HttpResponse response = httpclient.execute(httpget);
        List<Cookie> cookies = ((AbstractHttpClient)httpclient).getCookieStore().getCookies();
        //打印
        HttpUtil.showCookies(cookies,"");
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
        res= AspriseUtil.recognize(f);
        res=res.replaceAll(" ", "");
        ll.info("imgCode:" + res);
        return res;
    }

    public  String parseYZM(HttpGet httpget, HttpClient httpclient){
        ll.info("***********************get img start*************************");
        String url = "http://haijia.bjxueche.net/tools/CreateCode.ashx?key=ImgCode&random=0.006666118821571199";
        httpget.setHeader("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");
        httpget.setHeader("Accept-Encoding", "gzip, deflate");
        httpget.setHeader("Accept", "image/png,image/*;q=0.8,*/*;q=0.5");
        httpget.setHeader("Referer", "http://haijia.bjxueche.net/");

        httpget.setURI(URI.create(url));
        String f= FileUtil.getFileName();
        ll.info("f is :" + f);
        File file = new File(f);
        if (file.exists()) {
            file.delete();
        }
        HttpResponse response = null;
        try {
            response = httpclient.execute(httpget);
            HttpEntity entity = response.getEntity();
            Header[] h=response.getAllHeaders();
//            Header[] h=httpget.getAllHeaders();
            //打印
//            HttpUtil.showHeaderes(h, "get img headers");

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
        } catch (IOException e) {
            e.printStackTrace();
        }

        String imgCode=AspriseUtil.recognize(f);
        imgCode=imgCode.replaceAll(" ","");
        ll.info("yzm:" + imgCode);

        List<Cookie> cookies = ((AbstractHttpClient)httpclient).getCookieStore().getCookies();
        //打印
        HttpUtil.showCookies(cookies,"get img cookie");

        ll.info("***********************get img end*************************");

        return imgCode;
    }
}
