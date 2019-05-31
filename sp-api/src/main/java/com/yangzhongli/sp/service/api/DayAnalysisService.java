package com.yangzhongli.sp.service.api;

import com.github.pagehelper.PageInfo;
import com.yangzhongli.sp.service.bo.DayAnalysisVO;

import java.util.List;
import java.util.Map;

public interface DayAnalysisService {

    /**
     * 获取应用分析的历史数据的趋势图数据
     *
     * @param appId   应用ID
     * @param strDate 开始时间
     * @param endDate 结束时间
     * @param type    (1=访问次数;2=访问人数;3=打开次数,4=新增人数)
     * @return
     */
    Map<String, Object>  selectDay(String appId, String strDate, String endDate, int type);

    /**
     * 获取应用分析的历史数据的详情数据--分页
     *
     * @param appId 应用ID
     * @return
     */
    PageInfo<DayAnalysisVO> selectDayList(Integer pageNum, Integer pageSize, String appId, String strDate, String endDate);

    /**
     * 获取应用分析的历史数据的详情数据--csv导出
     *
     * @param appId 应用ID
     * @return
     */
    List<DayAnalysisVO> selectCsvDayList(String appId, String strDate, String endDate);
}
