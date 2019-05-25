package com.yangzhongli.sp.dao.instance;

import com.yangzhongli.sp.dao.base.MyMapper;
import com.yangzhongli.sp.dao.po.DuringAnalysis;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface DuringAnalysisMapper extends MyMapper<DuringAnalysis> {

    /**
     * 获取应用分析的实时数据的趋势图数据
     *
     * @param appId         应用ID
     * @param dateToday     时间今天
     * @param dateYesterday 昨天
     * @param dateSevenDay  7天前
     * @return
     */
    List<DuringAnalysis> selectDuringAnalysis(@Param("appId") String appId, @Param("dateToday") String dateToday, @Param("dateYesterday") String dateYesterday, @Param("dateSevenDay") String dateSevenDay);

    /**
     * 根据时间动态 获取应用分析的 历史数据 的趋势图数据
     *
     * @param appId         应用ID
     * @return
     */
    List<DuringAnalysis> selectHistoryDuring(@Param("appId") String appId,@Param("checkDay") String checkDay);
}