package org.cc.drive.school.haidian.service;

import org.cc.drive.school.haidian.bean.BookCarBean;

/**
 * Created by chichen.cc on 2016/2/23.
 */
public interface CarService {
    //查询可预约车辆
    public String queryCars(String cookies);

    //查询可预约车辆 指定日期
    public String queryCarsByDate(String cookies,String date);

    //查询可预约车辆 指定时间 上午812 下午15
    public String queryCars(String cookies,String date,String time);

    public String queryCars(String cookies,BookCarBean bcb);

    //预约车辆
    public String bookCars(String cookies);

    public String bookCars(String cookies,BookCarBean bcb);

}
