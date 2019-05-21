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
@Table(name = "new_beauty_contestant")//设置数据库中表名字
public class Contestant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "select uuid()")
    private String id;

    private String userId;
    //头图
    private String headPicture;
    //详情图片
    private String infoPicture;
    //年龄
    private Integer age;
    //用户编号
    private Integer code;

    private String name;
    //电话
    private String phone;
    //'职业'
    private String occupation;
    //整容项目
    private String plasticProject;
    //'宣言'
    private String declaration;
    //'点赞总数'
    private Long likesAmount;
    //'浏览量'
    private Long readAmount;

    private Date createTime;

    private Date updateTime;

}