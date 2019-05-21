package com.yangzhongli.sp.service.bo;

import lombok.Data;
import org.apache.ibatis.annotations.Mapper;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
public class ContestantVO {

    private String id;

    private String userId;
    //头图
    private String headPicture;
    //详情图片
    private String infoPicture;
    private String [] infoPictureStr;

    private String name;
    //电话
    private String phone;
    //年龄
    private Integer age;
    //用户编号
    private Integer code;
    //整容项目
    private String plasticProject;
    //'职业'
    private String occupation;
    //'宣言'
    private String declaration;
    //'点赞总数'
    private Long likesAmount;
    //'浏览量'
    private Long readAmount;

    private Date createTime;

    private Date updateTime;
    //判断是否本人(0不是，1是)
    private int isLikes;
    //点赞记录id
    private String likesRecordId;

}