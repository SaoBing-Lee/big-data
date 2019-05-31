package com.yangzhongli.sp.dao.dto;

import lombok.Data;

/**
 * @ClassName SystemDTO
 * @descripetion TODO
 * @Author liyanbing
 * @Date 2019-05-22
 */

@Data
public class SystemDTO {

    //手机系统
    private String system;
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
