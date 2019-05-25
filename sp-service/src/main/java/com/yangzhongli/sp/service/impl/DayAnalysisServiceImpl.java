package com.yangzhongli.sp.service.impl;

import com.github.pagehelper.PageInfo;
import com.yangzhongli.sp.dao.instance.DayAnalysisMapper;
import com.yangzhongli.sp.dao.po.DayAnalysis;
import com.yangzhongli.sp.service.api.DayAnalysisService;
import com.yangzhongli.sp.service.bo.DayAnalysisVO;
import com.yangzhongli.sp.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @ClassName DayAnalysisServiceImpl
 * @descripetion TODO
 * @Author liyanbing
 * @Date 2019-05-25
 */
public class DayAnalysisServiceImpl implements DayAnalysisService {

    @Autowired
    private DayAnalysisMapper dayAnalysisMapper;

    @Override
    public PageInfo<DayAnalysisVO> selectDayList(Integer pageNum, Integer pageSize, String appId) {
        PageUtils.startPage(pageNum, pageSize);
        List<DayAnalysis> list = dayAnalysisMapper.selectDayList(appId);
        return PageUtils.getPageInfo(list, DayAnalysisVO.class);
    }
}
