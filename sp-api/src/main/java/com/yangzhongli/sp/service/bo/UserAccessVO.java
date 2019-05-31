package com.yangzhongli.sp.service.bo;

import lombok.Data;

/**
 * @ClassName APPRole
 * @descripetion TODO
 * @Author liyanbing
 * @Date 2019-05-22
 */

@Data
public class UserAccessVO {


    private String id;
    //应用ID
    private String appId;
    //日访问次数
    private String visits;
    //日打开次数
    private String opens;
    //日访问人数
    private String accessPerson;
    //用户总数
    private String userNum;
    //新增访问次数
    private String newVisits;
    //新增打开次数
    private String newOpens;
    //新增访问人数
    private String newAccessPerson;
    //新增用户数
    private String newUserNum;

}
