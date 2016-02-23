package org.cc.drive.school.haidian.service;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;

import java.io.IOException;

/**
 * Created by chichen.cc on 2016/2/23.
 */
public interface YZMService {
    public String parseYZM() throws IOException;

    public  String parseYZM(HttpGet httpget, HttpClient httpclient);
}
