package com.example.mysqloracle.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ProPertiesUtil {


    public static String getValue(String fileNamePath, String key){
        Properties props = new Properties();
        InputStream in = null;
        try {
            in = new FileInputStream(fileNamePath);
            // 如果将in改为下面的方法，必须要将.Properties文件和此class类文件放在同一个包中
            //in = propertiesTools.class.getResourceAsStream(fileNamePath);
            props.load(in);
            String value = props.getProperty(key);
            // 有乱码时要进行重新编码
            // new String(props.getProperty("name").getBytes("ISO-8859-1"), "GBK");
            return value;

        } catch (IOException e) {
            e.printStackTrace();
            return null;

        } finally {
            if (null != in) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
