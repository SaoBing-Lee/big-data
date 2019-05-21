package com.yangzhongli.sp.utils;

import org.springframework.beans.BeanUtils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.*;

/**
 * insert description here
 *
 * @author liuxiaohua
 * @since 2018/7/26 11:29
 */
public class ConverterUtil {


    public static List<String> changes(String str) {
        List<String> list = new ArrayList<>();
        String[] str2 = str.split(";");
        for (String s : str2) {
            list.add(s);
        }
        return list;
    }

    /**
     * 对象之间转换
     *
     * @param clazz
     * @param object
     * @param <T>
     * @return
     */
    public static <T> T convert(Class<T> clazz, Object object) {
        T obj = null;
        if (object != null) {
            try {
                obj = clazz.newInstance();
                BeanUtils.copyProperties(object, obj);
            } catch (InstantiationException var4) {
                var4.printStackTrace();
            } catch (IllegalAccessException var5) {
                var5.printStackTrace();
            }
        }

        return obj;
    }

    public static <T, D> List<T> convert(Class<T> clazz, List<D> objList) {
        List<T> resultList = new ArrayList();
        if (objList != null && !objList.isEmpty()) {
            Iterator<D> var3 = objList.iterator();

            while (var3.hasNext()) {
                D obj = var3.next();
                resultList.add(convert(clazz, obj));
            }
        }
        return resultList;
    }

    public static <T, D> T transMap2Bean(Class<T> tClass, Map<String, D> map) {
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(tClass);
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            T t = tClass.newInstance();
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();
                String key2 = StringUtils.underscoreName(key);
                if (map.containsKey(key)) {
                    D value = map.get(key);
                    // 得到property对应的setter方法
                    Method setter = property.getWriteMethod();
                    setter.invoke(t, value);
                } else if (map.containsKey(key2)) {
                    D value = map.get(key2);
                    // 得到property对应的setter方法
                    Method setter = property.getWriteMethod();
                    setter.invoke(t, value);
                }
            }
            return t;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T, D> List<T> transMap2Bean(Class<T> tClass, List<Map<String, D>> origins) {
        if (origins == null || origins.isEmpty())
            return null;
        List<T> list = new ArrayList<>();
        for (Map<String, D> origin : origins) {
            list.add(transMap2Bean(tClass, origin));
        }
        return list;
    }

    public static <T> Map<String, Object> transBean2Map(T obj) {

        if (obj == null) {
            return null;
        }
        Map<String, Object> map = new HashMap<>();
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();
                // 过滤class属性
                if (!key.equals("class")) {
                    // 得到property对应的getter方法
                    Method getter = property.getReadMethod();
                    Object value = getter.invoke(obj);
                    map.put(key, value);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    public static <T> List<Map<String, Object>> transBean2Map(List<T> objs) {
        if (objs == null || objs.isEmpty())
            return null;

        List<Map<String, Object>> resultList = new ArrayList<>();
        for (T t : objs) {
            resultList.add(transBean2Map(t));
        }
        return resultList;
    }


    /**
     * 数组转为字符串
     *
     * @param str 数组
     * @return
     */
    public static String changeToString(String[] str) {
        String outStr = "";
        if (str.length > 0) {
            for (int i = 0; i < str.length; i++) {
                if (i == str.length) {
                    outStr += str[i];
                } else {
                    outStr += str[i] + ",";
                }
            }
            return outStr;
        } else {
            return null;
        }

    }
}
