package com.yangzhongli.sp.dao.instance;

import com.yangzhongli.sp.dao.base.MyMapper;
import com.yangzhongli.sp.dao.po.DayAnalysis;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface DayAnalysisMapper extends MyMapper<DayAnalysis> {

    /**
     * 获取应用分析的历史数据的趋势图数据
     *
     * @param appId         应用ID
     * @return
     */
    List<DayAnalysis> selectDay(@Param("appId") String appId,@Param("strDate") String strDate, @Param("endDate") String endDate);


    /**
     * 获取应用分析的历史数据的详情和趋势图数据
     *
     * @param appId         应用ID
     * @return
     */
    List<DayAnalysis> selectDayList(@Param("appId") String appId,@Param("strDate") String strDate, @Param("endDate") String endDate);


}