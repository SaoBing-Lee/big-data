package com.yangzhongli.sp.service.impl;

import com.github.pagehelper.PageInfo;
import com.yangzhongli.sp.dao.instance.WechatUserMapper;
import com.yangzhongli.sp.dao.po.WechatUser;
import com.yangzhongli.sp.service.api.WechatUserService;
import com.yangzhongli.sp.service.bo.WechatUserVO;
import com.yangzhongli.sp.utils.ConverterUtil;
import com.yangzhongli.sp.utils.MD5Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
public class WechatUserServiceImpl implements WechatUserService {

    private final static String REG_PHONE = "/[1-9](\\d){11}/";

    @Autowired
    private WechatUserMapper wechatUserMapper;

    public WechatUserVO selectByPrimaryKey(WechatUserVO wechatUserVO) {
        WechatUser wechatUser = wechatUserMapper.selectByPrimaryKey(ConverterUtil.convert(WechatUser.class, wechatUserVO));
        return ConverterUtil.convert(WechatUserVO.class, wechatUser);
    }

    @Override
    public WechatUserVO selectByKey(String id) {
        WechatUser wechatUser = wechatUserMapper.selectByPrimaryKey(id);
        return ConverterUtil.convert(WechatUserVO.class, wechatUser);
    }

    @Override
    public WechatUserVO getOpenId(String openId) {
        WechatUser wechatUser = wechatUserMapper.getWechatUser(openId);
        return ConverterUtil.convert(WechatUserVO.class, wechatUser);
    }

    @Override
    public WechatUserVO saveWechatUser(WechatUserVO wechatUserVO) {
        wechatUserMapper.insertSelective(ConverterUtil.convert(WechatUser.class, wechatUserVO));
        return wechatUserVO;
    }

    @Override
    public Long wechatUserCount(String strTime, String endTime) {
        return wechatUserMapper.wechatUserCount(strTime, endTime);
    }

    @Override
    public PageInfo<WechatUserVO> selectBackUserList(Integer pageNum, Integer pageSize, Map<String, Object> map) {
        return null;
    }


    @Override
    public String userLogin(String userName, String password) {
        String passwordMd5 = MD5Util.getMD5(password, true, 16);
        return wechatUserMapper.userLogin(userName, passwordMd5);
    }

    @Override
    public int update(String userId,String phone) {
        WechatUser wechatUser = wechatUserMapper.selectByPrimaryKey(userId);
        wechatUser.setPhone(phone);
        return wechatUserMapper.updateByPrimaryKeySelective(wechatUser);
    }
}
