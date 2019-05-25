package com.yangzhongli.sp.service.api;

import com.yangzhongli.sp.service.bo.UserAccessVO;

/**
 * @ClassName UserAccessService
 * @descripetion 访问总表数据--实时数据的关键指标
 * @Author liyanbing
 * @Date 2019-05-25
 */
public interface UserAccessService {

    /**
     * 根据appId查询访问总表对应数据
     *
     * @param appId 应用ID
     * @return
     */
    UserAccessVO get(String appId);
}
