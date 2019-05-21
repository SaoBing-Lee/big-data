package com.yangzhongli.sp.utils;

import com.yangzhongli.sp.constants.WxPay;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * 微信支付工具类
 */
public class LyyPaymentUtils {

    private static final Logger logger = LoggerFactory.getLogger(LyyPaymentUtils.class);

    public static final String PAYMENT_METHOD_WECHAT = "1";             //微信支付
    public static final String PAYMENT_METHOD_ALIPAY = "2";             //支付宝支付
    public static final String PAYMENT_METHOD_REFUND = "5";             //退款
    private static final int OUT_TRADE_NO_MAX_LENGTH = 30;              //订单号最大长度



    public static Map<String, String> getPackage(WxPay tpWxPayDto) {
        String attach = tpWxPayDto.getAttach();
        String deviceInfo = tpWxPayDto.getDeviceInfo();
        String out_trade_no = tpWxPayDto.getOutTradeNo();
        String totalFee = getMoney(tpWxPayDto.getTotalFee());
        String spbill_create_ip = tpWxPayDto.getSpbillCreateIp();
        String notify_url = tpWxPayDto.getNotifyUrl();
        String trade_type = tpWxPayDto.getTradeType();
        String body = tpWxPayDto.getBody();
        String nonce_str = getNonceStr();
        String openId = tpWxPayDto.getOpenId();
        String productId = tpWxPayDto.getProductId();

        SortedMap<String, String> packageParams = new TreeMap<String, String>();
        packageParams.put("appid", SystemConfigurer.PAY_APP_ID);
        packageParams.put("mch_id", SystemConfigurer.PAY_PARTNER_NO);
        packageParams.put("nonce_str", nonce_str);
        packageParams.put("body", body);
        packageParams.put("out_trade_no", out_trade_no);
        packageParams.put("total_fee", totalFee);
        packageParams.put("spbill_create_ip", spbill_create_ip);
        packageParams.put("notify_url", notify_url);
        packageParams.put("trade_type", trade_type);
        if (attach != null) {
            packageParams.put("attach", attach);
        }
        if (deviceInfo != null) {
            packageParams.put("device_info", deviceInfo);
        }
        if (openId != null) {
            packageParams.put("openid", openId);
        }
        if (productId != null) {
            packageParams.put("product_id", productId);
        }
        String sign = TenpayUtil.createSign(packageParams, SystemConfigurer.PAY_PARTNER_KEY);
        StringBuilder xml = new StringBuilder();
        xml.append("<xml><appid>" + SystemConfigurer.PAY_APP_ID + "</appid>");
        xml.append("<body>" + body + "</body>");
        xml.append("<mch_id>" + SystemConfigurer.PAY_PARTNER_NO + "</mch_id>");
        xml.append("<nonce_str>" + nonce_str + "</nonce_str>");
        xml.append("<notify_url>" + notify_url + "</notify_url>");
        xml.append("<out_trade_no>" + out_trade_no + "</out_trade_no>");
        xml.append("<spbill_create_ip>" + spbill_create_ip + "</spbill_create_ip>");
        xml.append("<total_fee>" + totalFee + "</total_fee>");
        xml.append("<trade_type>" + trade_type + "</trade_type>");
        if (attach != null) {
            xml.append("<attach>" + attach + "</attach>");
        }
        if (deviceInfo != null) {
            xml.append("<device_info>" + deviceInfo + "</device_info>");
        }
        if (openId != null) {
            xml.append("<openid>" + openId + "</openid>");
        }
        if (productId != null) {
            xml.append("<product_id>" + productId + "</product_id>");
        }
        xml.append("<sign><![CDATA[" + sign + "]]></sign></xml>");
        String createOrderURL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
        String prepay_id = getPayNo(createOrderURL, xml.toString());
        if (StringUtils.isEmpty(prepay_id)) {
        	return null;
        }
        SortedMap<String, String> finalpackage = new TreeMap<String, String>();
        String timestamp = Sha1Util.getTimeStamp();
        String packages = "prepay_id=" + prepay_id;
        finalpackage.put("appId", SystemConfigurer.PAY_APP_ID);
        finalpackage.put("timeStamp", timestamp);
        finalpackage.put("nonceStr", nonce_str);
        finalpackage.put("package", packages);
        finalpackage.put("signType", "MD5");
        String finalSign = TenpayUtil.createSign(finalpackage, SystemConfigurer.PAY_PARTNER_KEY);
        finalpackage.put("paySign", finalSign);
        finalpackage.put("notifyUrl", notify_url);
        finalpackage.put("outTradeNo", out_trade_no);
        return finalpackage;
    }


    //查询支付订单
    public static Map<String, String> getWxOrder(WxPay tpWxPayDto) {
        String out_trade_no = tpWxPayDto.getOutTradeNo();
        String notify_url = tpWxPayDto.getNotifyUrl();
        String nonce_str = getNonceStr();

        SortedMap<String, String> packageParams = new TreeMap<String, String>();
        packageParams.put("appid", SystemConfigurer.PAY_APP_ID);
        packageParams.put("mch_id", SystemConfigurer.PAY_PARTNER_NO);
        packageParams.put("nonce_str", nonce_str);
        packageParams.put("out_trade_no", out_trade_no);
        String sign = TenpayUtil.createSign(packageParams, SystemConfigurer.PAY_PARTNER_KEY);
        StringBuilder xml = new StringBuilder();
        xml.append("<xml><appid>" + SystemConfigurer.PAY_APP_ID + "</appid>");
        xml.append("<mch_id>" + SystemConfigurer.PAY_PARTNER_NO + "</mch_id>");
        xml.append("<nonce_str>" + nonce_str + "</nonce_str>");
        xml.append("<out_trade_no>" + out_trade_no + "</out_trade_no>");
        xml.append("<sign><![CDATA[" + sign + "]]></sign></xml>");
        String createOrderURL = "https://api.mch.weixin.qq.com/pay/orderquery";
        String prepay_id = getPayNo(createOrderURL, xml.toString());
        if (StringUtils.isEmpty(prepay_id)) {
            return null;
        }
        SortedMap<String, String> finalpackage = new TreeMap<String, String>();
        String timestamp = Sha1Util.getTimeStamp();
        String packages = "prepay_id=" + prepay_id;
        finalpackage.put("appId", SystemConfigurer.PAY_APP_ID);
        finalpackage.put("timeStamp", timestamp);
        finalpackage.put("nonceStr", nonce_str);
        finalpackage.put("package", packages);
        finalpackage.put("signType", "MD5");
        String finalSign = TenpayUtil.createSign(finalpackage, SystemConfigurer.PAY_PARTNER_KEY);
        finalpackage.put("paySign", finalSign);
        finalpackage.put("notifyUrl", notify_url);
        finalpackage.put("outTradeNo", out_trade_no);
        return finalpackage;
    }


    public static Map<String, String> getGroupPackage(WxPay tpWxPayDto) {
        String attach = tpWxPayDto.getAttach();
        String deviceInfo = tpWxPayDto.getDeviceInfo();
        String out_trade_no = tpWxPayDto.getOutTradeNo();
        String totalFee = getMoney(tpWxPayDto.getTotalFee());
        String spbill_create_ip = tpWxPayDto.getSpbillCreateIp();
        String notify_url = tpWxPayDto.getNotifyUrl();
        String trade_type = tpWxPayDto.getTradeType();
        String body = tpWxPayDto.getBody();
        String nonce_str = getNonceStr();
        String openId = tpWxPayDto.getOpenId();
        String productId = tpWxPayDto.getProductId();

        SortedMap<String, String> packageParams = new TreeMap<String, String>();
        packageParams.put("appid", SystemConfigurer.GROUP_PAY_APP_ID);
        packageParams.put("mch_id", SystemConfigurer.GROUP_PAY_PARTNER_NO);
        packageParams.put("nonce_str", nonce_str);
        packageParams.put("body", body);
        packageParams.put("out_trade_no", out_trade_no);
        packageParams.put("total_fee", totalFee);
        packageParams.put("spbill_create_ip", spbill_create_ip);
        packageParams.put("notify_url", notify_url);
        packageParams.put("trade_type", trade_type);
        if (attach != null) {
            packageParams.put("attach", attach);
        }
        if (deviceInfo != null) {
            packageParams.put("device_info", deviceInfo);
        }
        if (openId != null) {
            packageParams.put("openid", openId);
        }
        if (productId != null) {
            packageParams.put("product_id", productId);
        }
        String sign = TenpayUtil.createSign(packageParams, SystemConfigurer.GROUP_PAY_PARTNER_KEY);
        StringBuilder xml = new StringBuilder();
        xml.append("<xml><appid>" + SystemConfigurer.GROUP_PAY_APP_ID + "</appid>");
        xml.append("<body>" + body + "</body>");
        xml.append("<mch_id>" + SystemConfigurer.GROUP_PAY_PARTNER_NO + "</mch_id>");
        xml.append("<nonce_str>" + nonce_str + "</nonce_str>");
        xml.append("<notify_url>" + notify_url + "</notify_url>");
        xml.append("<out_trade_no>" + out_trade_no + "</out_trade_no>");
        xml.append("<spbill_create_ip>" + spbill_create_ip + "</spbill_create_ip>");
        xml.append("<total_fee>" + totalFee + "</total_fee>");
        xml.append("<trade_type>" + trade_type + "</trade_type>");
        if (attach != null) {
            xml.append("<attach>" + attach + "</attach>");
        }
        if (deviceInfo != null) {
            xml.append("<device_info>" + deviceInfo + "</device_info>");
        }
        if (openId != null) {
            xml.append("<openid>" + openId + "</openid>");
        }
        if (productId != null) {
            xml.append("<product_id>" + productId + "</product_id>");
        }
        xml.append("<sign><![CDATA[" + sign + "]]></sign></xml>");
        String createOrderURL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
        String prepay_id = getPayNo(createOrderURL, xml.toString());
        if (StringUtils.isEmpty(prepay_id)) {
        	return null;
        }
        SortedMap<String, String> finalpackage = new TreeMap<String, String>();
        String timestamp = Sha1Util.getTimeStamp();
        String packages = "prepay_id=" + prepay_id;
        finalpackage.put("appId", SystemConfigurer.GROUP_PAY_APP_ID);
        finalpackage.put("timeStamp", timestamp);
        finalpackage.put("nonceStr", nonce_str);
        finalpackage.put("package", packages);
        finalpackage.put("signType", "MD5");
        String finalSign = TenpayUtil.createSign(finalpackage, SystemConfigurer.GROUP_PAY_PARTNER_KEY);
        finalpackage.put("paySign", finalSign);
        finalpackage.put("notifyUrl", notify_url);
        finalpackage.put("outTradeNo", out_trade_no);
        return finalpackage;
    }

    public static String getNonceStr() {
        String currTime = TenpayUtil.getCurrTime();
        String strTime = currTime.substring(8, currTime.length());
        String strRandom = TenpayUtil.buildRandom(4) + "";
        return strTime + strRandom;
    }

    /**
     * 订单号生成
     * <p>
     *     订单号生成规则：1位(支付渠道)+17位(年月日时分秒毫秒)+12位(user_id后面补随机数到12位)
     * </p>
     * @param partner 支付渠道
     * @return
     */
    public static String generateOutTradeNo(String partner, String lyyUserId) throws IndexOutOfBoundsException{
        String outTradeNo = partner + TenpayUtil.getCurrTMillisecond() + lyyUserId;
        if (outTradeNo.length() > OUT_TRADE_NO_MAX_LENGTH) {
            throw new IndexOutOfBoundsException("订单号长度超过了30");
        }
        int surplus = OUT_TRADE_NO_MAX_LENGTH - outTradeNo.length();
        if (surplus > 0) {
            outTradeNo += TenpayUtil.buildRandom(surplus);
        }
        return outTradeNo;
    }

    public static String getOutTradeno(String partner) {
        String currTime = TenpayUtil.getCurrTime();
        String strRandom = TenpayUtil.buildRandom(4) + "";
        return partner + currTime + strRandom;
    }


    public static String getMoney(String amount) {
        if (amount == null) {
            return "";
        }
        // 金额转化为分为单位
        String currency = amount.replaceAll("\\$|\\￥|\\,", "");  //处理包含, ￥ 或者$的金额
        int index = currency.indexOf(".");
        int length = currency.length();
        Long amLong = 0l;
        if (index == -1) {
            amLong = Long.valueOf(currency + "00");
        } else if (length - index >= 3) {
            amLong = Long.valueOf((currency.substring(0, index + 3)).replace(".", ""));
        } else if (length - index == 2) {
            amLong = Long.valueOf((currency.substring(0, index + 2)).replace(".", "") + 0);
        } else {
            amLong = Long.valueOf((currency.substring(0, index + 1)).replace(".", "") + "00");
        }
        return amLong.toString();
    }

    /**
     * 获取预支付id
     */
    public static String getPayNo(String url, String xmlParam) {
        String prepay_id = "";
        try {
            String returnMsg = MessageUtil.sendWeiXinMessage(url, "POST", xmlParam);
            if (returnMsg == null) {
                return prepay_id;
            }
            if (returnMsg.indexOf("FAIL") != -1) {
                logger.warn("xmlParam:" + xmlParam);
                logger.warn("getPayNo:" + returnMsg);
                return "";
            }
            Map map = WxUtil.doXMLParse(returnMsg);
            prepay_id = (String) map.get("prepay_id");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return prepay_id;
    }

    /**
     * 简单的订单号生成
     * 充值送礼订单用到
     * <p>
     *  订单号生成规则：1位(支付渠道)+17位(年月日时分秒毫秒)+3位(随机数)
     * </p>
     *
     * @return
     */
    public static String generateSimpleOutTradeNo(String partner) {
        return partner + TenpayUtil.getCurrTMillisecond() + TenpayUtil.buildRandom(3);
    }
}
