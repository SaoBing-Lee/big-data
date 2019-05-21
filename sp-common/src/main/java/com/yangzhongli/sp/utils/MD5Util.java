package com.yangzhongli.sp.utils;

import sun.misc.BASE64Encoder;

import java.security.MessageDigest;

public class MD5Util {
    private static final String[] hexDigits = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

    public MD5Util() {
    }

    private static String byteArrayToHexString(byte[] b) {
        StringBuffer resultSb = new StringBuffer();

        for(int i = 0; i < b.length; ++i) {
            resultSb.append(byteToHexString(b[i]));
        }

        return resultSb.toString();
    }

    private static String byteToHexString(byte b) {
        int n = b;
        if (b < 0) {
            n = b + 256;
        }

        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    /**
     *
     * @param pwd
     *            需要加密的字符串
     * @param isUpper
     *            字母大小写(false为默认小写，true为大写)
     * @param bit
     *            加密的类型（16,32,64）
     * @return
     */
    public static String getMD5(String pwd, boolean isUpper, Integer bit) {
        String md5 = new String();
        try {
            // 创建加密对象
            MessageDigest md = MessageDigest.getInstance("md5");
            if (bit == 64) {
                BASE64Encoder bw = new BASE64Encoder();
                String bsB64 = bw.encode(md.digest(pwd.getBytes("utf-8")));
                md5 = bsB64;
            } else {
                // 计算MD5函数
                md.update(pwd.getBytes());
                byte b[] = md.digest();
                int i;
                StringBuffer sb = new StringBuffer("");
                for (int offset = 0; offset < b.length; offset++) {
                    i = b[offset];
                    if (i < 0)
                        i += 256;
                    if (i < 16)
                        sb.append("0");
                    sb.append(Integer.toHexString(i));
                }
                md5 = sb.toString();
                if(bit == 16) {
                    //截取32位md5为16位
                    String md16 = md5.substring(8, 24).toString();
                    md5 = md16;
                    if (isUpper)
                        md5 = md5.toUpperCase();
                    return md5;
                }
            }
            //转换成大写
            if (isUpper)
                md5 = md5.toUpperCase();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("md5加密抛出异常！");
        }

        return md5;
    }


    public static void main(String[] args) throws Exception {
        String a = "newBeauty^&10";
        String md5a = getMD5(a, true, 16);
        System.out.println(md5a);
        System.out.println(md5a.length());
    }


    public static String MD5Encode(String origin, String charsetname) {
        String resultString = null;

        try {
            resultString = new String(origin);
            MessageDigest md = MessageDigest.getInstance("MD5");
            if (charsetname != null && !"".equals(charsetname)) {
                resultString = byteArrayToHexString(md.digest(resultString.getBytes(charsetname)));
            } else {
                resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
            }
        } catch (Exception var4) {
            ;
        }

        return resultString;
    }
}
