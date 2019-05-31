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
     * @param type  (1=访问次数;2=访问人数;3=打开次数,4=新增人数,5=平均在线时长)
     * @return 返回map 将不同时间段的数据分类
     */
    Map<String, Object> selectDuringAnalysis(String appId, int type);

    /**
     * 根据当天时间 获取应用分析的 实时数据 的详情数据
     *
     * @param appId 应用ID
     * @return
     */
    List<DuringAnalysisVO> selectDuringAnalysisDetail(String appId);


    /**
     * 根据时间动态 获取应用分析的 历史数据 的趋势图数据
     *
     * @param appId   应用ID
     * @param strDate 开始时间
     * @param endDate 结束时间
     * @param type    (1=访问次数;2=访问人数;3=打开次数,4=新增人数,5=平均在线时长)
     * @return
     */
    List<String> selectHistoryDuring(String appId, String strDate, String endDate, int type);

}
