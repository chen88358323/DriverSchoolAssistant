package org.cc.drive.school.haidian.util;

import java.util.Properties;

/**
 * Created by chichen.cc on 2016/2/23.
 */
public class FileUtil {
    public static String getFileName(){
        String f="";
        long name=System.currentTimeMillis();
        f=System.getProperty("java.io.tmpdir")+name+".gif";
        return f;
    }


}
