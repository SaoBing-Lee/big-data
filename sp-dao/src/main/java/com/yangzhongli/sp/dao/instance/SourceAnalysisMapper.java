package com.yangzhongli.sp.dao.instance;

import com.yangzhongli.sp.dao.base.MyMapper;
import com.yangzhongli.sp.dao.dto.SourceDTO;
import com.yangzhongli.sp.dao.po.SourceAnalysis;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface SourceAnalysisMapper extends MyMapper<SourceAnalysis> {

    /**
     * 根据应用id、时间段，查询应用来源分析 趋势图的时间集合
     *
     * @param appId
     * @param strDate 开始时间
     * @param endDate 结束时间
     * @return
     */
    List<String> selectSourceDateList(@Param("appId") String appId, @Param("strDate") String strDate, @Param("endDate") String endDate);

    /**
     * 根据应用id、selectSourceDateList获取的时间，查询应用来源分析 趋势图的集合
     *
     * @param appId
     * @param checkDay 时间
     * @return
     */
    List<SourceAnalysis> selectSource(@Param("appId") String appId, @Param("checkDay") String checkDay);

    /**
     * 根据应用id、时间段，查询应用来源分析 列表
     *
     * @param appId
     * @param strDate 开始时间
     * @param endDate 结束时间
     * @return
     */
    List<SourceDTO> selectSourceList(@Param("appId") String appId, @Param("strDate") String strDate, @Param("endDate") String endDate);


}