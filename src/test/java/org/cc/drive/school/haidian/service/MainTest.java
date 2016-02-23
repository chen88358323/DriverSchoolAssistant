package org.cc.drive.school.haidian.service;

import org.apache.http.client.CookieStore;
import org.cc.drive.school.haidian.util.HttpUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

/**
 * Created by chichen.cc on 2016/2/22.
 */
public class MainTest extends BaseService {
    @Autowired
    private CarService carService;
    @Autowired
    private CookiesService cookiesServiceImpl;
    @Autowired
    private YZMService yzmService;

    @Test
    public void book() throws IOException {
        CookieStore cs=cookiesServiceImpl.getLoginCookies();
        boolean loginTag= HttpUtil.queryCookies(cs.getCookies(), "LoginOn");
        if(loginTag){

        }
        ll.info("json:");
    }

}
