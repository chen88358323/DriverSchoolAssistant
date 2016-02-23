package org.cc.drive.school.haidian.util;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by chichen.cc on 2016/2/23.
 */
public class PostUtil {
    private static final Logger ll = LoggerFactory.getLogger(PostUtil.class);

    /***
     * 提交制定的json数据
     * @param url
     * @param  json 提交数据
     * @param usercookie  对应用户cookie
     * */
    public static String postJsonData(String url,String json,String usercookie){
        String res=null;

        BasicHttpParams httpParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpParams, 10 * 1000);
        HttpConnectionParams.setSoTimeout(httpParams, 10 * 1000);
        DefaultHttpClient client = new DefaultHttpClient(httpParams);
        HttpPost post = new HttpPost(url);
        ll.info("parameters:" + json);
        try{
            StringEntity entity = new StringEntity(json);
            post.setEntity(entity);
            post.setHeader("Accept", "application/json");
            post.setHeader("Content-type", "application/json");

            long startTime = System.currentTimeMillis();
            post.setHeader("Cookie",usercookie);
            //设置编码
            HttpResponse response=client.execute(post);
            long endTime = System.currentTimeMillis();
            int statusCode = response.getStatusLine().getStatusCode();
            ll.info("statusCode:" + statusCode);
            ll.info("调用API 花费时间(单位：毫秒)：" + (endTime - startTime));
            if(statusCode != HttpStatus.SC_OK){
                ll.error("Method failed:"+response.getStatusLine());
            }
            //Read the response body
            res= EntityUtils.toString(response.getEntity());
            ll.info("====>"+res);
        }catch(IOException e){
            //发生网络异常
            ll.error("exception occurred!\n"+e);
        }finally {
            if(post!=null)
                post.releaseConnection();
            return res;
        }
    }
}
