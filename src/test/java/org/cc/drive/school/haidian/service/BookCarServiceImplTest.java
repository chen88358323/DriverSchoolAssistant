package org.cc.drive.school.haidian.service;

import org.apache.http.client.CookieStore;
import org.cc.drive.school.haidian.util.HttpUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

/**
 * Created by chichen.cc on 2016/2/22.
 */
public class BookCarServiceImplTest extends BaseService {
    @Autowired
    private BookServiceImpl bookServiceImpl;
    @Autowired
    private CookiesService cookiesServiceImpl;

    @Test
    public void book() throws IOException {
        boolean loginTag =false;
        CookieStore cs=cookiesServiceImpl.getLoginCookies();
        int count=0;
        do{//logon
            count++;
            loginTag= HttpUtil.queryCookies(cs.getCookies(), "LoginOn");

            if(loginTag) {
                ll.info("the "+count+" is success");
                bookServiceImpl.bookcar(cs);
            }
            ll.info("the "+count+" is fail!!!");

            try {
                Thread.sleep(900);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }while(!loginTag);
        ll.info("json:");
    }

}
