package org.cc.drive.school.haidian.service;

import org.apache.http.client.CookieStore;
import org.cc.drive.school.haidian.bean.BookCarBean;

/**
 * Created by chichen.cc on 2016/2/23.
 */
public interface BookService {
    public String bookcar(CookieStore cs);
    public String bookcar(CookieStore cs ,BookCarBean bcb);
}
