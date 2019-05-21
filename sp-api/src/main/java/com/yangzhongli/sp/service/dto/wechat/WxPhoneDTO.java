package com.yangzhongli.sp.service.dto.wechat;

import lombok.Data;

/**
 * @ClassName WxPhoneDTO
 * @descripetion 获取用户电话号码信息 --微信返回后解析的实体类
 * @Author liyanbing
 * @Date 2019-03-25
 */
@Data
public class WxPhoneDTO {

    //用户绑定的手机号（国外手机号会有区号）
    private String phoneNumber;
    //没有区号的手机号
    private String purePhoneNumber;
    //区号
    private String countryCode;
}
