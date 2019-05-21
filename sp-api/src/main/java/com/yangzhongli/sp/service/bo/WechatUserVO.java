package com.yangzhongli.sp.service.bo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class WechatUserVO {
    private String id;
    //用户昵称
    private String nickName;

    private String openId;
    //用户头像
    private String avatarUrl;
    //用户性别（0=女，1=男）
    private Integer gender;
    //国籍
    private String country;
    //省份
    private String province;
    //城市
    private String city;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    private String address;

    private String addressDetail;

    private String phone;

}