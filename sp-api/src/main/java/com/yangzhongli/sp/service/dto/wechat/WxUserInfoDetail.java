package com.yangzhongli.sp.service.dto.wechat;

import lombok.Data;

@Data
public class WxUserInfoDetail {

    private String nickName;
    private String avatarUrl;
    private String gender;
    private String city;
    private String province;
    private String country;
    private String language;

    private String unionId;
    private String openId;

}