package com.yangzhongli.sp.service.bo;

import lombok.Data;

/**
 * @ClassName APPRole
 * @descripetion TODO
 * @Author liyanbing
 * @Date 2019-05-22
 */

@Data
public class EventAllVO {

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
