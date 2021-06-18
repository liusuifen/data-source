package com.example.mysqloracle.util;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.io.file.*;
import cn.hutool.core.io.resource.ResourceUtil;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * 时间转换工具
 *
 * @Author: dingqi
 * @Date: 2018/7/26
 */

public class DatesUtil {


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
     *
     * @param str
     * @return
     */
    public static LocalDate strToLocalDate(String str) {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        if (str.length() == 10) {
            str = str + " 00:00:00";
        }
        LocalDate date2 = LocalDate.parse(str, fmt);
        return date2;
    }


    /**
     * 将Long类型的时间戳转换成String 类型的时间格式，时间格式为：yyyy-MM-dd HH:mm:ss
     */
    public static LocalDate convertTimeToLocalDate(Long time) {
        Date d = new Date(time * 1000);
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str = sf.format(d);
        LocalDate localDate = strToLocalDate(str);
        return localDate;
    }


    /**
     * 将Long类型的时间戳转换成String 类型的时间格式，时间格式为：yyyy-MM-dd HH:mm:ss
     */
    public static LocalDateTime convertTimeToLocalDateTime(Long time) {
        Date d = new Date(time * 1000);
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str = sf.format(d);
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.parse(str, df);
        return localDateTime;
    }

    /**
     * 根据指定的时间，增加天数
     *
     * @param date
     * @param num
     * @return
     */
    public static LocalDate Add(LocalDate date, Integer num) {
        LocalDate localDate = date.plusDays(num);
        return localDate;
    }


    /**
     * 比较第一个日期是否小于第二个日期
     *
     * @param firstDate  第一个日期
     * @param secondDate 第二个日期
     * @return true-小于;false-大于
     */
    public static boolean localDateIsBefore(LocalDate firstDate, LocalDate secondDate) {
        return firstDate.isBefore(secondDate);
    }


    /**
     * 比较第一个日期是否大于第二个日期
     *
     * @param firstDate  第一个日期
     * @param secondDate 第二个日期
     * @return true-大于;false-不大于
     */
    public static boolean localDateIsAfter(LocalDate firstDate, LocalDate secondDate) {
        return firstDate.isAfter(secondDate);
    }

    /**
     * 比较两个日期是否相等
     *
     * @param firstDate  第一个日期
     * @param secondDate 第二个日期
     * @return true-相等;false-不相等
     */
    public static boolean localDateIsEqual(LocalDate firstDate, LocalDate secondDate) {
        return firstDate.isEqual(secondDate);
    }

    /**
     * Date -> LocalDateTime
     *
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
     *
     * @param date
     * @return
     */
    public static LocalDate dateToLocalDate(Date date) {
        LocalDateTime localDateTime = dateToLocalDateTime(date);
        return localDateTime.toLocalDate();
    }

    /**
     * Date -> LocalTime
     *
     * @param date
     * @return
     */
    public static LocalTime dateToLocalTime(Date date) {
        LocalDateTime localDateTime = dateToLocalDateTime(date);
        return localDateTime.toLocalTime();
    }

    /**
     * LocalDateTime -> Date
     *
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
     *
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
     *
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
     *
     * @param localDate
     * @return
     */
    public static LocalDateTime localDateToLocalDateTime(LocalDate localDate) {
        Date date = localDateToDate(localDate);
        LocalDateTime localDateTime = dateToLocalDateTime(date);
        return localDateTime;
    }

    public static Integer strToInteger(String str) {
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
//        FileReader fileReader = new FileReader("application.properties");
//        String result = fileReader.readString();
//        System.out.println(result);
//        FileWriter writer = new FileWriter("C:\\Users\\bl007\\Desktop\\test.sql");
//        writer.write("test");
//        FileAppender appender = new FileAppender(new File("C:\\Users\\bl007\\Desktop\\test.sql"), 16, true);
//        appender.append("123");
//        appender.append("abc");
//        appender.append("xyz");
//
//        appender.flush();
//        appender.toString();
//        Tailer tailer = new Tailer(FileUtil.file("C:\\\\Users\\\\bl007\\\\Desktop\\\\test.sql"), Tailer.CONSOLE_HANDLER, 2);
//        tailer.start();
//        File file = FileUtil.file("C:\\\\\\\\Users\\\\\\\\bl007\\\\\\\\Desktop\\\\\\\\test.sql");
//        String name = FileNameUtil.getName(file);
//        System.out.println(name);
//        String str = ResourceUtil.readUtf8Str("C:\\\\Users\\\\bl007\\\\Desktop\\\\test.sql");
//
//        Set<String> imgStr = HtmlUtil.getImgStr(str);
//        System.out.println(str);

//        System.out.println("原始字符串为:"+str);
//        String newStr = HtmlUtil.replaceHtmlTag(str, "img", "src", "src=\"https://pro-unions.oss-cn-shenzhen.aliyuncs.com", "\"");
//        System.out.println("       替换后为:"+newStr);

//        String oldPicture = "/Uploads/Warranty/1605286379000000/1/56e13fc54d0c4.jpg,/Uploads/Warranty/1605286379000000/1/56e13fc54e835.jpg,/Uploads/Warranty/1605286379000000/1/56e13fc55038d.jpg";
        String oldPicture=",/Uploads/Warranty/940015204232008/2/598c06ebcd3a8.jpg";
        String[] split = oldPicture.split(",");
        String firstLetter = Character.toString(oldPicture.charAt(0));
        if (",".equals(firstLetter)) {//以,开头
            String filePicture=oldPicture.substring(1,oldPicture.length());
            String[] split1 = filePicture.split(",");
            //获取老系统进度表中字符串第一个字符,，如果以第一个字符为,
            List<String> convert = Convert.convert(List.class, split1);
            String file = "";
            for (String s : convert) {
                file += "https://pro-unions.oss-cn-shenzhen.aliyuncs.com" + s + "|";
            }
            System.out.println(file.substring(0,file.length()-1));

        }else {
            //获取老系统进度表中字符串第一个字符,，如果以第一个字符为,
            List<String> convert = Convert.convert(List.class, split);
            String file = "";
            for (String s : convert) {
                file += "https://pro-unions.oss-cn-shenzhen.aliyuncs.com" + s + "|";
            }
            System.out.println(file.substring(0,file.length()-1));
        }
    }


}
