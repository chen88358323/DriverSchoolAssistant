package org.cc.drive.school.haidian.util.ocr.tesseract;

import org.apache.commons.lang3.StringUtils;
import org.cc.drive.school.haidian.util.orc.tesseract.ImageRead;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * Created by chichen.cc on 2016/2/24.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:spring-config.xml"})
public class TesseractTest extends AbstractJUnit4SpringContextTests {
    Logger ll = LoggerFactory.getLogger(TesseractTest.class);
    @Test
    public void testReadGIF(){
        String gifPath="D:\\CODE\\github\\DriverSchoolAssistant\\web\\WEB-INF\\img\\1456212406925.gif";
        String res=null;
        File f=new File(gifPath);
        try {
            InputStream instream = new FileInputStream(f);
            BufferedImage bi = ImageIO.read(instream);
            instream.close();
            res = ImageRead.read(bi, 0);
            if(StringUtils.isNotBlank(res)){
                res=res.replaceAll(" ","");
            }
            ll.info(res + ":====" +  "   ");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
