package com.yangzhongli.sp.dao.instance;

import com.yangzhongli.sp.dao.base.MyMapper;
import com.yangzhongli.sp.dao.dto.DeviceDTO;
import com.yangzhongli.sp.dao.po.DeviceAnalysis;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface DeviceAnalysisMapper extends MyMapper<DeviceAnalysis> {


    /**
     * 根据应用id、时间段，查询应用机型分析 趋势图和列表
     *
     * @param appId
     * @param strDate 开始时间
     * @param endDate 结束时间
     * @return
     */
    List<DeviceDTO> selectDevice(@Param("appId") String appId, @Param("strDate") String strDate, @Param("endDate") String endDate);

    /**
     * 根据应用id、时间段，查询应用机型分析详细信息 趋势图
     *
     * @param appId
     * @param strDate
     * @param endDate
     * @param device 手机型号
     * @return
     */
    List<DeviceAnalysis> selectDeviceDetail(@Param("appId") String appId, @Param("strDate") String strDate, @Param("endDate") String endDate, @Param("device") String device);


    /**
     * 根据应用id、时间段，查询应用机型分析详细信息 列表
     *
     * @param appId
     * @param strDate
     * @param endDate
     * @param device 手机型号
     * @return
     */
    List<DeviceAnalysis> selectDeviceDetailList(@Param("appId") String appId, @Param("strDate") String strDate, @Param("endDate") String endDate, @Param("device") String device);

}