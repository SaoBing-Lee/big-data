package com.yangzhongli.sp.dao.dto;

import lombok.Data;

/**
 * @ClassName NetworkDTO
 * @descripetion TODO
 * @Author liyanbing
 * @Date 2019-05-22
 */

@Data
public class NetworkDTO {

    //网络
    private String network;
    //日访问次数
    private String amountVisits;
    //日打开次数
    private String amountOpens;
    //日访问人数
    private String amountAccessPerson;
    //日新增人数
    private String amountNewPerson;

}
