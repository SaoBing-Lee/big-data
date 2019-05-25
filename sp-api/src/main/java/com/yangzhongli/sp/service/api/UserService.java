package com.yangzhongli.sp.service.api;

import com.github.pagehelper.PageInfo;
import com.yangzhongli.sp.service.bo.UserAppRoleVO;
import com.yangzhongli.sp.service.bo.UserVO;

import java.util.List;

public interface UserService {

    /**
     * 获取用户信息判断是否是管理员
     * @param id
     * @return
     */
    UserVO getUserById(String id);
    /**
     * 编辑获取用户信息和用户权限
     * @param id
     * @return
     */
    UserVO selectByKey(String id);


    /**
     * 添加用户 （密码MD5 32位）
     * @param userVO
     * @return
     */
    UserVO saveUser(UserVO userVO);


    /**
     * 用户
     *
     * @param name
     * @param password
     * @return
     */
    String userLogin(String name, String password);

    /**
     * 查询用户信息和用户权限
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<UserVO> selectUserList(Integer pageNum , Integer pageSize);

    /**
     * 编辑用户权限
     * @param list
     * @return
     */
    void update(List<UserAppRoleVO> list);

    /**
     * 批量删除用户
     * @param ids
     * @return
     */
    int delUser(String [] ids);

    /**
     * 判断用户名是否已经注册,true，false不存在
     * @param name
     * @return
     */
    boolean nameIsNot(String name);

}
