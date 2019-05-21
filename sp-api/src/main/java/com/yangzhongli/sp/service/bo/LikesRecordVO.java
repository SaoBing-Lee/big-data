package com.yangzhongli.sp.service.bo;

import lombok.Data;
import org.apache.ibatis.annotations.Mapper;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
public class LikesRecordVO {

    private String id;

    private String userId;

    private String ContestantId;

    private Date createTime;

    private Date updateTime;

}