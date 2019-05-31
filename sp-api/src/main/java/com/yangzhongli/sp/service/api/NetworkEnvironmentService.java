package com.yangzhongli.sp.service.api;

import com.github.pagehelper.PageInfo;
import com.yangzhongli.sp.service.bo.NetworkEnvironmentVO;
import com.yangzhongli.sp.service.bo.NetworkVO;

import java.util.List;

public interface NetworkEnvironmentService {


    /**
     * 根据应用id、时间段，查询应用网络环境 趋势图
     *
     * @param appId
     * @param strDate 开始时间
     * @param endDate 结束时间
     * @param type    (1=访问次数;2=访问人数;3=打开次数,4=新增人数)
     * @return
     */
    List<NetworkVO> selectNetwork(String appId, String strDate, String endDate, int type);


    /**
     * 根据应用id、时间段，查询应用网络环境 列表
     *
     * @param appId
     * @param strDate 开始时间
     * @param endDate 结束时间
     * @return
     */
    List<NetworkVO> selectNetworkList(String appId, String strDate, String endDate);


    /**
     * 根据应用id、时间段，查询应用网络环境详细信息 趋势图
     *
     * @param appId
     * @param strDate
     * @param endDate
     * @param network 手机网络
     * @param type    (1=访问次数;2=访问人数;3=打开次数,4=新增人数)
     * @return
     */
    List<NetworkEnvironmentVO> selectNetworkDetail(String appId, String strDate, String endDate, String network, int type);


    /**
     * 根据应用id、时间段，查询应用网络环境详细信息 列表
     *
     * @param appId
     * @param strDate
     * @param endDate
     * @param network 手机网络
     * @return
     */
    PageInfo<NetworkEnvironmentVO> selectNetworkDetailList(Integer pageNum , Integer pageSize,String appId, String strDate, String endDate, String network);

}
