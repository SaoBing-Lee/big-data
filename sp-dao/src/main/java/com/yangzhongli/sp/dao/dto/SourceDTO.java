package com.yangzhongli.sp.dao.dto;

import lombok.Data;

/**
 * @ClassName SourceDTO
 * @descripetion TODO
 * @Author liyanbing
 * @Date 2019-05-22
 */

@Data
public class SourceDTO {

    //来源id
    private String scenarioId;
    //来源名称
    private String scenarioName;
    //日访问次数
    private String amountVisits;
    //日访问人数
    private String amountAccessPerson;

}
