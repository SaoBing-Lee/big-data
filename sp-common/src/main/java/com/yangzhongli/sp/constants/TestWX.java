package com.yangzhongli.sp.constants;

import lombok.Data;

import java.io.Serializable;

@Data
public class TestWX implements Serializable {

    private static final long serialVersionUID = 1L;
    /** 商户账号appid*/
    public String mch_appid;
    /** 微信支付商户号*/
    public String mchid;
    /** 随机串*/
    public String nonce_str;
    /** 签名*/
    public String sign;
    /** 商户订单号*/
    public String partner_trade_no;
    /** 用户id*/
    public String openid;
    /** 是否校验用户姓名 NO_CHECK：不校验真实姓名  FORCE_CHECK：强校验真实姓名*/
    public String check_name;
    /** 金额 单位：分*/
    public Integer amount;
    /** 企业付款描述信息*/
    public String desc;
    /** ip地址*/
    public String spbill_create_ip;
}
