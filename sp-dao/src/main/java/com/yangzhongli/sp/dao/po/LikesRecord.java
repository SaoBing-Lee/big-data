package com.yangzhongli.sp.dao.po;

import lombok.Data;
import org.apache.ibatis.annotations.Mapper;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Mapper
@Data
@Table(name = "new_beauty_likes_record")//设置数据库中表名字
public class LikesRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "select uuid()")
    private String id;

    private String userId;

    private String contestantId;

    private Date createTime;

    private Date updateTime;

}