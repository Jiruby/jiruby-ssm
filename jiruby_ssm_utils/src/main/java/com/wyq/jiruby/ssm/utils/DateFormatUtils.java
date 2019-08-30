package com.wyq.jiruby.ssm.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatUtils {

    public static Date string2Date(String str) throws Exception {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = sdf.parse(str);
        return date;
    }

    public static String date2Str(Date date){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String str = sdf.format(date);
        return str;
    }
}
