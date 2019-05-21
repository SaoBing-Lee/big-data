package com.yangzhongli.sp.service.api;

import com.yangzhongli.sp.service.bo.RuleVO;

import java.util.List;

public interface RuleService {

    /**
     * 添加规则
     * @param ruleVO
     */
   int save(RuleVO ruleVO);

    /**
     * 获取规则
     * @return
     */
    List<RuleVO> getRule();

    RuleVO ruleEdit(String id);

}
