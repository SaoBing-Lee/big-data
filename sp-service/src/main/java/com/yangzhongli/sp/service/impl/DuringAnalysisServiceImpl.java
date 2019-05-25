package com.yangzhongli.sp.service.impl;

import com.yangzhongli.sp.dao.instance.DuringAnalysisMapper;
import com.yangzhongli.sp.dao.po.DuringAnalysis;
import com.yangzhongli.sp.service.api.DuringAnalysisService;
import com.yangzhongli.sp.service.bo.DuringAnalysisVO;
import com.yangzhongli.sp.utils.ConverterUtil;
import com.yangzhongli.sp.utils.DateChangeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * @ClassName DuringAnalysisServiceImpl
 * @descripetion TODO
 * @Author liyanbing
 * @Date 2019-05-25
 */
@Slf4j
@Service
public class DuringAnalysisServiceImpl implements DuringAnalysisService {

    @Autowired
    private DuringAnalysisMapper duringAnalysisMapper;

    @Override
    public Map<String, Object> selectDuringAnalysis(String appId) {
        Map<String, Object> map = new HashMap<>(3);
        List<DuringAnalysisVO> todayList = new ArrayList<>();
        List<DuringAnalysisVO> yesterdayList = new ArrayList<>();
        List<DuringAnalysisVO> sevenDayList = new ArrayList<>();

        String dateToday = DateChangeUtils.changeDateShow(new Date()); //今天的时间字符串
        Date yesterday = DateChangeUtils.getDateBefore(new Date(), 1); //获取昨天的时间
        String dateYesterday = DateChangeUtils.changeDateShow(yesterday); //获取昨天的字符串时间
        Date sevenDay = DateChangeUtils.getDateBefore(new Date(), 7); //获取7天前的时间
        String dateSevenDay = DateChangeUtils.changeDateShow(sevenDay); //获取7天前的字符串时间
        List<DuringAnalysis> list = duringAnalysisMapper.selectDuringAnalysis(appId, dateToday, dateYesterday, dateSevenDay);
        List<DuringAnalysisVO> duringAnalysisVOList = ConverterUtil.convert(DuringAnalysisVO.class, list);

        if (!CollectionUtils.isEmpty(duringAnalysisVOList)) {
            for (DuringAnalysisVO duringAnalysisVO : duringAnalysisVOList) {
                if (duringAnalysisVO.getDate().equals(dateToday)) {
                    todayList.add(duringAnalysisVO);
                }
                if (duringAnalysisVO.getDate().equals(dateYesterday)) {
                    yesterdayList.add(duringAnalysisVO);
                }
                if (duringAnalysisVO.getDate().equals(dateSevenDay)) {
                    sevenDayList.add(duringAnalysisVO);
                }
            }
            map.put("todayList", todayList);
            map.put("yesterdayList", yesterdayList);
            map.put("sevenDayList", sevenDayList);
        }
        return map;
    }

    @Override
    public List<DuringAnalysisVO> selectHistoryDuring(String appId, int beforeDay) {
        String checkDay;
        if (beforeDay == 0) {
            checkDay = DateChangeUtils.changeDateShow(new Date()); //今天的时间字符串
        } else {
            Date changeday = DateChangeUtils.getDateBefore(new Date(), beforeDay); //获取时间
            checkDay = DateChangeUtils.changeDateShow(changeday); //将时间转换成字符串
        }
        List<DuringAnalysis> list = duringAnalysisMapper.selectHistoryDuring(appId, checkDay);
        return ConverterUtil.convert(DuringAnalysisVO.class, list);
    }
}
