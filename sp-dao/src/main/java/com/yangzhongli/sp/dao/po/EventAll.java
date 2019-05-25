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
@Table(name="custom_event")//设置数据库中表名字
public class EventAll {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "select uuid()")
    private String id;
    //应用ID
    private String appId;
    //日期
    private String date;
    //事件名称
    private String eventName;
    //事件类型(分享=1,点击=2,跳转=3)
    private String eventType;
    //触发次数
    private String triggerNum;
    //触发人数
    private String triggerPerson;
    //界面平均访问时长
    private String visitsMean;
}
