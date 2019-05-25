package com.yangzhongli.sp.dao.po;

import lombok.Data;
import org.apache.ibatis.annotations.Mapper;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @ClassName UserAppRole
 * @descripetion TODO
 * @Author liyanbing
 * @Date 2019-05-22
 */
@Mapper
@Data
@Table(name="user_app_role")//设置数据库中表名字
public class UserAppRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "select uuid()")
    private String id;
    //用户名称
    private String userId;
    //密码
    private String appRoleId;
}
