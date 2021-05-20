package com.example.mysqloracle.util;

import com.google.common.collect.Lists;

import java.math.BigDecimal;
import java.util.*;

public class MapUtil {

    public <K,V> boolean isEmpty(Map<K,V> map){
        return map == null || map.size() == 0 ;
    }

    public <K,V> boolean isNotEmpty(Map<K,V> map){
        return !isEmpty(map);
    }


    /**
     * 得到map 的key值 并转换字符串
     *
     * @param data map对象
     * @param key  map key.
     * @return 返回map 的key值 并转换字符串
     */
    public static String getStringObjectDefaultNull(Map<?, ?> data, Object key) {
        return getStringObjectDefault(data, key, null);
    }

    /**
     * 得到map中key对着的value值并转化成字符串 不存在或者数据为空返回默认值
     *
     * @param data     map对象
     * @param key      map key
     * @param defaults 默认值
     * @return map中key对着的value值并转化成字符串 不存在或者数据为空返回默认值
     */
    public static String getStringObjectDefault(Map<?, ?> data, Object key, String defaults) {
        if (data == null || key == null) {
            return null;
        }
        if (!data.containsKey(key)) {
            return defaults;
        }
        Object p = data.get(key);
        if (p == null) {
            return defaults;
        }
        String n = p.toString();
        return n.trim().length() < 1 ? defaults : n;
    }


    /**
     * 得到map 对象key的value值并转换成Integer
     *
     * @param data map对象
     * @param key  key值
     * @return map中的Integer值 无法转换或不存在返回null
     */
    public static Integer getIntegerObjectDefaultNull(Map<?, ?> data, String key) {
        String data1 = getStringObjectDefaultNull(data, key);
        if (data1 == null) {
            return null;
        }
        try {
            return Integer.valueOf(data1);
        } catch (Throwable e) {
        }
        return null;
    }


    /**
     * 得到map 对象key的value值并转换成Integer
     *
     * @param data map对象
     * @param key  key值
     * @return map中的Integer值 无法转换或不存在返回null
     */
    public static Short getShortObjectDefaultNull(Map<?, ?> data, String key) {
        String data1 = getStringObjectDefaultNull(data, key);
        if (data1 == null) {
            return null;
        }
        try {
            return Short.valueOf(data1);
        } catch (Throwable e) {
        }
        return null;
    }

    /**
     * 得到map 对象key的value值并转换成Double
     *
     * @param data map对象
     * @param key  key值
     * @return map中的Integer值 无法转换或不存在返回null
     */
    public static Double getDoubleObjectDefaultNull(Map<?, ?> data, String key) {
        String data1 = getStringObjectDefaultNull(data, key);
        if (data1 == null) {
            return null;
        }
        try {
            return Double.valueOf(data1);
        } catch (Throwable e) {
        }
        return null;
    }


    public static Integer getIntegerObjectDefault(Map<?, ?> data, String key, Integer defVal) {
        String data1 = getStringObjectDefaultNull(data, key);
        if (data1 == null) {
            return defVal;
        }
        try {
            return Integer.valueOf(data1);
        } catch (Throwable e) {
        }
        return defVal;
    }

    /**
     * 得到map 对象key的value值并转换成Integer
     *
     * @param data map对象
     * @param key  key值
     * @return map中的Integer值 无法转换或不存在返回null
     */
    public static Byte getByteObjectDefaultNull(Map<?, ?> data, String key) {
        String data1 = getStringObjectDefaultNull(data, key);
        if (data1 == null) {
            return null;
        }
        try {
            return Byte.valueOf(data1);
        } catch (Throwable e) {
        }
        return null;
    }

    /**
     * 得到map 对象key的value值并转换成Integer
     *
     * @param data map对象
     * @param key  key值
     * @return map中的Integer值 无法转换或不存在返回null
     */
    public static Byte getByteObjectDefault(Map<?, ?> data, String key, Byte def) {
        String data1 = getStringObjectDefaultNull(data, key);
        if (data1 == null) {
            return null;
        }
        if (!data.containsKey(key)) {
            return null;
        }
        try {
            return Byte.valueOf(data1);
        } catch (Throwable e) {
        }
        return def;
    }


    /**
     * 得到map 对象key的value值并转换成Date
     *
     * @param data map对象
     * @param key  key值
     * @return map中的Date值 无法转换或不存在返回null
     */
    public static Date getDateObjectDefaultNull(Map<?, ?> data, String key) {
        String data1 = getStringObjectDefaultNull(data, key);
        if (data1 == null) {
            return null;
        }
        try {
            return DateUtil.parseDate(data1);
        } catch (Throwable e) {
        }
        return null;
    }


    /**
     * 得到map 对象key的value值并转换成Long
     *
     * @param data map对象
     * @param key  key值
     * @return map中的Long值 无法转换或不存在返回null
     */
    public static Long getLongObjectDefaultNull(Map<?, ?> data, String key) {
        String data1 = getStringObjectDefaultNull(data, key);
        if (data1 == null) {
            return null;
        }
        try {
            return Long.valueOf(data1);
        } catch (Throwable e) {
        }
        return null;
    }

    /**
     * 得到map 对象key的value值并转换成Long
     *
     * @param data map对象
     * @param key  key值
     * @return map中的Long值 无法转换或不存在返回null
     */
    public static Long getLongObjectDefault(Map<?, ?> data, String key, Long def) {
        String data1 = getStringObjectDefaultNull(data, key);
        if (data1 == null) {
            return null;
        }
        if (!data.containsKey(key)) {
            return null;
        }
        try {
            return Long.valueOf(data1);
        } catch (Throwable e) {
        }
        return def;
    }

    /**
     * 得到map 对象key的value值并转换成BigDecimal
     *
     * @param data map对象
     * @param key  key值
     * @return map中的Integer值 无法转换或不存在返回null
     */
    public static BigDecimal getBigDecimalObjectDefaultNull(Map<?, ?> data, String key) {
        String data1 = getStringObjectDefaultNull(data, key);
        if (data1 == null) {
            return null;
        }
        try {

            return new BigDecimal(data1);
        } catch (Throwable e) {
        }
        return null;
    }


    /**
     * 得到map 对象key的value值并转换成List<Long>
     *
     * @param data map对象
     * @param key  key值
     * @return map中的List<Long>值 无法转换或不存在返回null
     */
    public static List getList(Map<?, ?> data, String key) {
        if (data == null) {
            return null;
        }
        if (!data.containsKey(key)) {
            return null;
        }
        Object v = data.get(key);
        if (v == null) {
            return null;
        }
        List list = Lists.newArrayList();

        if (v instanceof List) {
            List<?> list2 = (List<?>) v;
            if (list2 != null) {
                for (Object o : list2) {
                    if (o != null) {
                        String p = o.toString().trim();
                        if (p.length() > 0) {
                            try {

                                list.add(p);
                            } catch (Throwable e) {
                            }
                        }
                    }
                }
            }
        } else if (v instanceof Object[]) {
            Object[] list2 = (Object[]) v;
            if (list2 != null) {
                for (Object o : list2) {
                    String p = o.toString().trim();
                    if (p.length() > 0) {
                        try {

                            list.add(p);
                        } catch (Throwable e) {
                        }
                    }
                }
            }
        } else {
            String p = v.toString().trim();
            if (p.length() > 0) {
                try {

                    list.add(p);
                } catch (Throwable e) {
                }
            }
        }
        return list.isEmpty() ? null : list;
    }

    /**
     * 得到map 对象key的value值并转换成List<Long>
     *
     * @param data map对象
     * @param key  key值
     * @return map中的List<Long>值 无法转换或不存在返回null
     */
    public static List<Long> getLongList(Map<?, ?> data, String key) {
        try {
            List list = getList(data, key);
            if (list == null || list.isEmpty()) {
                return null;
            }
            List<Long> resultList = Lists.newArrayList();
            for (Object ele : list) {
                resultList.add(Long.valueOf(ele.toString()));
            }
            return resultList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 得到map 对象key的value值并转换成String
     *
     * @param data map对象
     * @param key  key值
     * @return map中的String值 无法转换或不存在返回null
     */
    public static String getMapData(Map<?, ?> data, Object key) {
        if (data == null) {
            return null;
        }
        if (!data.containsKey(key)) {
            return null;
        }
        Object p = data.get(key);
        if (p == null) {
            return null;
        }
        String n = p.toString();
        return n.trim().length() < 1 ? null : n;
    }

    /**
     * 得到map 对象key的value值并转换成String
     *
     * @param data map对象
     * @param key  key值
     * @return map中的String值 无法转换或不存在返回null
     */
    public static String getMapDataContainKeyDefault(Map<?, ?> data, Object key, String containKeyDefaultValue) {
        if (data == null) {
            return null;
        }
        if (!data.containsKey(key)) {
            return null;
        }
        Object p = data.get(key);
        if (p == null) {
            return containKeyDefaultValue;
        }
        String n = p.toString();
        return n.trim().length() < 1 ? containKeyDefaultValue : n;
    }

    /**
     * 得到map 对象key的value值并转换成默认值的类
     *
     * @param data                   map对象
     * @param key                    key值
     * @param containKeyDefaultValue map中的String值 无法转换或不存在返回null
     * @return
     */
    public static <T> T getMapData(Map<?, ?> data, String key, T containKeyDefaultValue) {
        if (data == null) {
            return null;
        }
        if (!data.containsKey(key)) {
            return null;
        }
        Object p = data.get(key);
        if (p == null) {
            return containKeyDefaultValue;
        }

        if (containKeyDefaultValue instanceof String) {
            String n = p.toString();
            T s = n.trim().length() < 1 ? containKeyDefaultValue : (T) n;
            return s;
        }
        if (containKeyDefaultValue instanceof Long) {
            return (T) getLongObjectDefaultNull(data, key);
        }
        if (containKeyDefaultValue instanceof Integer) {
            return (T) getIntegerObjectDefaultNull(data, key);
        }
        if (containKeyDefaultValue instanceof Date) {
            return (T) getDateObjectDefaultNull(data, key);
        }
        return (T) p;
    }

    /**
     * 对参数按照key升序排序
     *
     * @param params
     * @return
     */
    public static Map<String, Object> sortMap(Map<String, Object> params)
    {

        TreeMap<String, Object> treeMap = new TreeMap<String, Object>(new Comparator<String>()
        {
            @Override
            public int compare(String o1, String o2)
            {
                return o1.compareTo(o2);
            }
        });
        for (String key : params.keySet())
        {
            treeMap.put(key, params.get(key));
        }

        return treeMap;
    }

    /**
     * 对平铺后的参数排序、做拼接操作
     *
     * @params params
     * @return
     */
    public static String combineParams(Map<String, Object> params) throws Exception {

        StringBuilder combinedParams = new StringBuilder();
        for (String key : params.keySet())
        {
            combinedParams.append(key).append("=").append(params.get(key).toString()).append("&");
        }
        if (combinedParams.length() > 1)
        {
            return combinedParams.substring(0, combinedParams.length() - 1);
        }

        return combinedParams.toString();
    }
}
