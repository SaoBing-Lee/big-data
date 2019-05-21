package com.yangzhongli.sp.config;

import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.service.impl.WxPayServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WechatPayConfig {
    @Bean
    @Autowired
    public WxPayService wxPayService(WxPayConfig wxPayConfig){
        WxPayService wxPayService = new WxPayServiceImpl();
        wxPayService.setConfig(wxPayConfig);
        return wxPayService;
    }

    @ConfigurationProperties(prefix = "pay.wechat")
    @Bean
    public WxPayConfig wxPayConfig(){
        return new WxPayConfig();
    }

}
