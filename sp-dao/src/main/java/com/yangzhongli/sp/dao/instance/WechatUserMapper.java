package com.yangzhongli.sp.dao.instance;

import com.yangzhongli.sp.dao.base.MyMapper;
import com.yangzhongli.sp.dao.dto.UserDTO;
import com.yangzhongli.sp.dao.po.WechatUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Mapper
@Component
public interface WechatUserMapper extends MyMapper<WechatUser> {

    /**
     * 获取用户信息
     * @param openId opneid
     * @return
     */
    WechatUser getWechatUser(String openId);

    /**
     * 统计用户数量
     * @param strTime 开始时间
     * @param endTime 结束时间
     * @return
     */
    Long wechatUserCount(@Param("strTime") String strTime, @Param("endTime") String endTime);

    /**selectBackUserList
     * 后台获取用户列表
     * @param map 用户姓名，用户电话 、用户创建时间 （时间段筛选，strTime,endTime）
     * @return
     */
    List<UserDTO> selectBackUserList(Map<String,Object> map);

    WechatUser selectByKey(String id);

    /**
     * 系统管理用户登陆
     * @return
     */
    String userLogin(@Param("userName") String userName,@Param("password") String password);
}