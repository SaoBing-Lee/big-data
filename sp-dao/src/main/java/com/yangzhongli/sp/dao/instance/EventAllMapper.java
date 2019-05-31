package com.yangzhongli.sp.dao.instance;

import com.yangzhongli.sp.dao.base.MyMapper;
import com.yangzhongli.sp.dao.po.EventAll;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface EventAllMapper extends MyMapper<EventAll> {

    /**
     * 根据应用id、时间段，查询应用自定义事件列表
     *
     * @param appId
     * @param strDate 开始时间
     * @param endDate 结束时间
     * @return
     */
    List<EventAll> selectEventAllList(@Param("appId") String appId, @Param("strDate") String strDate, @Param("endDate") String endDate);


    /**
     * 根据应用id、时间段，查询应用自定义事件详情趋势图
     *
     * @param appId
     * @param strDate 开始时间
     * @param endDate 结束时间
     * @param id      事件主键
     * @return
     */
    List<EventAll> selectEventAllDetail(@Param("appId") String appId, @Param("strDate") String strDate, @Param("endDate") String endDate, @Param("id") String id);


    /**
     * 根据应用id、时间段，查询应用自定义事件详情数据列表
     *
     * @param appId
     * @param strDate 开始时间
     * @param endDate 结束时间
     * @param id      事件主键
     * @return
     */
    List<EventAll> selectEventAllDetailList(@Param("appId") String appId, @Param("strDate") String strDate, @Param("endDate") String endDate, @Param("id") String id);

}
