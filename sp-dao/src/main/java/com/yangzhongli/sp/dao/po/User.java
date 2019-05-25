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
@Table(name="user")//设置数据库中表名字
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "select uuid()")
    private String id;
    //用户名称
    private String name;
    //密码
    private String password;
    //超级管理员=1，普通管理员=0
    private Integer type;

    private Date createTime;

    private Date updateTime;
}