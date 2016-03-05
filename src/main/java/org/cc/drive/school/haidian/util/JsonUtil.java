package org.cc.drive.school.haidian.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Created by chichen.cc on 2016/1/15.
 */
public class JsonUtil {
    private static final Logger ll = LoggerFactory.getLogger(JsonUtil.class);

    public static void main(String asd[]){
        parseCarsList("");
//        parseBookRes("");
    }

    /**
     * 预约返回结果
     * 成功显示
     *
     "{\"d\":\"[\\r\\n  {\\r\\n    \\\"Result\\\": true,\\r\\n    \\\"OutMSG\\\": \\\"\\\"\\r\\n  }\\r\\n]\"}" ;
     * **/
    public static boolean parseBookRes(String json){
        json=json.replace("\\r\\n","").replace(" ","");
        ll.info(json);
        boolean res=getResult(json);
        return res;
    }
    private static  boolean getResult(String msg){
        boolean res=false;
        if(StringUtils.isNotBlank(msg)){
            Map resmap = (Map<String, Object>) JSON.parseObject(msg);
            Object obj=resmap.get("d");

            if(obj!=null){
                String j=obj.toString().replace("[","").replace("]","");
                ll.info(obj.toString());
                resmap = (Map<String, Object>) JSON.parseObject(j);
                obj=resmap.get("Result");
                if(obj!=null) {
                    String str=obj.toString().trim();
                    if("true".equals(str)){
                        res=true;
                    }
                    ll.info("结果是:"+str);
                }
            }
        }
        return res;
    }
    /***
        json="{\"d\":\"[\\r\\n  {\\r\\n    \\\"YYRQ\\\": \\\"20160122\\\",\\r\\n    \\\"XNSD\\\": \\\"812\\\",\\r\\n    \\\"CNBH\\\": \\\"05106\\\"\\r\\n  },\\r\\n  {\\r\\n    \\\"YYRQ\\\": \\\"20160122\\\",\\r\\n    \\\"XNSD\\\": \\\"812\\\",\\r\\n    \\\"CNBH\\\": \\\"05107\\\"\\r\\n  },\\r\\n  {\\r\\n    \\\"YYRQ\\\": \\\"20160122\\\",\\r\\n    \\\"XNSD\\\": \\\"812\\\",\\r\\n    \\\"CNBH\\\": \\\"05145\\\"\\r\\n  },\\r\\n  {\\r\\n    \\\"YYRQ\\\": \\\"20160122\\\",\\r\\n    \\\"XNSD\\\": \\\"812\\\",\\r\\n    \\\"CNBH\\\": \\\"05147\\\"\\r\\n  },\\r\\n  {\\r\\n    \\\"YYRQ\\\": \\\"20160122\\\",\\r\\n    \\\"XNSD\\\": \\\"812\\\",\\r\\n    \\\"CNBH\\\": \\\"05066\\\"\\r\\n  },\\r\\n  {\\r\\n    \\\"YYRQ\\\": \\\"20160122\\\",\\r\\n    \\\"XNSD\\\": \\\"812\\\",\\r\\n    \\\"CNBH\\\": \\\"05071\\\"\\r\\n  },\\r\\n  {\\r\\n    \\\"YYRQ\\\": \\\"20160122\\\",\\r\\n    \\\"XNSD\\\": \\\"812\\\",\\r\\n    \\\"CNBH\\\": \\\"05171\\\"\\r\\n  }\\r\\n]_1\"}";
     * **/
    public static Set<String> parseCarsSet(String json){
        String res=null;

        json=json.replace("\\r\\n","");
//        json=json.replace("\\","");
//        json=json.replace(" ","");
        ll.info(json);
        Set<String> rset= getCarSet(json);
        return rset;
    }

    public static List<String> parseCarsList(String json){
        String res=null;

        json=json.replace("\\r\\n","");
//        json=json.replace("\\","");
//        json=json.replace(" ","");
        ll.info(json);
        List<String> rset= getCarList(json);
        return rset;
    }
    private static  Set<String> getCarSet(String msg){
        Set<String> list=new HashSet<String>();
        if(StringUtils.isNotBlank(msg)){
            Map resmap = (Map<String, Object>) JSON.parseObject(msg);
            Object obj=resmap.get("d");

            if(obj!=null){
                String j=obj.toString().replace("_1","");
                ll.info(obj.toString());
                List<Cars> dicArray=new ArrayList<Cars>(JSONArray.parseArray(j, Cars.class));
                if(dicArray!=null &&dicArray.size()>0){
                    for (int i = 0; i < dicArray.size(); i++) {
                        list.add(dicArray.get(i).getCNBH());
                        ll.info("the num :" + i + " CNBH:" + dicArray.get(i).getCNBH());
                    }
                }

            }
            ll.info("共"+list.size()+"个");
//            ll.info(obj.toString());
        }

        return list;
    }
    private static  List<String> getCarList(String msg){
        List<String> list=new ArrayList<String>();
        if(StringUtils.isNotBlank(msg)){
            Map resmap = (Map<String, Object>) JSON.parseObject(msg);
            Object obj=resmap.get("d");

            if(obj!=null){
                String j=obj.toString();
                j=j.substring(0,j.length()-2);
//                String j=obj.toString().replace("_1","");
                ll.info(obj.toString());
                List<Cars> dicArray=new ArrayList<Cars>(JSONArray.parseArray(j, Cars.class));
                if(dicArray!=null &&dicArray.size()>0){
                    for (int i = 0; i < dicArray.size(); i++) {
                        list.add(dicArray.get(i).getCNBH());
                        ll.info("the num :" + i + " CNBH:" + dicArray.get(i).getCNBH());
                    }
                }

            }
            ll.info("共"+list.size()+"个");
//            ll.info(obj.toString());
        }

        return list;
    }
}

class Cars{
    private String YYRQ;
    private String XNSD;
    private String CNBH;
    public Cars(){}
    public Cars(String YYRQ, String XNSD, String CNBH) {
        this.YYRQ = YYRQ;
        this.XNSD = XNSD;
        this.CNBH = CNBH;
    }

    public String getYYRQ() {
        return YYRQ;
    }

    public void setYYRQ(String YYRQ) {
        this.YYRQ = YYRQ;
    }

    public String getXNSD() {
        return XNSD;
    }

    public void setXNSD(String XNSD) {
        this.XNSD = XNSD;
    }

    public String getCNBH() {
        return CNBH;
    }

    public void setCNBH(String CNBH) {
        this.CNBH = CNBH;
    }
}