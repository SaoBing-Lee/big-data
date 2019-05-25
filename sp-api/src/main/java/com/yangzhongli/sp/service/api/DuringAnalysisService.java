package com.yangzhongli.sp.service.api;

import com.yangzhongli.sp.service.bo.DuringAnalysisVO;

import java.util.List;
import java.util.Map;

/**
 * @ClassName DuringAnalysisService
 * @descripetion 时段访问总表
 * @Author liyanbing
 * @Date 2019-05-25
 */
public interface DuringAnalysisService {

    /**
     * 获取应用分析的实时数据的趋势图数据
     *
     * @param appId 应用ID
     * @return 返回map 将不同时间段的数据分类
     */
    Map<String, Object> selectDuringAnalysis(String appId);

    /**
     * 根据时间动态 获取应用分析的 历史数据 的趋势图数据
     *
     * @param appId     应用ID
     * @param beforeDay 查询几天前的数据（0=今天，，7=7天前，14=14天前，30=30天前）
     * @return
     */
    List<DuringAnalysisVO> selectHistoryDuring(String appId, int beforeDay);

}
