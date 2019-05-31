package com.yangzhongli.sp.service.api;

import com.github.pagehelper.PageInfo;
import com.yangzhongli.sp.service.bo.EventAllVO;

import java.util.List;

/**
 * @ClassName EventService
 * @descripetion TODO
 * @Author liyanbing
 * @Date 2019-05-28
 */
public interface EventService {


    /**
     * 根据应用id、时间段，查询应用自定义事件列表
     *
     * @param appId
     * @param strDate 开始时间
     * @param endDate 结束时间
     * @return
     */
    PageInfo<EventAllVO> selectEventAllList(Integer pageNum, Integer pageSize, String appId, String strDate, String endDate);

    /**
     * 根据应用id、时间段，查询应用自定义事件详情趋势图
     *
     * @param appId
     * @param strDate 开始时间
     * @param endDate 结束时间
     * @param id      事件主键
     * @param type    (1=触发次数;2=触发人数)
     * @return
     */
    List<EventAllVO> selectEventAllDetail(String appId, String strDate, String endDate, String id, int type);


    /**
     * 根据应用id、时间段，查询应用自定义事件详情数据列表
     *
     * @param appId
     * @param strDate 开始时间
     * @param endDate 结束时间
     * @param id      事件主键
     * @return
     */
    PageInfo<EventAllVO> selectEventAllDetailList(Integer pageNum, Integer pageSize, String appId, String strDate, String endDate, String id);

}
