package org.cc.drive.school.haidian.bean;

/**
 * Created by chichen.cc on 2016/2/23.
 */
public class BookCarBean {
    //时间 20160229
    private String Date;
    //时段 812 15
    private String timeInterval;

    private String carNum;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BookCarBean(){}

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getTimeInterval() {
        return timeInterval;
    }

    public void setTimeInterval(String timeInterval) {
        this.timeInterval = timeInterval;
    }

    public String getCarNum() {
        return carNum;
    }

    public void setCarNum(String carNum) {
        this.carNum = carNum;
    }

    public BookCarBean(String date, String timeInterval, String carNum) {
        Date = date;
        this.timeInterval = timeInterval;
        this.carNum = carNum;
    }
}
