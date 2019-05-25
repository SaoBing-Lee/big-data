package com.yangzhongli.sp.dao.po;

import lombok.Data;
import org.apache.ibatis.annotations.Mapper;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @ClassName APPRole
 * @descripetion TODO
 * @Author liyanbing
 * @Date 2019-05-22
 */

@Mapper
@Data
@Table(name="app_role")//设置数据库中表名字
public class AppRole {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "select uuid()")
    private String id;
    //用户名称
    private String name;
    //应用ID
    private String appId;
    //图标
    private String picture;

    private Date createTime;

    private Date updateTime;
}
