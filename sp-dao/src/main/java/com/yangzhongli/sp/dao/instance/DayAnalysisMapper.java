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
     * 获取应用分析的实时数据的详情数据
     *
     * @param appId         应用ID
     * @return
     */
    List<DayAnalysis> selectDayList(@Param("appId") String appId);


}