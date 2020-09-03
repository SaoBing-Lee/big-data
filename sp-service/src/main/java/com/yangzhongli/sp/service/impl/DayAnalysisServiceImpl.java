package com.yangzhongli.sp.service.impl;

import com.github.pagehelper.PageInfo;
import com.yangzhongli.sp.dao.instance.DayAnalysisMapper;
import com.yangzhongli.sp.dao.po.DayAnalysis;
import com.yangzhongli.sp.dao.po.DuringAnalysis;
import com.yangzhongli.sp.service.api.DayAnalysisService;
import com.yangzhongli.sp.service.bo.DayAnalysisVO;
import com.yangzhongli.sp.utils.ConverterUtil;
import com.yangzhongli.sp.utils.DateUtils;
import com.yangzhongli.sp.utils.PageUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName DayAnalysisServiceImpl
 * @descripetion TODO
 * @Author liyanbing
 * @Date 2019-05-25
 */
@Service
@Slf4j
public class DayAnalysisServiceImpl implements DayAnalysisService {

    @Autowired
    private DayAnalysisMapper dayAnalysisMapper;

    @Override
    public  Map<String, Object> selectDay(String appId, String strDate, String endDate,int type) {
        Map<String, Object> map = new HashMap<>(2);
        List<String> strList = new ArrayList<>();
        List<String> dateList = new ArrayList<>();
        List<DayAnalysis>  list = dayAnalysisMapper.selectDay(appId,strDate,endDate);
        if (!CollectionUtils.isEmpty(list)) {
            for (DayAnalysis dayAnalysis : list) {
                dateList.add(dayAnalysis.getDate());
                strList.add(this.difType(dayAnalysis, type));
            }
            //如果返回值为空就需要等长度值
            if (CollectionUtils.isEmpty(strList)) {
                strList.add("0");
            }
            map.put("strList",strList);
            map.put("dateList",dateList);
        }
        return map;
    }
    /**
     * 根据不同的类型
     *
     * @param dayAnalysis
     * @param type
     * @return
     */
    private String difType(DayAnalysis dayAnalysis, int type) {
        String str = null;
        //1=访问次数;2=访问人数;3=打开次数,4=新增人数,5=平均时长
        if (1 == type) {
            str = dayAnalysis.getVisits();
        }
        if (2 == type) {
            str = dayAnalysis.getAccessPerson();
        }
        if (3 == type) {
            str = dayAnalysis.getOpens();
        }
        if (4 == type) {
            str = dayAnalysis.getNewPerson();
        }
        if (5 == type) {
            str = dayAnalysis.getOnlineMean();
        }
        return str;
    }
    @Override
    public PageInfo<DayAnalysisVO> selectDayList(Integer pageNum, Integer pageSize, String appId, String strDate, String endDate) {
        PageUtils.startPage(pageNum, pageSize);
        List<DayAnalysis> list = dayAnalysisMapper.selectDayList(appId,strDate,endDate);
        return PageUtils.getPageInfo(list, DayAnalysisVO.class);
    }

    @Override
    public List<DayAnalysisVO> selectCsvDayList(String appId,String strDate, String endDate) {
        List<DayAnalysis> list = dayAnalysisMapper.selectDayList(appId,strDate,endDate);
        return ConverterUtil.convert(DayAnalysisVO.class,list);
    }
}
