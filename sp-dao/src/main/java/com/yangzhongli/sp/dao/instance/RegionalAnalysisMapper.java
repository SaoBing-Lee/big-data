package com.yangzhongli.sp.dao.instance;

import com.yangzhongli.sp.dao.base.MyMapper;
import com.yangzhongli.sp.dao.dto.ProvinceDTO;
import com.yangzhongli.sp.dao.po.RegionalAnalysis;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface RegionalAnalysisMapper extends MyMapper<RegionalAnalysis> {

    /**
     * 根据应用id和日期 获取地图信息的--访问次数
     *
     * @param appId   应用id
     * @param strDate 开始时间
     * @param endDate 结束时间
     * @return
     */
    List<ProvinceDTO> selectProvinceAmountVisits(@Param("appId") String appId, @Param("strDate") String strDate, @Param("endDate") String endDate);

    /**
     * 根据应用id和日期 获取地图信息的--访问人数
     *
     * @param appId   应用id
     * @param strDate 开始时间
     * @param endDate 结束时间
     * @return
     */
    List<ProvinceDTO> selectProvinceAmountAccessPerson(@Param("appId") String appId, @Param("strDate") String strDate, @Param("endDate") String endDate);

    /**
     * 根据应用id和日期 获取地图信息的--打开次数
     *
     * @param appId   应用id
     * @param strDate 开始时间
     * @param endDate 结束时间
     * @return
     */
    List<ProvinceDTO> selectProvinceAmountOpens(@Param("appId") String appId, @Param("strDate") String strDate, @Param("endDate") String endDate);

    /**
     * 根据应用id和日期 获取地图信息的--新增人数
     *
     * @param appId   应用id
     * @param strDate 开始时间
     * @param endDate 结束时间
     * @return
     */
    List<ProvinceDTO> selectProvinceAmountNewPerson(@Param("appId") String appId, @Param("strDate") String strDate, @Param("endDate") String endDate);


    /**
     * 根据应用id和日期 获取省份列表
     *
     * @param appId
     * @param strDate 开始时间
     * @param endDate 结束时间
     * @return
     */
    List<ProvinceDTO> selectProvinceList(@Param("appId") String appId, @Param("strDate") String strDate, @Param("endDate") String endDate);

    /**
     * 根据应用id和日期 获取省份列表中的  市列表
     *
     * @param appId
     * @param strDate  开始时间
     * @param endDate  结束时间
     * @param province 省份
     * @return
     */
    List<ProvinceDTO> selectCityList(@Param("appId") String appId, @Param("strDate") String strDate, @Param("endDate") String endDate, @Param("province") String province);


    /**
     * 根据应用id、时间段、省份名字，查询当前省 省份详细信息列表
     *
     * @param appId
     * @param strDate  开始时间
     * @param endDate  结束时间
     * @param province 省份
     * @return
     */
    List<ProvinceDTO> selectProvinceDetailList(@Param("appId") String appId, @Param("strDate") String strDate, @Param("endDate") String endDate, @Param("province") String province);


    /**
     * 根据应用id、时间段、市名字，查询当前省 市详情数据列表
     *
     * @param appId
     * @param strDate  开始时间
     * @param endDate  结束时间
     * @param city 省份
     * @return
     */
    List<RegionalAnalysis> selectCityDetailList(@Param("appId") String appId, @Param("strDate") String strDate, @Param("endDate") String endDate, @Param("city") String city);

}