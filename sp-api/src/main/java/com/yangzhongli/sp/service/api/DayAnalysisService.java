package com.yangzhongli.sp.service.api;

import com.github.pagehelper.PageInfo;
import com.yangzhongli.sp.service.bo.DayAnalysisVO;

public interface DayAnalysisService {

    /**
     * 获取应用分析的实时数据的详情数据--分页
     *
     * @param appId 应用ID
     * @return
     */
    PageInfo<DayAnalysisVO> selectDayList(Integer pageNum , Integer pageSize, String appId);
}
