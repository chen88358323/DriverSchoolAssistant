package org.cc.drive.school.haidian.util;

import com.asprise.ocr.Ocr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * Created by chichen.cc on 2016/1/21.
 */
public class AspriseUtil {
    private static final Logger ll = LoggerFactory.getLogger(AspriseUtil.class);

    public static void main(String asdf[]){
       recognize("");
    }

    public static String recognize(String file){
        String res=null;
        Ocr.setUp(); // one time setup
        Ocr ocr = new Ocr(); // create a new OCR engine
        ocr.startEngine("eng", Ocr.SPEED_FASTEST); // English
        res = ocr.recognize(new File[] {new File(file)},
                Ocr.RECOGNIZE_TYPE_ALL, Ocr.OUTPUT_FORMAT_PLAINTEXT); // PLAINTEXT | XML | PDF | RTF
        ll.info("img recognize Result: " + res);
        ocr.stopEngine();
        return res;
    }
}
