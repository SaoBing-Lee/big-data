package com.yangzhongli.sp.service.api;

import com.github.pagehelper.PageInfo;
import com.yangzhongli.sp.dao.po.UserSystem;
import com.yangzhongli.sp.service.bo.SystemVO;
import com.yangzhongli.sp.service.bo.UserSystemVO;

import java.util.List;

public interface SystemService {


    /**
     * 根据应用id、时间段,统计分析终端 趋势图
     *
     * @param appId
     * @param strDate
     * @param endDate
     * @param type    1=访问次数;2=访问人数;3=打开次数
     * @return
     */
    List<SystemVO> selectSystem(String appId, String strDate, String endDate, int type);


    /**
     * 根据应用id、时间段,统计分析终端 详细数据
     *
     * @param appId
     * @param strDate
     * @param endDate
     * @return
     */
    List<SystemVO> selectSystemList(String appId, String strDate, String endDate);


    /**
     * 根据应用id、系统名称、时间段,统计分析终端详细信息 趋势图
     *
     * @param appId
     * @param strDate
     * @param endDate
     * @param systemPhone 手机系统名称
     * @param type        1=访问次数;2=访问人数;3=打开次数,4=新增人数
     * @return
     */
    List<UserSystemVO> selectSystemDetail(String appId, String strDate, String endDate, String systemPhone, int type);


    /**
     * 根据应用id、系统名称、时间段,统计分析终端详细信息 列表
     *
     * @param appId
     * @param strDate
     * @param endDate
     * @param systemPhone 手机系统名称
     * @return
     */
    PageInfo<UserSystemVO> selectSystemDetailList(Integer pageNum, Integer pageSize, String appId, String strDate, String endDate, String systemPhone);


}
