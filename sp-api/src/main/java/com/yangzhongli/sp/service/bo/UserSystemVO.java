package com.yangzhongli.sp.service.bo;

import lombok.Data;

/**
 * @ClassName APPRole
 * @descripetion TODO
 * @Author liyanbing
 * @Date 2019-05-22
 */

@Data
public class UserSystemVO {

    private String id;
    //应用ID
    private String appId;
    //日期
    private String date;
    //日访问次数
    private String visits;
    //日打开次数
    private String opens;
    //日访问人数
    private String accessPerson;


}
