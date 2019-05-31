package com.yangzhongli.sp.service.impl;

import com.github.pagehelper.PageInfo;
import com.yangzhongli.sp.dao.dto.NetworkDTO;
import com.yangzhongli.sp.dao.instance.NetworkEnvironmentMapper;
import com.yangzhongli.sp.dao.po.NetworkEnvironment;
import com.yangzhongli.sp.service.api.NetworkEnvironmentService;
import com.yangzhongli.sp.service.bo.NetworkEnvironmentVO;
import com.yangzhongli.sp.service.bo.NetworkVO;
import com.yangzhongli.sp.utils.ConverterUtil;
import com.yangzhongli.sp.utils.PageUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName NetworkEnvironmentServiceImpl
 * @descripetion TODO
 * @Author liyanbing
 * @Date 2019-05-28
 */
@Service
@Slf4j
public class NetworkEnvironmentServiceImpl implements NetworkEnvironmentService {

    @Autowired
    private NetworkEnvironmentMapper networkEnvironmentMapper;

    @Override
    public List<NetworkVO> selectNetwork(String appId, String strDate, String endDate, int type) {
        List<NetworkVO> list = new ArrayList<>();
        NetworkVO networkVO = new NetworkVO();
        List<NetworkDTO> networkDTOList = networkEnvironmentMapper.selectNetwork(appId, strDate, endDate);
        if (!CollectionUtils.isEmpty(networkDTOList)) {
            for (NetworkDTO networkDTO : networkDTOList) {
                networkVO.setNetwork(networkDTO.getNetwork());
                //1=访问次数;2=访问人数;3=打开次数,4=新增人数
                if (1 == type) {
                    networkVO.setAmountVisits(networkDTO.getAmountVisits());
                }
                if (2 == type) {
                    networkVO.setAmountAccessPerson(networkDTO.getAmountAccessPerson());
                }
                if (3 == type) {
                    networkVO.setAmountOpens(networkDTO.getAmountOpens());
                }
                if (4 == type) {
                    networkVO.setAmountNewPerson(networkDTO.getAmountNewPerson());
                }
                list.add(networkVO);
            }
        }
        return list;
    }

    @Override
    public List<NetworkVO> selectNetworkList(String appId, String strDate, String endDate) {
        List<NetworkDTO> networkDTOList = networkEnvironmentMapper.selectNetwork(appId, strDate, endDate);
        return ConverterUtil.convert(NetworkVO.class, networkDTOList);
    }

    @Override
    public List<NetworkEnvironmentVO> selectNetworkDetail(String appId, String strDate, String endDate, String network, int type) {
        List<NetworkEnvironmentVO> list = new ArrayList<>();
        NetworkEnvironmentVO networkEnvironmentVO = new NetworkEnvironmentVO();
        List<NetworkEnvironment> networkEnvironmentList = networkEnvironmentMapper.selectNetworkDetail(appId, strDate, endDate, network);
        if (!CollectionUtils.isEmpty(networkEnvironmentList)) {
            for (NetworkEnvironment networkEnvironment : networkEnvironmentList) {
                networkEnvironmentVO.setNetwork(networkEnvironment.getNetwork());
                networkEnvironmentVO.setDate(networkEnvironment.getDate());
                //1=访问次数;2=访问人数;3=打开次数,4=新增人数
                if (1 == type) {
                    networkEnvironmentVO.setVisits(networkEnvironment.getVisits());
                }
                if (2 == type) {
                    networkEnvironmentVO.setAccessPerson(networkEnvironment.getAccessPerson());
                }
                if (3 == type) {
                    networkEnvironmentVO.setOpens(networkEnvironment.getOpens());
                }
                if (4 == type) {
                    networkEnvironmentVO.setNewPerson(networkEnvironment.getNewPerson());
                }
                list.add(networkEnvironmentVO);
            }
        }
        return list;
    }

    @Override
    public PageInfo<NetworkEnvironmentVO> selectNetworkDetailList(Integer pageNum, Integer pageSize, String appId, String strDate, String endDate, String network) {
        PageUtils.startPage(pageNum, pageSize);
        List<NetworkEnvironment> networkEnvironmentList = networkEnvironmentMapper.selectNetworkDetail(appId, strDate, endDate, network);
        return PageUtils.getPageInfo(networkEnvironmentList, NetworkEnvironmentVO.class);
    }
}
