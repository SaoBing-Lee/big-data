package com.yangzhongli.sp.service.dto.wechat;

import lombok.Data;

/**
 * @ClassName WxAuthTokenDTO
 * @descripetion TODO
 * @Author liyanbing
 * @Date 2019-03-26
 */
@Data
public class WxAuthTokenDTO {

    private String access_token;

    private String expires_in;

    private String errcode;

    private String errmsg;
}
