package com.yangzhongli.sp.dao.instance;

import com.yangzhongli.sp.dao.base.MyMapper;
import com.yangzhongli.sp.dao.dto.SystemDTO;
import com.yangzhongli.sp.dao.po.UserSystem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface UserSystemMapper extends MyMapper<UserSystem> {

    /**
     * 根据应用id、时间段,统计分析终端 趋势图和详情列表
     *
     * @param appId
     * @param strDate
     * @param endDate
     * @return
     */
    List<SystemDTO> selectSystemList(@Param("appId") String appId, @Param("strDate") String strDate, @Param("endDate") String endDate);


    /**
     * 根据应用id、系统名称、时间段,统计分析终端详细信息 趋势图
     *
     * @param appId
     * @param strDate
     * @param endDate
     * @param systemPhone 手机系统名称
     * @return
     */
    List<UserSystem> selectSystemDetail(@Param("appId") String appId, @Param("strDate") String strDate, @Param("endDate") String endDate, @Param("systemPhone") String systemPhone);


    /**
     * 根据应用id、系统名称、时间段,统计分析终端详细信息 列表
     *
     * @param appId
     * @param strDate
     * @param endDate
     * @param systemPhone 手机系统名称
     * @return
     */
    List<UserSystem> selectSystemDetailList(@Param("appId") String appId, @Param("strDate") String strDate, @Param("endDate") String endDate, @Param("systemPhone") String systemPhone);

}
