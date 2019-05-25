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
public class CustomEventVO {
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
