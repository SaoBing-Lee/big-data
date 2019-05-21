package com.yangzhongli.sp.service.bo;

import lombok.Data;
import org.apache.ibatis.annotations.Mapper;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @ClassName Rule
 * @descripetion TODO
 * @Author liyanbing
 * @Date 2019-05-07
 */
@Data
public class RuleVO {

    private String id;
    //奖励
    private String reward;
    //'参与方式'
    private String participation;
    //条件
    private String conditions;
    //'投票规则'
    private String voteRule;
    //'奖励规则'
    private String rewardRule;
    //活动公布时间地点
    private String promulgate;

    private String strTime;

    private String endTime;

    private Date createTime;

    private Date updateTime;
}
