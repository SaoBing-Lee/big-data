package com.yangzhongli.sp.service.impl;

import com.github.pagehelper.PageInfo;
import com.yangzhongli.sp.dao.instance.EventAllMapper;
import com.yangzhongli.sp.dao.po.EventAll;
import com.yangzhongli.sp.service.api.EventService;
import com.yangzhongli.sp.service.bo.EventAllVO;
import com.yangzhongli.sp.utils.PageUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName EventServiceImpl
 * @descripetion TODO
 * @Author liyanbing
 * @Date 2019-05-28
 */
@Service
@Slf4j
public class EventServiceImpl implements EventService {
    @Autowired
    private EventAllMapper eventAllMapper;

    @Override
    public PageInfo<EventAllVO> selectEventAllList(Integer pageNum, Integer pageSize, String appId, String strDate, String endDate) {
        PageUtils.startPage(pageNum, pageSize);
        List<EventAll> list = eventAllMapper.selectEventAllList(appId, strDate, endDate);
        return PageUtils.getPageInfo(list, EventAllVO.class);
    }

    @Override
    public List<EventAllVO> selectEventAllDetail(String appId, String strDate, String endDate, String id, int type) {
        List<EventAllVO> list = new ArrayList<>();
        EventAllVO eventAllVO = new EventAllVO();
        List<EventAll> eventAllList = eventAllMapper.selectEventAllDetail(appId, strDate, endDate, id);
        if (!CollectionUtils.isEmpty(eventAllList)) {
            for (EventAll eventAll : eventAllList) {
                eventAllVO.setEventName(eventAll.getEventName());
                eventAllVO.setDate(eventAll.getDate());
                //(1=触发次数;2=触发人数)
                if (1 == type) {
                    eventAllVO.setTriggerNum(eventAll.getTriggerNum());
                }
                if (2 == type) {
                    eventAllVO.setTriggerPerson(eventAll.getTriggerPerson());
                }
                list.add(eventAllVO);
            }
        }
        return list;
    }

    @Override
    public PageInfo<EventAllVO> selectEventAllDetailList(Integer pageNum, Integer pageSize, String appId, String strDate, String endDate, String id) {
        PageUtils.startPage(pageNum, pageSize);
        List<EventAll> list = eventAllMapper.selectEventAllDetailList(appId, strDate, endDate, id);
        return PageUtils.getPageInfo(list, EventAllVO.class);
    }


}
