package com.yangzhongli.sp.service.dto.wechat;

import lombok.Data;

@Data
public class WxAuthDTO {

    private String openid;

    private String unionid;

    private String session_key;

    private String errcode;

    private String errmsg;

}
