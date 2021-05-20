package com.example.mysqloracle.util;

import org.springframework.util.Assert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * 时间转换工具
 *
 * @Author: dingqi
 * @Date: 2018/7/26
 */

public class DateUtil {


    /**
     * 时间戳转换显示（yyyy-MM-dd HH:mm:ss）
     *
     * @param timestamp
     * @return
     */
    public static String toDate(String timestamp) {
        StringBuffer sbf = new StringBuffer();
        sbf.append(timestamp);
        if (timestamp.length() == 10) {
            sbf.append("000");
        }
        long time = Long.parseLong(sbf.toString());  //int放不下的，用long
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(time);
    }

    /**
     * 时间戳转换显示（yyyy-MM-dd HH:mm:ss）
     *
     * @param timestamp
     * @return
     */
    public static String toDate2(String timestamp) {
        StringBuffer sbf = new StringBuffer();
        sbf.append(timestamp);
        if (timestamp.length() == 10) {
            sbf.append("000");
        }
        long time = Long.parseLong(sbf.toString());  //int放不下的，用long
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒");

        return sdf.format(time);
    }

    /**
     * 时间戳转换显示（yyyy-MM-dd）
     *
     * @param timestamp
     * @return
     */
    public static String toDate1(String timestamp) {
        StringBuffer sbf = new StringBuffer();
        sbf.append(timestamp);
        if (timestamp.length() == 10) {
            sbf.append("000");
        }
        long time = Long.parseLong(sbf.toString());  //int放不下的，用long
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        return sdf.format(time);
    }

    /**
     * 时间显示转10位时间戳（yyyy-MM-dd HH:mm:ss）
     *
     * @return
     */
    public static String toTimestamp10(String date) throws ParseException {
        return String.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date).getTime()).substring(0, 10);
    }

    public static String toTimestamp11(String date) throws ParseException {
        return String.valueOf(new SimpleDateFormat("yyyy-MM-dd").parse(date).getTime()).substring(0, 10);
    }


    /**
     * 时间转换string（yyyy-MM-dd）
     *
     * @return
     */
    public static String DatetoTime(Date date) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").format(date);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * str转换为LocalDate
     * @param str
     * @return
     */
    public static LocalDate strToLocalDate(String str){
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        if(str.length()==10){
            str=str+" 00:00:00";
        }
        LocalDate date2 = LocalDate.parse(str, fmt);
        return date2;
    }


    /**
     * 将Long类型的时间戳转换成String 类型的时间格式，时间格式为：yyyy-MM-dd HH:mm:ss
     */
    public static LocalDate convertTimeToLocalDate(Long time){
        Date d = new Date(time*1000);
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str = sf.format(d);
        LocalDate localDate = strToLocalDate(str);
        return localDate;
    }


    /**
     * 将Long类型的时间戳转换成String 类型的时间格式，时间格式为：yyyy-MM-dd HH:mm:ss
     */
    public static LocalDateTime convertTimeToLocalDateTime(Long time){
        Date d = new Date(time*1000);
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str = sf.format(d);
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.parse(str,df);
        return localDateTime;
    }

    /**
     * 根据指定的时间，增加天数
     * @param date
     * @param num
     * @return
     */
    public static LocalDate Add(LocalDate date,Integer num){
        LocalDate localDate = date.plusDays(num);
        return localDate;
    }



    /**
     * 比较第一个日期是否小于第二个日期
     * @param firstDate 第一个日期
     * @param secondDate 第二个日期
     * @return true-小于;false-大于
     */
    public static boolean localDateIsBefore(LocalDate firstDate, LocalDate secondDate) {
        return firstDate.isBefore(secondDate);
    }


    /**
     * 比较第一个日期是否大于第二个日期
     * @param firstDate 第一个日期
     * @param secondDate 第二个日期
     * @return true-大于;false-不大于
     */
    public static boolean localDateIsAfter(LocalDate firstDate, LocalDate secondDate) {
        return firstDate.isAfter(secondDate);
    }

    /**
     * 比较两个日期是否相等
     * @param firstDate 第一个日期
     * @param secondDate 第二个日期
     * @return true-相等;false-不相等
     */
    public static boolean localDateIsEqual(LocalDate firstDate, LocalDate secondDate) {
        return firstDate.isEqual(secondDate);
    }

    /**
     * Date -> LocalDateTime
     * @param date
     * @return
     */
    public static LocalDateTime dateToLocalDateTime(Date date) {
        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        return LocalDateTime.ofInstant(instant, zone);
    }


    /**
     * Date -> LocalDate
     * @param date
     * @return
     */
    public static LocalDate dateToLocalDate(Date date) {
        LocalDateTime localDateTime = dateToLocalDateTime(date);
        return localDateTime.toLocalDate();
    }

    /**
     * Date -> LocalTime
     * @param date
     * @return
     */
    public static LocalTime dateToLocalTime(Date date) {
        LocalDateTime localDateTime = dateToLocalDateTime(date);
        return localDateTime.toLocalTime();
    }

    /**
     * LocalDateTime -> Date
     * @param localDateTime
     * @return
     */
    public static Date localDateTimeToDate(LocalDateTime localDateTime) {
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDateTime.atZone(zone).toInstant();
        return Date.from(instant);
    }

    /**
     * LocalDate -> Date
     * @param localDate
     * @return
     */
    public static Date localDateToDate(LocalDate localDate) {
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDate.atStartOfDay().atZone(zone).toInstant();
        return Date.from(instant);
    }

    /**
     * LocalTime -> Date
     * @param localTime
     * @return
     */
    public static Date localTimeToDate(LocalTime localTime) {
        LocalDate localDate = LocalDate.now();
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = LocalDateTime.of(localDate, localTime).atZone(zone).toInstant();
        return Date.from(instant);
    }

    /**
     * localDate ->LocalDateTime
     * @param localDate
     * @return
     */
    public static LocalDateTime localDateToLocalDateTime(LocalDate localDate) {
        Date date = localDateToDate(localDate);
        LocalDateTime localDateTime = dateToLocalDateTime(date);
        return localDateTime;
    }

    public static Integer strToInteger(String str){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        int date = 0;
        try {
            Date dateStart = format.parse(str);
            date = (int) (dateStart.getTime() / 1000);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 转换字符串成date
     *
     * @param date
     * @return
     */
    public static Date parseDate(String date) {
        if (date == null) return null;
        if (date.matches("^\\d+$")) {
            try {
                return new Date(Long.valueOf(date));
            } catch (Throwable e) {
            }
        }
        String[] pattern = new String[]{
                "yyyy-MM-dd HH:mm:ss",
                "yyyy/MM/dd HH:mm:ss",
                "yyyyMMddHHmmss",
                "yyyy-MM-dd",
                "yyyy/MM/dd",
                "yyyyMMdd"
        };
        Date cDate = null;
        for (int i = 0; i < pattern.length; i++) {
            cDate = parse(date, pattern[i]);
            if (cDate != null) {
                return cDate;
            }
        }
        return null;
    }

    /**
     * 使用用户格式提取字符串日期
     *
     * @param strDate 日期字符串
     * @param pattern 日期格式
     * @return {@link Date}
     */
    public static Date parse(String strDate, String pattern) {
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        try {
            return df.parse(strDate);
        } catch (Exception e) {
            //e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        String startDate = "2020-09-03";
        String endDate = "2021-05-12";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        int startDay = 0;
        int endDay = 0;
        try {
            Date dateStart = format.parse(startDate);
            Date datEnd = format.parse(endDate);

            startDay = (int) (dateStart.getTime() / 1000);
            endDay = (int) (datEnd.getTime() / 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.err.println(startDay);
        System.err.println(endDay);

    }


}
