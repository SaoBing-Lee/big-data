package com.yangzhongli.sp.service.bo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class UserVO {
    private String id;
    //用户名称
    private String name;
    //密码
    private String password;
    //超级管理员=1，普通管理员=0
    private Integer type;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
    //添加用户时使用
    private List<UserAppRoleVO> userAppRoleVOS;
    //查询编辑用户信息和权限时使用
    private List<AppRoleVO> appRoleVOList;
    //查询用户列表时使用
    private String appRoleNames;

}