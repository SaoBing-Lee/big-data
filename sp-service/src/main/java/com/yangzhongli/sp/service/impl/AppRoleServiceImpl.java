package com.yangzhongli.sp.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yangzhongli.sp.dao.instance.AppRoleMapper;
import com.yangzhongli.sp.dao.po.AppRole;
import com.yangzhongli.sp.service.api.AppRoleService;
import com.yangzhongli.sp.service.bo.AppRoleVO;
import com.yangzhongli.sp.utils.ConverterUtil;
import com.yangzhongli.sp.utils.PageUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @ClassName AppRoleServiceImpl
 * @descripetion TODO
 * @Author liyanbing
 * @Date 2019-05-23
 */
@Slf4j
@Service
public class AppRoleServiceImpl implements AppRoleService {
    @Autowired
    private AppRoleMapper appRoleMapper;

    @Override
    public int save(AppRoleVO appRoleVO) {
        appRoleVO.setUpdateTime(new Date());
        appRoleVO.setCreateTime(new Date());
        return appRoleMapper.insert(ConverterUtil.convert(AppRole.class, appRoleVO));
    }

    @Override
    public PageInfo<AppRoleVO> select(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<AppRole> list = appRoleMapper.selectAll();
        return PageUtils.getPageInfo(list, AppRoleVO.class);
    }

    @Override
    public int delAppRole(String[] ids) {
        return appRoleMapper.delAppRole(ids);
    }

    @Override
    public String selectAppRole(String userId) {
        String[] str = appRoleMapper.selectAppRole(userId);
        return ConverterUtil.changeToString(str);
    }

    @Override
    public List<AppRoleVO> getUserRoleList(String userId) {
        List<AppRole> list = appRoleMapper.getUserRoleList(userId);
        return ConverterUtil.convert(AppRoleVO.class,list);
    }

}
