package com.yangzhongli.sp.service.impl;

import com.github.pagehelper.PageInfo;
import com.yangzhongli.sp.dao.dto.ProvinceDTO;
import com.yangzhongli.sp.dao.dto.SystemDTO;
import com.yangzhongli.sp.dao.instance.UserSystemMapper;
import com.yangzhongli.sp.dao.po.UserSystem;
import com.yangzhongli.sp.service.api.SystemService;
import com.yangzhongli.sp.service.bo.SystemVO;
import com.yangzhongli.sp.service.bo.UserSystemVO;
import com.yangzhongli.sp.utils.ConverterUtil;
import com.yangzhongli.sp.utils.PageUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName SystemServiceImpl
 * @descripetion TODO
 * @Author liyanbing
 * @Date 2019-05-28
 */
@Slf4j
@Service
public class SystemServiceImpl implements SystemService {
    @Autowired
    private UserSystemMapper userSystemMapper;

    @Override
    public List<SystemVO> selectSystem(String appId, String strDate, String endDate,int type) {
        List<SystemVO> systemVOS = new ArrayList<>();
        List<SystemDTO> systemDTOList =userSystemMapper.selectSystemList(appId,strDate,endDate);
        SystemVO systemVO = new SystemVO();
        //1=访问次数;2=访问人数;3=打开次数,4=新增人数
        if(!CollectionUtils.isEmpty(systemDTOList)){
            for (SystemDTO systemDTO : systemDTOList) {
                systemVO.setSystem(systemDTO.getSystem());
                //1=访问次数;2=访问人数;3=打开次数
                if (1 == type) {
                    systemVO.setAmountVisits(systemDTO.getAmountVisits());
                }
                if (2 == type) {
                    systemVO.setAmountAccessPerson(systemDTO.getAmountAccessPerson());
                }
                if (3 == type) {
                    systemVO.setAmountOpens(systemDTO.getAmountOpens());
                }
                if (4 == type) {
                    systemVO.setAmountNewPerson(systemDTO.getAmountNewPerson());
                }
                systemVOS.add(systemVO);
            }
        }
        return systemVOS;
    }

    @Override
    public List<SystemVO> selectSystemList(String appId, String strDate, String endDate) {
        List<SystemDTO> systemDTOList =userSystemMapper.selectSystemList(appId,strDate,endDate);
        return ConverterUtil.convert(SystemVO.class,systemDTOList);
    }

    @Override
    public List<UserSystemVO> selectSystemDetail(String appId, String strDate, String endDate, String systemPhone, int type) {
        UserSystemVO userSystemVO = new UserSystemVO();
        List<UserSystemVO> userSystemVOList = new ArrayList<>();
        List<UserSystem> userSystemList = userSystemMapper.selectSystemDetail(appId,strDate,endDate,systemPhone);
        if(!CollectionUtils.isEmpty(userSystemList)){
            for (UserSystem userSystem : userSystemList) {
                userSystemVO.setDate(userSystem.getDate());
                // 1=访问次数;2=访问人数;3=打开次数,4=新增人数
                if (1 == type) {
                    userSystemVO.setVisits(userSystem.getVisits());
                }
                if (2 == type) {
                    userSystemVO.setAccessPerson(userSystem.getAccessPerson());
                }
                if (3 == type) {
                    userSystemVO.setOpens(userSystem.getOpens());
                }
                if (4 == type) {
                    userSystemVO.setNewPerson(userSystem.getNewPerson());
                }
                userSystemVOList.add(userSystemVO);
            }
        }
        return userSystemVOList;
    }

    @Override
    public PageInfo<UserSystemVO> selectSystemDetailList(Integer pageNum, Integer pageSize, String appId, String strDate, String endDate, String systemPhone) {
        PageUtils.startPage(pageNum,pageSize);
        List<UserSystem> list = userSystemMapper.selectSystemDetailList(appId,strDate,endDate,systemPhone);
        return PageUtils.getPageInfo(list,UserSystemVO.class);
    }
}
