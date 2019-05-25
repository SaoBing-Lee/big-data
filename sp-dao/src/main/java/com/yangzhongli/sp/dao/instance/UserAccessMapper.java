package com.yangzhongli.sp.dao.instance;

import com.yangzhongli.sp.dao.base.MyMapper;
import com.yangzhongli.sp.dao.po.UserAccess;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface UserAccessMapper extends MyMapper<UserAccess> {

    /**
     * 根据appId查询访问总表对应数据
     *
     * @param appId 应用ID
     * @return
     */
    UserAccess get(@Param("appId") String appId);

}