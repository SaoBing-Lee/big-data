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
public class SourceAnalysisVO {

    private String id;
    //应用ID
    private String appId;
    //场景id
    private String scenarioId;
    //渠道场景文字描述
    private String scenarioName;
    //日期
    private String date;
    //日访问次数
    private String visits;
    //访问人数
    private String accessPerson;
    //日访次数占比
    private String visitsAcc;
    //访问人数占比
    private String accessPersonAcc;

}
