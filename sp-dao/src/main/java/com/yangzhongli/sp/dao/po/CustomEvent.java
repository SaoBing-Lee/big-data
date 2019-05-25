package com.yangzhongli.sp.dao.po;

import lombok.Data;
import org.apache.ibatis.annotations.Mapper;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @ClassName APPRole
 * @descripetion TODO
 * @Author liyanbing
 * @Date 2019-05-22
 */

@Mapper
@Data
@Table(name="custom_event")//设置数据库中表名字
public class CustomEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "select uuid()")
    private String id;
    //应用ID
    private String appId;
    //事件ID
    private String eventId;
    //事件名称
    private String eventName;
    //事件编码
    private String eventCode;

}
