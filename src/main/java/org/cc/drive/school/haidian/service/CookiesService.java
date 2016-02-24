package org.cc.drive.school.haidian.service;

import org.apache.http.client.CookieStore;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Created by chichen.cc on 2016/2/22.
 */
@Service
public interface CookiesService {

    public CookieStore getLoginCookies()  throws IOException ;
}
