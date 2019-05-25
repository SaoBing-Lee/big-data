package com.yangzhongli.sp.dao.instance;

import com.yangzhongli.sp.dao.base.MyMapper;
import com.yangzhongli.sp.dao.po.AppRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface AppRoleMapper extends MyMapper<AppRole> {

    /**
     * 批量删除小程序（菜单）
     * @param ids
     * @return
     */
    int delAppRole(String [] ids);

    /**
     * 根据用户ID 查询用户的权限（小程序）--用户列表使用
     * @param userId
     * @return
     */
    String []  selectAppRole(@Param("userId") String userId);

    /**
     * 根据用户ID 查询用户的权限（小程序）--编辑用户使用
     * @param userId
     * @return
     */
    List<AppRole> getUserRoleList(@Param("userId") String userId);


}