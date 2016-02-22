package org.cc.drive.school.haidian.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

/**
 * Created by chichen.cc on 2016/2/22.
 */
public class CookiesServiceImplTest extends BaseService {
    @Autowired
    private CookiesService cookiesServiceImpl;
    @Test
    public void testgetCookie() throws IOException {
        cookiesServiceImpl.getLoginCookies();
    }

}
