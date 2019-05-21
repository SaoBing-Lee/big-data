package com.yangzhongli.sp.service.dto.wechat;

import lombok.Data;

@Data
public class WxUserInfo {

    private String encryptedData;
    private String errMsg;
    private String iv;
    private String rawData;
    private String signature;

    private WxUserInfoDetail info;

}