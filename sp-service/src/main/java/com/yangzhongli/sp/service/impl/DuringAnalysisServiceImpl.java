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
    public Map<String, Object> selectDuringAnalysis(String appId, int type) {
        Map<String, Object> map = new HashMap<>(4);
        List<String> todayList = new ArrayList<>();
        List<String> yesterdayList = new ArrayList<>();
        List<String> sevenDayList = new ArrayList<>();
        List<String> dateList = new ArrayList<>();

        String dateToday = DateChangeUtils.changeDateStr(new Date()); //今天的时间字符串
        Date yesterday = DateChangeUtils.getDateBefore(new Date(), 1); //获取昨天的时间
        String dateYesterday = DateChangeUtils.changeDateStr(yesterday); //获取昨天的字符串时间
        Date sevenDay = DateChangeUtils.getDateBefore(new Date(), 7); //获取7天前的时间
        String dateSevenDay = DateChangeUtils.changeDateStr(sevenDay); //获取7天前的字符串时间
        List<DuringAnalysis> list = duringAnalysisMapper.selectDuringAnalysis(appId, dateToday, dateYesterday, dateSevenDay);
        if (!CollectionUtils.isEmpty(list)) {
            for (DuringAnalysis duringAnalysis : list) {
                dateList.add(duringAnalysis.getTime());
                if (duringAnalysis.getDate().equals(dateToday)) {
                    todayList.add(this.difType(duringAnalysis, type));
                }
                if (duringAnalysis.getDate().equals(dateYesterday)) {
                    yesterdayList.add(this.difType(duringAnalysis, type));
                }
                if (duringAnalysis.getDate().equals(dateSevenDay)) {
                    sevenDayList.add(this.difType(duringAnalysis, type));
                }
            }
            //如果返回值为空就需要等长度值
            if (CollectionUtils.isEmpty(todayList)) {
                todayList.add("0");
            }
            if (CollectionUtils.isEmpty(yesterdayList)) {
                for (int i = 0; i < 24; i++) {
                    yesterdayList.add("0");
                }
            }
            if (CollectionUtils.isEmpty(sevenDayList)) {
                for (int i = 0; i < 24; i++) {
                    sevenDayList.add("0");
                }
            }
            map.put("todayList", todayList);
            map.put("yesterdayList", yesterdayList);
            map.put("sevenDayList", sevenDayList);
            map.put("date", dateList);
        }
        return map;
    }

    @Override
    public List<DuringAnalysisVO> selectDuringAnalysisDetail(String appId) {
        String dateToday = DateChangeUtils.changeDateStr(new Date()); //今天的时间字符串
        List<DuringAnalysis> list = duringAnalysisMapper.selectDuringAnalysisDetail(appId, dateToday);
        return ConverterUtil.convert(DuringAnalysisVO.class, list);
    }

    /**
     * 根据不同的类型
     *
     * @param duringAnalysis
     * @param type
     * @return
     */
    private String difType(DuringAnalysis duringAnalysis, int type) {
        String str = null;
        //1=访问次数;2=访问人数;3=打开次数,4=新增人数,5=平均时长
        if (1 == type) {
            str = duringAnalysis.getVisits();
        }
        if (2 == type) {
            str = duringAnalysis.getAccessPerson();
        }
        if (3 == type) {
            str = duringAnalysis.getOpens();
        }
        if (4 == type) {
            str = duringAnalysis.getNewPerson();
        }
        if (5 == type) {
            str = duringAnalysis.getOnlineMean();
        }
        return str;
    }

    @Override
    public List<String>  selectHistoryDuring(String appId, String strDate, String endDate,int type) {
        List<String> strList = new ArrayList<>();
        List<DuringAnalysis> list = duringAnalysisMapper.selectHistoryDuring(appId, strDate, endDate);
        if (!CollectionUtils.isEmpty(list)) {
            for (DuringAnalysis duringAnalysis : list) {
                strList.add(this.difType(duringAnalysis, type));
            }
            //如果返回值为空就需要等长度值
            if (CollectionUtils.isEmpty(strList)) {
                strList.add("0");
            }
        }
        return strList;
    }
}
