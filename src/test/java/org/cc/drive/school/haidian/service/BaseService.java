package org.cc.drive.school.haidian.service;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by chichen.cc on 2016/2/22.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:spring-config.xml"})
public class BaseService extends AbstractJUnit4SpringContextTests {
    Logger ll = LoggerFactory.getLogger(BaseService.class);

}
