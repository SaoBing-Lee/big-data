package com.yangzhongli.sp.service.bo;

import lombok.Data;

/**
 * @ClassName ProvinceDTO
 * @descripetion TODO
 * @Author liyanbing
 * @Date 2019-05-22
 */

@Data
public class ProvinceVO {

    //省名
    private String province;
    //市名
    private String city;
    //日期
    private String date;
    //日访问次数
    private String amountVisits;
    //日打开次数
    private String amountOpens;
    //日访问人数
    private String amountAccessPerson;
    //日新增人数
    private String amountNewPerson;

}
