package com.yangzhongli.sp.service.api;

import com.github.pagehelper.PageInfo;
import com.yangzhongli.sp.service.bo.SourceAnalysisVO;
import com.yangzhongli.sp.service.bo.SourceVO;

import java.util.List;

/**
 * @ClassName SourceAnalysisService
 * @descripetion TODO
 * @Author liyanbing
 * @Date 2019-05-30
 */
public interface SourceAnalysisService {


    /**
     * 根据应用id、selectSourceDateList获取的时间，查询应用来源分析 趋势图的集合
     *
     * @param appId
     * @param strDate 开始时间
     * @param endDate 结束时间
     * @return
     */
    List<SourceAnalysisVO> selectSource(String appId, String strDate, String endDate);

    /**
     * 根据应用id、时间段，查询应用来源分析 列表
     *
     * @param appId
     * @param strDate 开始时间
     * @param endDate 结束时间
     * @return
     */
    PageInfo<SourceVO> selectSourceList(Integer pageNum, Integer pageSize, String appId, String strDate, String endDate);

}
