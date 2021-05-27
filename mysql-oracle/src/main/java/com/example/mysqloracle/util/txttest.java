package com.example.mysqloracle.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;

public class txttest {


    /**
     * 读取txt文件的内容
     *
     * @param file 想要读取的文件对象
     * @return 返回文件内容
     */
    public static String txt2String(File file) {
        String result = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
            String s = null;
            while ((s = br.readLine()) != null) {//使用readLine方法，一次读一行
                result = result + "\n" + s;
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) {
        File file = new File("C:\\Users\\bl007\\Desktop\\源数据.txt");
        String contextJson = txt2String(file);
//        List<ProductCommissionJsonData> jsonData = JsonUtil.parseList(contextJson, ProductCommissionJsonData.class);

    }

}
