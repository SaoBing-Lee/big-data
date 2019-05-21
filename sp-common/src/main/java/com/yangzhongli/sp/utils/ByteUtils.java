package com.yangzhongli.sp.utils;

import static java.util.Optional.ofNullable;

/**
 * insert description here
 *
 * @author liuxiaohua
 * @since 2018/12/4 16:12
 */
public class ByteUtils {

    /**
     * @param: [byteArray]
     * @return: java.lang.String
     * @auther: texous
     * @date: 2018/8/8 17:41
     * @description: 字节数组转换成字符串
     */
    public static String toHexString(byte[] byteArray) {
        ofNullable(byteArray).filter(array -> array.length > 0)
                .orElseThrow(() -> new IllegalArgumentException("this byteArray must not be null or empty"));

        StringBuilder hexString = new StringBuilder();

        for (int i = 0; i < byteArray.length; ++i) {
            if ((byteArray[i] & 255) < 16) {
                hexString.append("0");
            }

            hexString.append(Integer.toHexString(255 & byteArray[i]));
        }

        return hexString.toString().toLowerCase();
    }

    /**
     * @param: [hexString]
     * @return: byte[]
     * @auther: texous
     * @date: 2018/8/8 17:40
     * @description: 字符串转字节数组
     */
    public static byte[] toByteArray(String hexString) {
        ofNullable(hexString).orElseThrow(() -> new IllegalArgumentException("this hexString must not be empty"));
        hexString = hexString.toLowerCase();
        byte[] byteArray = new byte[hexString.length() / 2];
        int k = 0;

        for (int i = 0; i < byteArray.length; ++i) {
            byte high = (byte) (Character.digit(hexString.charAt(k), 16) & 255);
            byte low = (byte) (Character.digit(hexString.charAt(k + 1), 16) & 255);
            byteArray[i] = (byte) (high << 4 | low);
            k += 2;
        }
        return byteArray;
    }

    public static void main(String[] args) {
        System.out.println(String.format("test %s hahaha %s hehehe %s", 1, false, null));
    }

}
