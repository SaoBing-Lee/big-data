package com.yangzhongli.sp.dao.dto;

import lombok.Data;

/**
 * @ClassName ShareDTO
 * @descripetion 列表查询的实体
 * @Author liyanbing
 * @Date 2019-04-04
 */
@Data
public class ContestantDTO {

    private String id;

    private String userId;

    private String name;
    //电话
    private String phone;
    //年龄
    private Integer age;

    private Integer code;
    //'分享图片'
    private String infoPicture;
    private String headPicture;
    //'点赞总数'
    private Long likesAmount;
    //'浏览量'
    private Long readAmount;
    //'职业'
    private String occupation;
    //整容项目
    private String plasticProject;
    //'宣言'
    private String declaration;
    //判断是否本人(0不是，1是)
    private int isLikes;
    //点赞记录id
    private String likesRecordId;
}
