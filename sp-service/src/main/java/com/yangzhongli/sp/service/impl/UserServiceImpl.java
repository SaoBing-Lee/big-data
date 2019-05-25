package com.yangzhongli.sp.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.StringUtil;
import com.yangzhongli.sp.dao.instance.UserAppRoleMapper;
import com.yangzhongli.sp.dao.instance.UserMapper;
import com.yangzhongli.sp.dao.po.User;
import com.yangzhongli.sp.dao.po.UserAppRole;
import com.yangzhongli.sp.service.api.AppRoleService;
import com.yangzhongli.sp.service.api.UserService;
import com.yangzhongli.sp.service.bo.AppRoleVO;
import com.yangzhongli.sp.service.bo.UserAppRoleVO;
import com.yangzhongli.sp.service.bo.UserVO;
import com.yangzhongli.sp.utils.ConverterUtil;
import com.yangzhongli.sp.utils.MD5Util;
import com.yangzhongli.sp.utils.PageUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final static String REG_PHONE = "/[1-9](\\d){11}/";

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserAppRoleMapper userAppRoleMapper;
    @Autowired
    private AppRoleService appRoleService;


    @Override
    public UserVO getUserById(String id) {
        User user = userMapper.selectByPrimaryKey(id);
        return ConverterUtil.convert(UserVO.class,user);
    }

    @Override
    public UserVO selectByKey(String id) {
        User user = userMapper.selectByPrimaryKey(id);
        UserVO userVO = ConverterUtil.convert(UserVO.class, user);
        if (null != user) {
            List<AppRoleVO> appRoleVOS = appRoleService.getUserRoleList(user.getId());
            userVO.setAppRoleVOList(appRoleVOS);
        }
        return userVO;
    }


    @Override
    public UserVO saveUser(UserVO userVO) {
        userVO.setUpdateTime(new Date());
        userVO.setCreateTime(new Date());
        userMapper.insertSelective(ConverterUtil.convert(User.class, userVO));
        List<UserAppRoleVO> userAppRoleVOS = userVO.getUserAppRoleVOS();
        for (UserAppRoleVO userApp : userAppRoleVOS) {
            userApp.setUserId(userVO.getId());//TODO 可能会出现读写冲突
            userAppRoleMapper.insert(ConverterUtil.convert(UserAppRole.class, userApp));
        }
        return userVO;
    }

    @Override
    public String userLogin(String name, String password) {
        String passwordMd5 = null;
        if (StringUtil.isNotEmpty(password)) {
            passwordMd5 = MD5Util.getMD5(password, true, 16);
        }
        return userMapper.userLogin(name, passwordMd5);
    }

    @Override
    public PageInfo<UserVO> selectUserList(Integer pageNum, Integer pageSize) {
        PageInfo<UserVO> pageInfo = new PageInfo<>();
        PageHelper.startPage(pageNum, pageSize);
        List<User> list = userMapper.selectUser();
        log.info("selectUserListlist========" + list);
        if (!CollectionUtils.isEmpty(list)) {
            pageInfo = PageUtils.getPageInfo(list, UserVO.class);
            for (UserVO userVO : pageInfo.getList()) {
                log.info("userVO.getId()========" + userVO.getId());
                String appRoleVOS = appRoleService.selectAppRole(userVO.getId());
                userVO.setAppRoleNames(appRoleVOS);
            }
        }
        return pageInfo;
    }

    @Override
    public void update(List<UserAppRoleVO> list) {
        UserAppRole userAppRole = new UserAppRole();
        if(!CollectionUtils.isEmpty(list)){
            userAppRole.setUserId(list.get(0).getUserId());
            int delCount = userAppRoleMapper.delete(userAppRole);
            if (delCount > 0) {
                for (UserAppRoleVO userAppRoleVO : list) {
                    userAppRoleMapper.insert(ConverterUtil.convert(UserAppRole.class, userAppRoleVO));
                }
            }
        }
    }

    @Override
    public int delUser(String[] ids) {
        UserAppRole userAppRole = new UserAppRole();
        for (int i=0;i<ids.length;i++){
            userAppRole.setUserId(ids[i]);
            userAppRoleMapper.delete(userAppRole);
        }
        return userMapper.del(ids);
    }

    @Override
    public boolean nameIsNot(String name) {
        int nameCount = userMapper.nameIsNot(name);
        if(nameCount>0){
            return true;
        }
        return false;
    }


}
