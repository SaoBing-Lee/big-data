package com.yangzhongli.sp.service.api;

import com.github.pagehelper.PageInfo;
import com.yangzhongli.sp.service.bo.AppRoleVO;

import java.util.List;

/**
 * @ClassName AppRoleService
 * @descripetion 小程序（权限）处理
 * @Author liyanbing
 * @Date 2019-05-23
 */
public interface AppRoleService {

    int save(AppRoleVO appRoleVO);

    /**
     * 查询小程序（菜单）列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<AppRoleVO> select( Integer pageNum,Integer pageSize);

    /**
     * 批量删除小程序（菜单）
     * @param ids
     * @return
     */
    int delAppRole(String[] ids);

    /**
     * 根据用户ID查询权限（小程序）--用户列表使用
     * @param userId
     * @return
     */
    String selectAppRole(String userId);

    /**
     * 根据用户ID 查询用户的权限（小程序）--编辑用户使用
     * @param userId
     * @return
     */
    List<AppRoleVO> getUserRoleList( String userId);

}
