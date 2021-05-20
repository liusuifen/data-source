package com.example.mysqloracle.util;

import com.alibaba.fastjson.JSONObject;
import com.example.mysqloracle.param.MapParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

/**
 * @Title: ${FILE_NAME}
 * @Package com.pingan.inteleyes.framework.security
 * @Author: chendingyi762
 * @Description:
 * @Date: Create in 上午11:12 2017/12/6
 * @Version: V1.0
 * @Copyright: www.pingan.com
 **/
public class SHAUtil {

    private static Logger logger = LoggerFactory.getLogger(SHAUtil.class.getClass());

    public static String sha1(String data) {

        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA1");
        } catch (NoSuchAlgorithmException e) {
            return "";
        }

        md.update(data.getBytes());

        StringBuilder buf = new StringBuilder();

        byte[] bits = md.digest();
        if (bits == null) {
            return "";
        }

        int value;
        int length = bits.length;

        for (int i = 0; i < length; i++) {
            value = bits[i];
            if (value < 0) {
                value += 256;
            }
            if (value < 16) {
                buf.append("0");
            }

            buf.append(Integer.toHexString(value));
        }

        return buf.toString();
    }

    public static String sha256(String data) {

        return SHAUtil.sha(data, "SHA-256");
    }

    public static String sha(String strText, String strType) {

        //返回值
        String strResult = null;
        if (strText != null && strText.length() > 0) {

            try {

                MessageDigest messageDigest = MessageDigest.getInstance(strType);
                messageDigest.update(strText.getBytes("UTF-8"));
                byte[] byteBuffer = messageDigest.digest();
                strResult = SHAUtil.bytesToHexString(byteBuffer);

            }
            catch (Exception e) {

                logger.error("SHA sha exception:", e);
            }
        }
        return strResult;
    }


    /**
     * hmacsha1加密
     *
     * @param encryptText
     * @param encryptKey
     * @return
     */
    public static String hmacsha1(String encryptText, String encryptKey) {

        try {
            byte[] keyData = encryptKey.getBytes("UTF-8");
            SecretKey secretKey = new SecretKeySpec(keyData, "HmacSHA256");
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(secretKey);
            byte[] textData = encryptText.getBytes("UTF-8");
            byte[] digest = mac.doFinal(textData);

            return SHAUtil.bytesToHexString(digest);
        }
        catch (Exception e) {

            logger.error("SHA sha exception:", e);
            return null;
        }
    }

    /**
     * 转换成Hex
     *
     * @param bytesArray
     */
    public static String bytesToHexString(byte[] bytesArray) {

        if (bytesArray == null) {
            return null;
        }

        StringBuffer strHexString = new StringBuffer();
        for (int i = 0; i < bytesArray.length; i++) {

            String hex = Integer.toHexString(0xff & bytesArray[i]);
            if (hex.length() == 1) {

                strHexString.append('0');
            }
            strHexString.append(hex);
        }

        return strHexString.toString();
    }

    public static void main(String[] args) {
        Map<String,Object> param = MapParam
                .create("page_size",10)
                .add("page",1)
                .add("sync_type",2)
                .add("app_id","2020112515091144")
                .add("ts",String.valueOf(System.currentTimeMillis()/1000));
        System.out.println("参数："+ param.toString());
        Map<String, Object>  sortMap = MapUtil.sortMap(param);
        System.out.println("排序后："+ sortMap);
        String json = JSONObject.toJSONString(sortMap);
        System.out.println("json："+ json);
        System.out.println(SHAUtil.hmacsha1(json,"fdaeaeckshfdaeaeckshfdaeaeckshrf"));
    }
}
