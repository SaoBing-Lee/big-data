package com.yangzhongli.sp.dao.po;

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

@Mapper
@Data
@Table(name="device_analysis")//设置数据库中表名字
public class DeviceAnalysis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "select uuid()")
    private String id;
    //应用ID
    private String appId;
    //日期
    private String date;
    //日访问次数
    private String visits;
    //设备型号
    private String device;
    //日打开次数
    private String opens;
    //日访问人数
    private String accessPerson;
    //日新增人数
    private String newPerson;


}
