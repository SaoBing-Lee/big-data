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
public class DuringAnalysisVO {

    private String id;
    //应用ID
    private String appId;
    //日期
    private String date;
    //访问时段
    private String time;
    //日访问次数
    private String visits;
    //日打开次数
    private String opens;
    //日访问人数
    private String accessPerson;
    //日新增人数
    private String newPerson;
    //日平均在线时长
    private String onlineMean;


}
