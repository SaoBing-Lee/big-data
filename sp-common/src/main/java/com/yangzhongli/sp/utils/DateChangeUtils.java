package com.yangzhongli.sp.utils;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * @author liyanbing
 * @ClassName:DateChangeUtils
 * @Description: 时间相关需要的工具类
 * @date 2018/5/23 9:31
 */
public class DateChangeUtils {


    public static final String PHONE_PATTERN = "1\\d{10}";

    private static Logger logger = LoggerFactory.getLogger(DateChangeUtils.class);

    /**
     * String时间截取,并返回 String
     *
     * @param time
     * @return
     */
    public static String getTimeCompare(Date time) {
        DateTime dt = new DateTime(time);
        DateTime dateTime = new DateTime(new Date());
        /**
         * 传入时间比较
         */
        Calendar c1 = Calendar.getInstance();
        c1.setTime(time);
        int year1 = c1.get(Calendar.YEAR);
        int month1 = c1.get(Calendar.MONTH) + 1;
        int day1 = c1.get(Calendar.DAY_OF_MONTH);
        Calendar c2 = Calendar.getInstance();
        c2.setTime(new Date());
        int year2 = c2.get(Calendar.YEAR);
        int month2 = c2.get(Calendar.MONTH) + 1;
        int day2 = c2.get(Calendar.DAY_OF_MONTH);

        //年
        int year = dt.getYear();
        //当前时间的年
        int dateYear = dateTime.getYear();


        /**
         * 比较两个时间是否是同一天
         */
        if (year1 == year2 && month1 == month2 && day1 == day2) {
            String newTimeHour = dt.toString("HH:mm");
            return newTimeHour;
        } else {
            if (year == dateYear) {
                String newTimeDay = dt.toString("MM-dd HH:mm");
                return newTimeDay;
            } else {
                return dt.toString("yyyy-MM-dd HH:mm");
            }
        }

    }

    /**
     * 时间转换为制定格式
     *
     * @param d
     * @return
     */
    public static Date changeDateSize(Date d) {
        if (d == null) {
            return null;
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String strDate = formatter.format(d);
        try {
            date = formatter.parse(strDate);
        } catch (Exception e) {
            logger.error("changeDateSize()的String转为Date出错" + e);
        }
        return date;
    }

    /**
     * 时间转换为制定格式
     *
     * @param d
     * @return
     */
    public static String changeDateShow(Date d) {
        String newTime = null;
        if (d == null) {
            return null;
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = formatter.format(d);
        try {
            Date date = formatter.parse(strDate);
            DateTime dateTime = new DateTime(date);
            int days = dateTime.getDayOfMonth();
            int months = dateTime.getMonthOfYear();
            newTime = months + "月" + days + "日";
        } catch (Exception e) {
            logger.error("changeDateShow()的String转为Date出错" + e);
        }
        return newTime;
    }


    /**
     * 时间转换为制定格式
     *
     * @param d
     * @return
     */
    public static Date changeDateCutss(Date d) {
        if (d == null) {
            return null;
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = new Date();
        String strDate = formatter.format(d);
        try {
            date = formatter.parse(strDate);
        } catch (Exception e) {
            logger.error("changeDateCutss()的String转为Date出错" + e);
        }
        return date;
    }


    /**
     * 将字符串转换为Date类型
     *
     * @param str
     * @return
     */
    private static Date setNewTimeStringDateYear(String str) {
        if (StringUtils.isEmpty(str)) {
            return null;
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = new Date();
        try {
            date = formatter.parse(str);
        } catch (Exception e) {
            logger.error("setNewTimeStringDateYear()的String转为Date出错" + e);
        }
        return date;

    }

    private static Date setNewTimeStringDateDay(String str) {
        SimpleDateFormat formatter = new SimpleDateFormat("MM-dd HH:mm");
        Date date = new Date();
        try {
            date = formatter.parse(str);
        } catch (Exception e) {
            logger.error("setNewTimeStringDateDay()的String转为Date出错" + e);
        }
        return date;

    }

    private static Date setNewTimeStringDateHour(String str) {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        Date date = new Date();
        try {
            date = formatter.parse(str);
        } catch (Exception e) {
            logger.error("setNewTimeStringDateHour()的String转为Date出错" + e);
        }
        return date;
    }

    /**
     * Date格式化
     *
     * @param d
     * @return
     */
    public static Date newTimeDateHour(Date d) {
        if (d == null) {
            return null;
        }
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        Date date = new Date();
        String strDate = formatter.format(d);
        try {
            date = formatter.parse(strDate);
        } catch (Exception e) {
            logger.error("setNewTimeDateHour()出错" + e);
        }
        return date;
    }


    /**
     * 对用户名--电话号码的处理
     *
     * @param str
     * @return
     */
    public static String getMobileHide(String str) {
        if (!StringUtils.isEmpty(str)) {
            boolean matches = Pattern.compile(PHONE_PATTERN).matcher(str).matches();
            if (matches) {
                String mobileHide = str.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
                return mobileHide;
            }
        }
        return str;
    }


    /**
     * 得到几天前的时间
     * @param d
     * @param day
     * @return
     */
    public static Date getDateBefore(Date d,int day){
        Calendar now =Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.DATE,now.get(Calendar.DATE)-day);
        return now.getTime();
    }

}
