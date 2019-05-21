package com.yangzhongli.sp.dao.po;

import lombok.Data;
import org.apache.ibatis.annotations.Mapper;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Mapper
@Data
@Table(name="new_beauty_user")//设置数据库中表名字
public class WechatUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "select uuid()")
    private String id;
    //用户昵称
    private String nickName;

    private String openId;
    //用户头像
    private String avatarUrl;
    //用户性别（0=女，1=男）
    private Integer gender;
    private String phone;
    //国籍
    private String country;
    //省份
    private String province;
    //城市
    private String city;

    private Date createTime;

    private Date updateTime;
}