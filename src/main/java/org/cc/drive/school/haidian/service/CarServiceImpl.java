package org.cc.drive.school.haidian.service;

import org.apache.http.client.CookieStore;
import org.cc.drive.school.haidian.bean.BookCarBean;
import org.cc.drive.school.haidian.util.DateUtil;
import org.cc.drive.school.haidian.util.JsonUtil;
import org.cc.drive.school.haidian.util.PostUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * Created by chichen.cc on 2016/2/23.
 */
@Service
public class CarServiceImpl implements  CarService {
    private static final Logger ll = LoggerFactory.getLogger(CarServiceImpl.class);

    public String queryCars(CookieStore cookies) {
        String json="{\"yyrq\":\""+DateUtil.get6days()+"\",\"yysd\":\"15\",\"xllxID\":\"2\",\"pageSize\":70,\"pageNum\":1}";
        String getCarsUrl="http://haijia.bjxueche.net/Han/ServiceBooking.asmx/GetCars";
        return PostUtil.postJsonData(getCarsUrl,json,cookies);
    }

    public String queryCarsByDate(CookieStore cookies, String date) {
        String json="{\"yyrq\":\""+date+"\",\"yysd\":\""+"15"+"\",\"xllxID\":\"2\",\"pageSize\":70,\"pageNum\":1}";
        String getCarsUrl="http://haijia.bjxueche.net/Han/ServiceBooking.asmx/GetCars";
        return PostUtil.postJsonData(getCarsUrl,json,cookies);

    }
    /****
     * date  20160229
     * time  812 15
     * **/
    public String queryCars(CookieStore cookies, String date, String time) {
        String json="{\"yyrq\":\""+date+"\",\"yysd\":\""+time+"\",\"xllxID\":\"2\",\"pageSize\":70,\"pageNum\":1}";
        String getCarsUrl="http://haijia.bjxueche.net/Han/ServiceBooking.asmx/GetCars";
        return PostUtil.postJsonData(getCarsUrl,json,cookies);

    }

    public List<String> getCarList(String json){
        return JsonUtil.parseCarsList(json);
    }
    public String queryCars(CookieStore cookies, BookCarBean bcb) {
        String json="{\"yyrq\":\""+bcb.getDate() +"\",\"yysd\":\""+bcb.getTimeInterval()+"\",\"xllxID\":\"2\",\"pageSize\":70,\"pageNum\":1}";
        String getCarsUrl="http://haijia.bjxueche.net/Han/ServiceBooking.asmx/GetCars";
        return PostUtil.postJsonData(getCarsUrl,json,cookies);
    }

    //查询可预约车辆
    public String queryCars(CookieStore cookies,String date){ String res=null;
        String json="{\"yyrq\":\""+date+"\",\"yysd\":\"812\",\"xllxID\":\"2\",\"pageSize\":70,\"pageNum\":1}";
        String getCarsUrl="http://haijia.bjxueche.net/Han/ServiceBooking.asmx/GetCars";
        return PostUtil.postJsonData(getCarsUrl,json,cookies);
    }

    public String bookCars(CookieStore cookies) {
        String res=null;
        String json="{\"yyrq\":\""+DateUtil.get6days()+"\",\"xnsd\":\"15\",\"cnbh\":\"05206\",\"imgCode\":\"\",\"KMID\":\"2\"}";
        String bookCarsUrl="http://haijia.bjxueche.net/Han/ServiceBooking.asmx/BookingCar";
        return PostUtil.postJsonData(bookCarsUrl,json,cookies);

    }

    public String bookCars(CookieStore cookies, BookCarBean bcb) {
        String res=null;
        String json="{\"yyrq\":\""+bcb.getDate()+"\",\"xnsd\":\""+bcb.getTimeInterval()+"\",\"cnbh\":\""+bcb.getCarNum()+"\",\"imgCode\":\"\",\"KMID\":\"2\"}";
        String bookCarsUrl="http://haijia.bjxueche.net/Han/ServiceBooking.asmx/BookingCar";
        return PostUtil.postJsonData(bookCarsUrl,json,cookies);

    }
}
