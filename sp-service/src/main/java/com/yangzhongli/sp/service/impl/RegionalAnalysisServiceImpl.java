package com.yangzhongli.sp.service.impl;

import com.github.pagehelper.PageInfo;
import com.yangzhongli.sp.dao.dto.ProvinceDTO;
import com.yangzhongli.sp.dao.instance.RegionalAnalysisMapper;
import com.yangzhongli.sp.dao.po.RegionalAnalysis;
import com.yangzhongli.sp.service.api.RegionalAnalysisService;
import com.yangzhongli.sp.service.bo.CityVO;
import com.yangzhongli.sp.service.bo.ProvinceVO;
import com.yangzhongli.sp.service.bo.RegionalAnalysisVO;
import com.yangzhongli.sp.utils.ConverterUtil;
import com.yangzhongli.sp.utils.PageUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @ClassName RegionalAnalysisServiceImpl
 * @descripetion TODO
 * @Author liyanbing
 * @Date 2019-05-27
 */
@Slf4j
@Service
public class RegionalAnalysisServiceImpl implements RegionalAnalysisService {

    @Autowired
    private RegionalAnalysisMapper regionalAnalysisMapper;


    @Override
    public List<ProvinceVO> selectProvinceAmountList(String appId, String strDate, String endDate, int type) {
        List<ProvinceDTO> list = null;
        //1=访问次数;2=访问人数;3=打开次数,4=新增人数
        if (1 == type) {
            list = regionalAnalysisMapper.selectProvinceAmountVisits(appId, strDate, endDate);
        }
        if (2 == type) {
            list = regionalAnalysisMapper.selectProvinceAmountAccessPerson(appId, strDate, endDate);
        }
        if (3 == type) {
            list = regionalAnalysisMapper.selectProvinceAmountOpens(appId, strDate, endDate);
        }
        if (4 == type) {
            list = regionalAnalysisMapper.selectProvinceAmountNewPerson(appId, strDate, endDate);
        }
        return ConverterUtil.convert(ProvinceVO.class, list);
    }


    @Override
    public PageInfo<ProvinceVO> selectProvinceList(Integer pageNum, Integer pageSize, String appId, String strDate, String endDate) {
        PageUtils.startPage(pageNum, pageSize);
        List<ProvinceDTO> list = regionalAnalysisMapper.selectProvinceList(appId, strDate, endDate);
        return PageUtils.getPageInfo(list, ProvinceVO.class);
    }

    @Override
    public List<ProvinceVO> selectCityList(String appId, String strDate, String endDate, String province) {
        List<ProvinceDTO> list = regionalAnalysisMapper.selectCityList(appId, strDate, endDate, province);
        return ConverterUtil.convert(ProvinceVO.class, list);
    }

    @Override
    public List<ProvinceVO> selectProAmountList(String appId, String strDate, String endDate, String province, int type) {
        List<ProvinceVO> list = null;
        ProvinceVO p = new ProvinceVO();
        List<ProvinceDTO> provinceDTOList = regionalAnalysisMapper.selectProvinceDetailList(appId, strDate, endDate,province);
        if(!CollectionUtils.isEmpty(provinceDTOList)){
            for (ProvinceDTO provinceDTO : provinceDTOList) {
                p.setProvince(provinceDTO.getProvince());
                p.setCity(provinceDTO.getCity());
                p.setDate(provinceDTO.getDate());
                //1=访问次数;2=访问人数;3=打开次数,4=新增人数
                if (1 == type) {
                    p.setAmountVisits(provinceDTO.getAmountVisits());
                }
                if (2 == type) {
                    p.setAmountAccessPerson(provinceDTO.getAmountAccessPerson());
                }
                if (3 == type) {
                    p.setAmountOpens(provinceDTO.getAmountOpens());
                }
                if (4 == type) {
                    p.setAmountNewPerson(provinceDTO.getAmountNewPerson());
                }
                list.add(p);
            }
        }
        return list;
    }

    @Override
    public PageInfo<ProvinceVO> selectProvinceDetailList(Integer pageNum, Integer pageSize, String appId, String strDate, String endDate, String province) {
        PageUtils.startPage(pageNum, pageSize);
        List<ProvinceDTO> provinceDTOList =regionalAnalysisMapper.selectProvinceDetailList(appId,strDate,endDate,province);
        return PageUtils.getPageInfo(provinceDTOList,ProvinceVO.class);
    }

    @Override
    public List<CityVO> selectCityDetailAmountList(String appId, String strDate, String endDate, String city, int type) {
        List<CityVO> list = null;
        CityVO c = new CityVO();
        List<RegionalAnalysis> regionalAnalysisList = regionalAnalysisMapper.selectCityDetailList(appId, strDate, endDate,city);
        if(!CollectionUtils.isEmpty(regionalAnalysisList)){
            for (RegionalAnalysis regionalAnalysis : regionalAnalysisList) {
                c.setCity(regionalAnalysis.getCity());
                c.setDate(regionalAnalysis.getDate());
                //1=访问次数;2=访问人数;3=打开次数,4=新增人数
                if (1 == type) {
                    c.setVisits(regionalAnalysis.getVisits());
                }
                if (2 == type) {
                    c.setAccessPerson(regionalAnalysis.getAccessPerson());
                }
                if (3 == type) {
                    c.setOpens(regionalAnalysis.getOpens());
                }
                if (4 == type) {
                   c.setNewPerson(regionalAnalysis.getNewPerson());
                }
                list.add(c);
            }
        }
        return list;
    }

    @Override
    public PageInfo<RegionalAnalysisVO> selectCityDetailList(Integer pageNum, Integer pageSize,String appId, String strDate, String endDate, String city) {
        PageUtils.startPage(pageNum, pageSize);
        List<RegionalAnalysis> list = regionalAnalysisMapper.selectCityDetailList(appId, strDate, endDate,city);
        return PageUtils.getPageInfo(list, RegionalAnalysisVO.class);
    }
}
