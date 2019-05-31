package com.yangzhongli.sp.service.api;

import com.github.pagehelper.PageInfo;
import com.yangzhongli.sp.service.bo.CityVO;
import com.yangzhongli.sp.service.bo.ProvinceVO;
import com.yangzhongli.sp.service.bo.RegionalAnalysisVO;

import java.util.List;

public interface RegionalAnalysisService {
    /**
     * 根据应用id和日期 获取地图信息的--访问次数
     *
     * @param appId   应用id
     * @param strDate 开始时间
     * @param endDate 结束时间
     * @param type    (1=访问次数;2=访问人数;3=打开次数,4=新增人数)
     * @return
     */
    List<ProvinceVO> selectProvinceAmountList(String appId, String strDate, String endDate, int type);

    /**
     * 根据应用id和日期 获取省份列表
     *
     * @param appId   应用id
     * @param strDate 开始时间
     * @param endDate 结束时间
     * @return
     */
    PageInfo<ProvinceVO> selectProvinceList(Integer pageNum, Integer pageSize, String appId, String strDate, String endDate);

    /**
     * 根据应用id和日期 获取省份列表中的  市列表
     *
     * @param appId
     * @param strDate  开始时间
     * @param endDate  结束时间
     * @param province 省份
     * @return
     */
    List<ProvinceVO> selectCityList(String appId, String strDate, String endDate, String province);

    /**
     * 根据应用id、时间段、省份名字，查询当前省 省份趋势图
     *
     * @param appId
     * @param strDate  开始时间
     * @param endDate  结束时间
     * @param province 省份
     * @param type     (1=访问次数;2=访问人数;3=打开次数,4=新增人数)
     * @return
     */
    List<ProvinceVO> selectProAmountList(String appId, String strDate, String endDate, String province, int type);

    /**
     * 根据应用id、时间段、省份名字，查询当前省 省份详细信息列表
     *
     * @param appId
     * @param strDate  开始时间
     * @param endDate  结束时间
     * @param province 省份
     * @return
     */
    PageInfo<ProvinceVO> selectProvinceDetailList(Integer pageNum, Integer pageSize, String appId, String strDate, String endDate, String province);

    /**
     * 根据应用id、时间段、市名字，查询当前省 时间段信息 市趋势图
     *
     * @param appId
     * @param strDate 开始时间
     * @param endDate 结束时间
     * @param city    省份
     * @param type    (1=访问次数;2=访问人数;3=打开次数,4=新增人数)
     * @return
     */
    List<CityVO> selectCityDetailAmountList(String appId, String strDate, String endDate, String city, int type);

    /**
     * 市详情数据列表  根据应用id、时间段、市名字，查询当前省 时间段信息
     *
     * @param appId
     * @param strDate 开始时间
     * @param endDate 结束时间
     * @param city    省份
     * @return
     */
    PageInfo<RegionalAnalysisVO> selectCityDetailList(Integer pageNum, Integer pageSize, String appId, String strDate, String endDate, String city);


}
