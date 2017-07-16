package com.easyandroid.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by NightStar on 2017/7/14.
 */

public class TimeFormatUtil {
    public static final String timeFormatUtil(String dateString){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        Date date = null;
        try {
            date = sdf.parse(dateString);
            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
            String pdate = sdf2.format(date);
            return pdate;
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
    }
}
