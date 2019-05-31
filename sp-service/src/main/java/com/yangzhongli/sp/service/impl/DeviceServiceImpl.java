package com.yangzhongli.sp.service.impl;

import com.github.pagehelper.PageInfo;
import com.yangzhongli.sp.dao.dto.DeviceDTO;
import com.yangzhongli.sp.dao.instance.DeviceAnalysisMapper;
import com.yangzhongli.sp.dao.po.DeviceAnalysis;
import com.yangzhongli.sp.service.api.DeviceService;
import com.yangzhongli.sp.service.bo.DeviceAnalysisVO;
import com.yangzhongli.sp.service.bo.DeviceVO;
import com.yangzhongli.sp.utils.PageUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName DeviceServiceImpl
 * @descripetion TODO
 * @Author liyanbing
 * @Date 2019-05-28
 */
@Slf4j
@Service
public class DeviceServiceImpl implements DeviceService {
    @Autowired
    private DeviceAnalysisMapper deviceAnalysisMapper;


    @Override
    public List<DeviceVO> selectDevice(String appId, String strDate, String endDate, int type) {
        List<DeviceVO> list = new ArrayList<>();
        DeviceVO deviceVO = new DeviceVO();
        List<DeviceDTO> deviceDTOList = deviceAnalysisMapper.selectDevice(appId, strDate, endDate);
        if (!CollectionUtils.isEmpty(deviceDTOList)) {
            for (DeviceDTO deviceDTO : deviceDTOList) {
                deviceVO.setDevice(deviceDTO.getDevice());
                //1=访问次数;2=访问人数;3=打开次数,4=新增人数
                if (1 == type) {
                    deviceVO.setAmountVisits(deviceDTO.getAmountVisits());
                }
                if (2 == type) {
                    deviceVO.setAmountAccessPerson(deviceDTO.getAmountAccessPerson());
                }
                if (3 == type) {
                    deviceVO.setAmountOpens(deviceDTO.getAmountOpens());
                }
                if (4 == type) {
                    deviceVO.setAmountNewPerson(deviceDTO.getAmountNewPerson());
                }
                list.add(deviceVO);
            }
        }
        return list;
    }

    @Override
    public PageInfo<DeviceVO> selectDeviceList(Integer pageNum, Integer pageSize, String appId, String strDate, String endDate) {
        PageUtils.startPage(pageNum, pageSize);
        List<DeviceDTO> deviceDTOList = deviceAnalysisMapper.selectDevice(appId, strDate, endDate);
        return PageUtils.getPageInfo(deviceDTOList, DeviceVO.class);
    }

    @Override
    public List<DeviceAnalysisVO> selectDeviceDetail(String appId, String strDate, String endDate, String device, int type) {
        List<DeviceAnalysisVO> list = new ArrayList<>();
        DeviceAnalysisVO deviceAnalysisVO = new DeviceAnalysisVO();
        List<DeviceAnalysis> deviceAnalysisList = deviceAnalysisMapper.selectDeviceDetail(appId, strDate, endDate, device);
        if (!CollectionUtils.isEmpty(deviceAnalysisList)) {
            for (DeviceAnalysis deviceAnalysis : deviceAnalysisList) {
                deviceAnalysisVO.setDevice(deviceAnalysis.getDevice());
                deviceAnalysisVO.setDate(deviceAnalysis.getDate());
                //1=访问次数;2=访问人数;3=打开次数,4=新增人数
                if (1 == type) {
                    deviceAnalysisVO.setVisits(deviceAnalysis.getVisits());
                }
                if (2 == type) {
                    deviceAnalysisVO.setAccessPerson(deviceAnalysis.getAccessPerson());
                }
                if (3 == type) {
                    deviceAnalysisVO.setOpens(deviceAnalysis.getOpens());
                }
                if (4 == type) {
                    deviceAnalysisVO.setNewPerson(deviceAnalysis.getNewPerson());
                }
                list.add(deviceAnalysisVO);
            }
        }
        return list;
    }

    @Override
    public PageInfo<DeviceAnalysisVO> selectDeviceDetailList(Integer pageNum, Integer pageSize, String appId, String strDate, String endDate, String device) {
        PageUtils.startPage(pageNum, pageSize);
        List<DeviceAnalysis> deviceAnalysisList = deviceAnalysisMapper.selectDeviceDetail(appId, strDate, endDate, device);
        return PageUtils.getPageInfo(deviceAnalysisList, DeviceAnalysisVO.class);
    }
}
