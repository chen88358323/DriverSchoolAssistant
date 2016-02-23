package org.cc.drive.school.haidian.service;

import org.apache.http.client.CookieStore;
import org.cc.drive.school.haidian.bean.BookCarBean;

import java.util.List;

/**
 * Created by chichen.cc on 2016/2/23.
 */
public interface CarService {
    //查询可预约车辆
    public String queryCars(CookieStore cookies);

    //查询可预约车辆 指定日期
    public String queryCarsByDate(CookieStore cookies,String date);

    //查询可预约车辆 指定时间 上午812 下午15
    public String queryCars(CookieStore cookies,String date,String time);

    public String queryCars(CookieStore cookies,BookCarBean bcb);

    public List<String> getCarList(String json);
    //预约车辆
    public String bookCars(CookieStore cookies);

    public String bookCars(CookieStore cookies,BookCarBean bcb);

}
