package com.yangzhongli.sp.service.bo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import tk.mybatis.mapper.annotation.Order;

import java.util.Date;

/**
 * @ClassName AppRoleVO
 * @descripetion TODO
 * @Author liyanbing
 * @Date 2019-05-22
 */
@Data
public class AppRoleVO {

    private String id;
    //用户名称
    private String name;
    //应用ID
    private String appId;
    //图标
    private String picture;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Order("desc")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
}
