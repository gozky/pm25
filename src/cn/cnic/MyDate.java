package cn.cnic;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by hadoop on 16-5-29.
 *
 * 工具类，用于处理有关时间
 */
public class MyDate {

    public static Date strToDate(String ds){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z");
        Date date = null;
        try {
            date = sdf.parse(ds);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //System.out.println(date);
        return date;
    }

    public static String dateToStr(Date date){
        String sdate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").format(date);
        return sdate;
    }
    public static int dayofWeek(Date date){
        //Date date = strToDate(ds);
        String sdate = new SimpleDateFormat("u").format(date);
        return Integer.parseInt(sdate);
    }

    public static int hourofDay(Date date){
        String sdate = new SimpleDateFormat("k").format(date);
        return Integer.parseInt(sdate);
    }

    public static int dayofMonth(Date date){
        String sdate = new SimpleDateFormat("d").format(date);
        return Integer.parseInt(sdate);
    }

    public static int dayofYear(Date date){
        String sdate = new SimpleDateFormat("D").format(date);
        return Integer.parseInt(sdate);
    }

    public static int weekofYear(Date date){
        String sdate = new SimpleDateFormat("w").format(date);
        return Integer.parseInt(sdate);
    }

    public static int weekofMonth(Date date){
        String sdate = new SimpleDateFormat("W").format(date);
        return Integer.parseInt(sdate);
    }

}
