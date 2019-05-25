package com.yangzhongli.sp.service.impl;

import com.yangzhongli.sp.dao.instance.UserAccessMapper;
import com.yangzhongli.sp.dao.po.UserAccess;
import com.yangzhongli.sp.service.api.UserAccessService;
import com.yangzhongli.sp.service.bo.UserAccessVO;
import com.yangzhongli.sp.utils.ConverterUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName UserAccessService
 * @descripetion TODO
 * @Author liyanbing
 * @Date 2019-05-25
 */
@Service
@Slf4j
public class UserAccessServiceImpl implements UserAccessService {
    @Autowired
    private UserAccessMapper userAccessMapper;

    @Override
    public UserAccessVO get(String appId) {
        UserAccess userAccess = userAccessMapper.get(appId);
        return ConverterUtil.convert(UserAccessVO.class,userAccess);
    }
}
