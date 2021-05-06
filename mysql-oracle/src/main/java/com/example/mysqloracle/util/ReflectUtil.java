package com.example.mysqloracle.util;

public class ReflectUtil {

    public static boolean isNotNull(Object obj) {
        if (obj != null) {
            return true;
        }
        return false;
    }

    public static boolean isNull(Object obj) {
        boolean result = !isNotNull(obj);
        return result;
    }
}