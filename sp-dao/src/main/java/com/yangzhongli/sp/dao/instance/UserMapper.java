package com.yangzhongli.sp.dao.instance;

import com.yangzhongli.sp.dao.base.MyMapper;
import com.yangzhongli.sp.dao.po.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface UserMapper extends MyMapper<User> {


    /**
     * 查询所有用户
     * @return
     */
    List<User> selectUser();
    /**
     * 系统管理用户登陆
     *
     * @return
     */
    String userLogin(@Param("userName") String userName, @Param("password") String password);

    /**
     * 批量删除用户
     * @param ids
     * @return
     */
    int del(String [] ids);

    /**
     * 判断用户名是否已经注册
     * @param name
     * @return
     */
    int nameIsNot(@Param("name") String name);
}