package com.yangzhongli.sp.service.api;

import com.github.pagehelper.PageInfo;
import com.yangzhongli.sp.service.bo.DeviceAnalysisVO;
import com.yangzhongli.sp.service.bo.DeviceVO;

import java.util.List;

/**
 * @ClassName DeviceService
 * @descripetion TODO
 * @Author liyanbing
 * @Date 2019-05-28
 */
public interface DeviceService {

    /**
     * 根据应用id、时间段，查询应用机型分析 趋势图
     *
     * @param appId
     * @param strDate 开始时间
     * @param endDate 结束时间
     * @param type    (1=访问次数;2=访问人数;3=打开次数,4=新增人数)
     * @return
     */
    List<DeviceVO> selectDevice(String appId, String strDate, String endDate, int type);


    /**
     * 根据应用id、时间段，查询应用机型分析列表
     *
     * @param appId
     * @param strDate 开始时间
     * @param endDate 结束时间
     * @return
     */
    PageInfo<DeviceVO> selectDeviceList(Integer pageNum, Integer pageSize,String appId, String strDate, String endDate);


    /**
     * 根据应用id、时间段，查询应用机型分析详细信息 趋势图
     *
     * @param appId
     * @param strDate
     * @param endDate
     * @param device 手机型号
     * @param type    (1=访问次数;2=访问人数;3=打开次数,4=新增人数)
     * @return
     */
    List<DeviceAnalysisVO> selectDeviceDetail(String appId, String strDate, String endDate, String device, int type);


    /**
     * 根据应用id、时间段，查询应用机型分析详细信息 列表
     *
     * @param appId
     * @param strDate
     * @param endDate
     * @param device 手机型号
     * @return
     */
    PageInfo<DeviceAnalysisVO> selectDeviceDetailList(Integer pageNum , Integer pageSize, String appId, String strDate, String endDate, String device);

}
