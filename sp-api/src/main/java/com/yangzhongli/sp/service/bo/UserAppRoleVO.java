package com.yangzhongli.sp.service.bo;

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
@Data
public class UserAppRoleVO {

    private String id;
    //用户
    private String userId;
    //权限
    private String appRoleId;
}
