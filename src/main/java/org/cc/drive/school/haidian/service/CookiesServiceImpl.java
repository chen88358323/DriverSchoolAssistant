package org.cc.drive.school.haidian.service;

import org.apache.commons.lang.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.DeflateDecompressingEntity;
import org.apache.http.client.entity.GzipDecompressingEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.cc.drive.school.haidian.util.HtmlParse;
import org.cc.drive.school.haidian.util.HttpUtil;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by chichen.cc on 2016/2/22.
 */
@Service
public class CookiesServiceImpl implements  CookiesService{
    private static final Logger ll = LoggerFactory.getLogger(CookiesServiceImpl.class);

    @Autowired
    private YZMService yzmService;

    public  CookieStore  getLoginCookies() throws IOException {
        String url="http://haijia.bjxueche.net/Login.aspx";
        HttpClient httpclient = new DefaultHttpClient();
        HttpGet httpget = new HttpGet(url);
        httpget.setHeader("Host", "haijia.bjxueche.net");
        httpget.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
        httpget.setHeader("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");
        httpget.setHeader("Accept-Encoding", "gzip, deflate");
        httpget.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:43.0) Gecko/20100101 Firefox/43.0");

        httpclient.getParams().setParameter("http.protocol.content-charset", "utf-8");
        HttpResponse response = httpclient.execute(httpget);
        CookieStore cookies = ((AbstractHttpClient)httpclient).getCookieStore();
        //打印
//        HttpUtil.showCookies(cookies.getCookies(),"get index ck");
        HttpEntity entity = response.getEntity();
        Map<String ,String> map = getEntityContent(entity);



        Header[] h=response.getAllHeaders();
        //打印
//        HttpUtil.showHeaderes(h, "get index headers");

        String imgCode=yzmService.parseYZM(httpget, httpclient);
        map.put("txtIMGCode", imgCode);

        boolean loginTag=false;
        CookieStore cs=  loginAction(httpclient, map, h);
        httpget.releaseConnection();
        loginTag=validLoginStatus(cs);
        return cs;
    }
    private boolean validLoginStatus(CookieStore logincookies){
        //打印
        HttpUtil.showCookies(logincookies.getCookies(), "get login ck");
        boolean b=HttpUtil.queryCookies(logincookies.getCookies(),"LoginOn");
        ll.info("*****  login status ==" + b);

        return b;
    }
    /****
     * 返回login页面信息
     * 注意返回二进制数据流 (gzip)
     * 需要gzip 解压缩
     * **/
    private static Map<String ,String> getEntityContent(HttpEntity en ){
        Map<String ,String> resMap=null;
        try {
            en = new GzipDecompressingEntity(en);
            String res= EntityUtils.toString(en, "UTF-8");
            ll.debug("login html :" + res);

            if(StringUtils.isNotBlank(res)){
                Document doc= HtmlParse.getDocByStr(res);
                resMap= HtmlParse. getParams(doc);
//                for (String key : resMap.keySet()) {
//                    String value = resMap.get(key);
//                    ll.info("Key = " + key + ", Value = " + value);
//
//                }
            }

            ll.info("==========================");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resMap;
    }
//    private static String destfilename = "D:\\temp\\del\\yc.gif";
//    private static byte[] tmp = new byte[2048];

    /**
     * 登录获取cookie
     *
     * **/
    public  CookieStore loginAction(HttpClient httpclient,Map<String ,String> paramsMap,Header[] headers){
        boolean loginTag=false;

        HttpPost httppost = new HttpPost("http://haijia.bjxueche.net/login.aspx");
//        httppost.setHeaders(headers);
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        for (String key : paramsMap.keySet()) {
            String value = paramsMap.get(key);
            ll.info("put Key = " + key + ", Value = " + value+" into params");
            params.add(new BasicNameValuePair(key, value));

        }
        httppost.setHeader("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");
        httppost.setHeader("Accept-Encoding", "gzip, deflate");
        httppost.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
        httppost.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:43.0) Gecko/20100101 Firefox/43.0");

        HttpResponse response = null;
        HttpEntity entity=null;
        try {
            httppost.setEntity(new UrlEncodedFormEntity(params));
            response = httpclient.execute(httppost);

            Header[] h=response.getAllHeaders();
            //打印
            HttpUtil.showHeaderes(h, "get login headers");

            entity = response.getEntity();

            if(entity.getContentEncoding()!=null){
                if("gzip".equalsIgnoreCase(entity.getContentEncoding().getValue())){
                    entity = new GzipDecompressingEntity(entity);
                } else if("deflate".equalsIgnoreCase(entity.getContentEncoding().getValue())){
                    entity = new DeflateDecompressingEntity(entity);
                }
            }
            String res=EntityUtils.toString(entity,"UTF-8" );

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        CookieStore logincookies = ((AbstractHttpClient)httpclient).getCookieStore();
        httppost.releaseConnection();
        return  logincookies;
    }
}
