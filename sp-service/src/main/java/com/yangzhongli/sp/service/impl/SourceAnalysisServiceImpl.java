package com.yangzhongli.sp.service.impl;

import com.github.pagehelper.PageInfo;
import com.yangzhongli.sp.dao.dto.SourceDTO;
import com.yangzhongli.sp.dao.instance.SourceAnalysisMapper;
import com.yangzhongli.sp.dao.po.SourceAnalysis;
import com.yangzhongli.sp.service.api.SourceAnalysisService;
import com.yangzhongli.sp.service.bo.SourceAnalysisVO;
import com.yangzhongli.sp.service.bo.SourceVO;
import com.yangzhongli.sp.utils.ConverterUtil;
import com.yangzhongli.sp.utils.PageUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName SourceAnalysisServiceImpl
 * @descripetion TODO
 * @Author liyanbing
 * @Date 2019-05-30
 */
@Slf4j
@Service
public class SourceAnalysisServiceImpl implements SourceAnalysisService {

    @Autowired
    private SourceAnalysisMapper sourceAnalysisMapper;


    @Override
    public List<SourceAnalysisVO> selectSource(String appId, String strDate, String endDate) {
        List<SourceAnalysis> sourceAnalysisList = new ArrayList<>();
        List<String> list = sourceAnalysisMapper.selectSourceDateList(appId, strDate, endDate);
        if (!CollectionUtils.isEmpty(list)) {
            for (String s : list) {
                log.info("s=========" + s);
                sourceAnalysisList = sourceAnalysisMapper.selectSource(appId, s);
            }
        }
        return ConverterUtil.convert(SourceAnalysisVO.class, sourceAnalysisList);
    }

    @Override
    public PageInfo<SourceVO> selectSourceList(Integer pageNum, Integer pageSize, String appId, String strDate, String endDate) {
        PageUtils.startPage(pageNum, pageSize);
        List<SourceDTO> sourceDTOList = sourceAnalysisMapper.selectSourceList(appId, strDate, endDate);
        return PageUtils.getPageInfo(sourceDTOList, SourceVO.class);
    }
}
