package com.yangzhongli.sp.service.bo;

import lombok.Data;

/**
 * @ClassName ShareListVO
 * @descripetion TODO
 * @Author liyanbing
 * @Date 2019-04-04
 */
@Data
public class ShareListVO {

    private String id;

    private String userId;

    private String name;
    //分享图片
    private String shareUrl;
    //点赞总数
    private Long likesAmount;
    //0参加用户，1案例展示
    private Integer type;
    //浏览量
    private Long readAmount;
    //判断是否本人(0不是，1是)
    private int isLikes;
}
