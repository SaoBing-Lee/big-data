package com.yangzhongli.sp.dao.dto;

import lombok.Data;

import java.util.Date;

@Data
public class UserDTO {
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

    private Date createTime;

    private Date updateTime;

    private String address;

    private String addressDetail;

    private String phone;

    //订单数量
    private Integer orderCount;

    //预约参观数量
    private Integer viewCount;

    //预约吃饭数量
    private Integer eatCount;


}