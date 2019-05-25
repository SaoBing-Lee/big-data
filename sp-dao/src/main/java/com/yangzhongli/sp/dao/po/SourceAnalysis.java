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
@Table(name="source_analysis")//设置数据库中表名字
public class SourceAnalysis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "select uuid()")
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
