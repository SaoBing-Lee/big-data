package com.yangzhongli.sp.service.bo;

import lombok.Data;
import org.apache.ibatis.annotations.Mapper;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @ClassName APPRole
 * @descripetion TODO
 * @Author liyanbing
 * @Date 2019-05-22
 */

@Data
public class NetworkEnvironmentVO {

    private String id;
    //应用ID
    private String appId;
    //日期
    private String date;
    //网络环境
    private String network;
    //日访问次数
    private String visits;
    //日打开次数
    private String opens;
    //日访问人数
    private String accessPerson;
    //日新增人数
    private String newPerson;
    //访问人数占比
    private String visitsAcc;
    //打开次数占比
    private String opensAcc;
    //访问人数占比
    private String accessPersonAcc;
    //新增人数占比
    private String newPersonAcc;


}
