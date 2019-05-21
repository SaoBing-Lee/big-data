package com.yangzhongli.sp.service.impl;

import com.yangzhongli.sp.dao.instance.RuleMapper;
import com.yangzhongli.sp.dao.po.Rule;
import com.yangzhongli.sp.service.api.RuleService;
import com.yangzhongli.sp.service.bo.RuleVO;
import com.yangzhongli.sp.utils.ConverterUtil;
import com.yangzhongli.sp.utils.IdUtils;
import com.yangzhongli.sp.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @ClassName RuleServiceImpl
 * @descripetion TODO
 * @Author liyanbing
 * @Date 2019-05-07
 */
@Slf4j
@Service
public class RuleServiceImpl implements RuleService {
    @Autowired
    private RuleMapper ruleMapper;

    @Override
    public int save(RuleVO ruleVO) {
        if (null != ruleVO) {
            ruleVO.setUpdateTime(new Date());
            if (StringUtils.isEmpty(ruleVO.getId())) {
                ruleVO.setCreateTime(new Date());
                ruleVO.setId(IdUtils.creatUUID());
                return ruleMapper.insert(ConverterUtil.convert(Rule.class, ruleVO));
            } else {
                return ruleMapper.updateByPrimaryKeySelective(ConverterUtil.convert(Rule.class, ruleVO));
            }
        }
        return 0;
    }

    @Override
    public List<RuleVO> getRule() {
        List<Rule> list = ruleMapper.selectAll();
        return ConverterUtil.convert(RuleVO.class, list);
    }

    @Override
    public RuleVO ruleEdit(String id) {
        Rule rule = ruleMapper.selectByPrimaryKey(id);
        return ConverterUtil.convert(RuleVO.class,rule);
    }
}
