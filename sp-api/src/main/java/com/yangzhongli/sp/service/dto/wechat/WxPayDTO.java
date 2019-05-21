package com.yangzhongli.sp.service.dto.wechat;

import lombok.Data;

@Data
public class WxPayDTO {
    private String deviceInfo;
    private String totalFee;
    private String spbillCreateIp;
    private String body;
    private String detail;
    private String attach;
    private String outTradeNo;
    private String notifyUrl;
    private String openId;
    private String tradeType;
    private String productId;
    private String isMinipg;
    private String subAppid;
}