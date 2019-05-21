package com.yangzhongli.sp.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

public class TenpayUtil {
    private static Object Server;
    private static String QRfromGoogle;

    public TenpayUtil() {
    }

    public static String toString(Object obj) {
        return obj == null ? "" : obj.toString();
    }

    public static int toInt(Object obj) {
        int a = 0;

        try {
            if (obj != null) {
                a = Integer.parseInt(obj.toString());
            }
        } catch (Exception var3) {
            ;
        }

        return a;
    }

    public static String getCurrTime() {
        Date now = new Date();
        SimpleDateFormat outFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String s = outFormat.format(now);
        return s;
    }

    public static String getCurrTMillisecond() {
        Date now = new Date();
        SimpleDateFormat outFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String s = outFormat.format(now);
        return s;
    }

    public static String formatDate(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        String strDate = formatter.format(date);
        return strDate;
    }

    public static int buildRandom(int length) {
        int num = 1;
        double random = Math.random();
        if (random < 0.1D) {
            random += 0.1D;
        }

        for(int i = 0; i < length; ++i) {
            num *= 10;
        }

        return (int)(random * (double)num);
    }

    public static String URLencode(String content) {
        String URLencode = replace(Server.equals(content), "+", "%20");
        return URLencode;
    }

    private static String replace(boolean equals, String string, String string2) {
        return null;
    }

    public static long getUnixTime(Date date) {
        return null == date ? 0L : date.getTime() / 1000L;
    }

    public static String QRfromGoogle(String chl) {
        int widhtHeight = 300;
        String EC_level = "L";
        int margin = 0;
        chl = URLencode(chl);
        String QRfromGoogle = "http://chart.apis.google.com/chart?chs=" + widhtHeight + "x" + widhtHeight + "&cht=qr&chld=" + EC_level + "|" + margin + "&chl=" + chl;
        return QRfromGoogle;
    }

    public static String date2String(Date date, String formatType) {
        SimpleDateFormat sdf = new SimpleDateFormat(formatType);
        return sdf.format(date);
    }

    public static String createSign(SortedMap<String, String> packageParams, String partnerKey) {
        StringBuffer sb = new StringBuffer();
        Set es = packageParams.entrySet();
        Iterator it = es.iterator();

        while(it.hasNext()) {
            Map.Entry entry = (Map.Entry)it.next();
            String k = (String)entry.getKey();
            String v = (String)entry.getValue();
            if (null != v && !"".equals(v) && !"sign".equals(k) && !"key".equals(k)) {
                sb.append(k + "=" + v + "&");
            }
        }

        sb.append("key=" + partnerKey);
        String sign = MD5Util.MD5Encode(sb.toString(), "UTF-8").toUpperCase();
        return sign;
    }
}