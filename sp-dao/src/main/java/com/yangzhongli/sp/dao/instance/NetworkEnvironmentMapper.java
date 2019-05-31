package com.yangzhongli.sp.dao.instance;

import com.yangzhongli.sp.dao.base.MyMapper;
import com.yangzhongli.sp.dao.dto.NetworkDTO;
import com.yangzhongli.sp.dao.po.NetworkEnvironment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface NetworkEnvironmentMapper extends MyMapper<NetworkEnvironment> {


    /**
     * 根据应用id、时间段，查询应用网络环境 趋势图和列表
     *
     * @param appId
     * @param strDate 开始时间
     * @param endDate 结束时间
     * @return
     */
    List<NetworkDTO> selectNetwork(@Param("appId") String appId, @Param("strDate") String strDate, @Param("endDate") String endDate);

    /**
     * 根据应用id、时间段，查询应用网络环境详细信息 趋势图
     *
     * @param appId
     * @param strDate
     * @param endDate
     * @param network 手机网络
     * @return
     */
    List<NetworkEnvironment> selectNetworkDetail(@Param("appId") String appId, @Param("strDate") String strDate, @Param("endDate") String endDate, @Param("network") String network);


    /**
     * 根据应用id、时间段，查询应用网络环境详细信息 列表
     *
     * @param appId
     * @param strDate
     * @param endDate
     * @param network 手机网络
     * @return
     */
    List<NetworkEnvironment> selectNetworkDetailList(@Param("appId") String appId, @Param("strDate") String strDate, @Param("endDate") String endDate, @Param("network") String network);

}