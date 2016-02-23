package org.cc.drive.school.haidian.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

/**
 * Created by chichen.cc on 2016/2/23.
 */
public class YZMServiceImplTest extends BaseService {
    @Autowired
    private YZMService yzmService;
    @Test
    public void testgetCookie() throws IOException {
        String logon = yzmService.parseYZM();
        ll.info("login cookie:" + logon);
    }

}
