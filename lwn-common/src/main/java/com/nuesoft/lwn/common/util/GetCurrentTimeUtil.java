package com.nuesoft.lwn.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by yanyong on 2016/10/18.
 * 获取当前时间
 */

public class GetCurrentTimeUtil {

    public static String currentTime(){
        Date date= new Date();//创建一个时间对象，获取到当前的时间
        SimpleDateFormat upLast = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置时间显示格式
        String time = upLast.format(date);//将当前时间格式化为需要的类型
        return time;
    }

    public static String time(){
        Date date= new Date();//创建一个时间对象，获取到当前的时间
        SimpleDateFormat upLast = new SimpleDateFormat("yyyy-MM-dd");//设置时间显示格式
        String time = upLast.format(date);//将当前时间格式化为需要的类型
        return time;
    }
}
