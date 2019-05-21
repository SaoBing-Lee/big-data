package com.yangzhongli.sp.utils;

import java.util.Locale;
import java.util.Random;
import java.util.UUID;

/**
 * insert description here
 *
 * @author liuxiaohua
 * @since 2018/7/26 11:31
 */
public class StringUtils {

    /**
     * 判断是否为 null 或者 "" 字符串
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        return str == null || "".equals(str);
    }

    /**
     * 判断是否为 null 或者 只有空格的字符串
     * @param str
     * @return
     */
    public static boolean isTrimEmpty(String str) {
        return str == null || "".equals(str.trim());
    }

    /**
     * Returns whether the given CharSequence contains only digits.
     */
    public static boolean isDigitsOnly(CharSequence str) {
        final int len = str.length();
        for (int i = 0; i < len; i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    final static String LETTERS[];
    static{
        LETTERS="0,1,2,3,4,5,6,7,8,9,a,b,c,d,e,f,g,h,i,j,0,1,2,3,4,5,6,7,8,9,k,l,m,n,o,p,q,r,0,1,2,3,4,5,6,7,8,9,s,t,u,v,w,x,y,z,0,1,2,3,4,5,6,7,8,9".split(",");
    }

    /**
     * 生成随机字符串
     * @param length
     * @return
     */
    public static String randomCharacter(int length){
        if(length<1)return "";
        Random rd = new Random();
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<length;i++){
            sb.append(LETTERS[rd.nextInt(LETTERS.length)]);
        }
        return sb.toString();
    }

    /**
     * 生成随机数字字符串
     * @param length
     * @return
     */
    public static String randomIntString(int length){
        if(length<1)return "";
        Random rd = new Random();
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<length;i++){
            sb.append(rd.nextInt(10));
        }
        return sb.toString();
    }

    /**
     * 创建 uniqueId
     * @param uid
     * @return
     */
    public static String createUniqueId(int uid){
        return MD5.MD5(Integer.toHexString(uid) + UUID.randomUUID().toString());
    }

    /**
     * 创建 uniqueId
     * @param prefix
     * @return
     */
    public static String createUniqueId(String prefix){
        return MD5.MD5(prefix + UUID.randomUUID().toString());
    }

    /**
     * 将驼峰转化为下划线
     * @param name
     * @return
     */
    public static String underscoreName(String name) {
        if (StringUtils.isTrimEmpty(name)) {
            return "";
        }
        StringBuilder result = new StringBuilder();
        result.append(lowerCaseName(name.substring(0, 1)));

        for(int i = 1; i < name.length(); ++i) {
            String s = name.substring(i, i + 1);
            String slc = lowerCaseName(s);
            if (!s.equals(slc)) {
                result.append("_").append(slc);
            } else {
                result.append(s);
            }
        }

        return result.toString();
    }

    /**
     * 将下划线转换为驼峰标识
     * @param name
     * @return
     */
    public static String humpName(String name) {
        if (StringUtils.isTrimEmpty(name)) {
            return "";
        }

        StringBuilder result = new StringBuilder();
        boolean needUpper = false;
        for(int i = 0; i < name.length(); ++i) {
            if ('_' == name.charAt(i)) {
                needUpper = true;
                continue;
            } else {
                if (needUpper) {
                    result.append(upperCaseName(name.substring(i, i+1)));
                    needUpper = false;
                } else
                    result.append(name.charAt(i));
            }
        }

        return result.toString();

    }

    /**
     * 英文字母转换为小写
     * @param name
     * @return
     */
    public static String lowerCaseName(String name) {
        return name.toLowerCase(Locale.US);
    }

    /**
     * 英文字母转换为大写
     * @param name
     * @return
     */
    public static String upperCaseName(String name) {
        return name.toUpperCase(Locale.US);
    }

    public static void main(String[] args) {
        String str = underscoreName("aaBBcddSSd");
        System.out.println(str);
        System.out.println(humpName(str));
        System.out.println(lowerCaseName("AADdadsSDDD你好啊"));
        System.out.println(upperCaseName("AADdadsSDDD你好啊"));

        System.out.println("'123456Dsde' 是否为纯数字: " + isDigitsOnly("123456Dsde"));
        System.out.println("'123456' 是否为纯数字: " + isDigitsOnly("123456"));

        System.out.println("随机长度字符串：" + randomCharacter(10));
        System.out.println("随机长度数字字符串：" + randomIntString(6));
        System.out.println("创建随机UniqueId：" + createUniqueId(10));
        System.out.println("创建随机UniqueId：" + createUniqueId(5));
        System.out.println("创建随机UniqueId: " + createUniqueId("ddd"));
        System.out.println("创建随机UniqueId: " + createUniqueId("bbb"));

    }

   /* *//**
     * 英文字母转换为大写
     * @param name
     * @return
     *//*
    public static String spiltToList(String name) {


    }
*/

}
