package com.example.mysqloracle.util;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

public class JsonUtil {

    public static <T> T parseObject(Object obj,Class<T> cls){
        if(obj == null || cls == null){
            return null;
        }
        return JSONObject.parseObject(JSONObject.toJSONString(obj),cls);
    }

    public static <T> T parseString(String json,Class<T> cls){
        if(json == null || cls == null){
            return null;
        }
        return JSONObject.parseObject(json,cls);
    }

    public static <T> List<T> parseList(Object obj, Class<T> cls){
        if(obj == null || cls == null){
            return null;
        }
        return JSONObject.parseArray(JSONObject.toJSONString(obj), cls);
    }

    public static <T> List<T> parseStringList(String obj, Class<T> cls){
        if(obj == null || cls == null){
            return null;
        }
        return JSONObject.parseArray(obj, cls);
    }
}
