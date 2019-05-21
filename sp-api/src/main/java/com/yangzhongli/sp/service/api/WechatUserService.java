package com.yangzhongli.sp.service.api;

import com.github.pagehelper.PageInfo;
import com.yangzhongli.sp.service.bo.WechatUserVO;

import java.util.Map;

public interface WechatUserService {

    WechatUserVO selectByPrimaryKey(WechatUserVO wechatUserVO);

    WechatUserVO selectByKey(String id);

    WechatUserVO getOpenId(String openId);

    WechatUserVO saveWechatUser(WechatUserVO wechatUserVO);

    /**
     * 统计用户数量
     * @param strTime 开始时间
     * @param endTime 结束时间
     * @return
     */
    Long wechatUserCount(String strTime, String endTime);

    /**
     * 后台获取用户列表
     *
     * @param map 用户姓名、电话、用户创建时间 （时间段筛选，strTime,endTime）
     * @return
     */
    PageInfo<WechatUserVO> selectBackUserList(Integer pageNum, Integer pageSize,Map<String, Object> map);


    /**
     * 用户
     * @param userName
     * @param password
     * @return
     */
    String userLogin(String userName,String password);

    /**
     * 修改用户信息
     * @param userId 用户id
     * @param phone 电话
     * @return
     */
    int update(String userId, String phone);
}
