package com.yangzhongli.sp.service.bo;

import lombok.Data;

/**
 * @ClassName LikeDemoDTO
 * @descripetion TODO
 * @Author liyanbing
 * @Date 2019-04-15
 */
@Data
public class LikeDemoPicturesVO {


    private String id;
    private String name;
    //'宣言'
    private String declaration;
    //轮播图片手术前
    private String headPictureFont;
    //轮播图片手术前手术后
    private String headPictureNow;
}
