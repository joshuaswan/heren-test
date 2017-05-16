package com.heren.his.commons.util;

import com.google.common.collect.Lists;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateUtils {
    public static Date addMonth(Date date, int n)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, n);
        return cal.getTime();
    }
    public static Date addDate(Date date, int n)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, n);
        return cal.getTime();
    }

    public static List<Date> getStartAndEnd(String year, String month)
    {
        Date start = null;
        Date end = null;
        if(month != null && month.length() > 0){
            if(month.length() == 1){
                month = "0" + month;
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
            try {
                start = sdf.parse(year + "-" + month);
                end = DateUtils.addMonth(start, 1);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
            try {
                start = sdf.parse(year);
                end = DateUtils.addMonth(start, 12);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return Lists.newArrayList(start, end);
    }
    public static List<Date> getStartAndEndDate(String year, String month, String date)
    {
        Date start = null;
        Date end = null;
        if(date != null && date.length() > 0){
            if(date.length() == 1){
                date = "0" + date;
            }
            if(month.length() == 1){
                month = "0" + month;
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            try {
                start = sdf.parse(year + "-" + month+"-"+date);
                end = addDate(start,1);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else {
            if(month.length() == 1){
                month = "0" + month;
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
            try {
                start = sdf.parse(year + "-" + month);
                end = DateUtils.addMonth(start, 1);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return Lists.newArrayList(start, end);
    }

    /**
     * 判断传入时间字符串+limitMonth 与当前日期比较是否超过当前日期，超过返回false，没超过返回true；
     * @param timeStr
     * @param limitMonth
     * @return
     */
    public static boolean isOverPeriodOfTime(String timeStr,int limitMonth){
        boolean isOk = true;
        Date nowDate = new Date();
        Date templeDate = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
        try {
            templeDate = sdf.parse(timeStr);
            templeDate = addMonth(templeDate,limitMonth);
            if(nowDate.after(templeDate)){
                isOk = true;
            }else {
                isOk = false;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return isOk;
    }
}
