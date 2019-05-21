package com.yangzhongli.sp.service.api;

import com.github.pagehelper.PageInfo;
import com.yangzhongli.sp.service.bo.LikesRecordVO;
import com.yangzhongli.sp.service.bo.ContestantVO;

import java.util.List;
import java.util.Map;

/**
 * @ClassName ShareService
 * @descripetion TODO
 * @Author liyanbing
 * @Date 2019-04-03
 */
public interface ContestantService {

    Map<String,Object> save(ContestantVO contestantVO);

    Long saveLikesRecord(LikesRecordVO likesRecordVO);
    /**
     * 查询前15排名列表
     *
     * @param userId
     * @return
     */
    List<ContestantVO> selectLimit(String userId);

    /**
     * 查询所有列表
     *
     * @param userId
     * @return
     */
    PageInfo<ContestantVO> selectContestantList(String userId, Integer pageNum , Integer pageSize);

    /**
     * 获取单个信息
     *
     * @param id
     * @param userId 用户ID
     * @return
     */
    ContestantVO getContestant(String id, String userId);

    ContestantVO get(String id);

    int del(String id);

    /**
     * 后台查询
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<ContestantVO> backList( Integer pageNum , Integer pageSize);
 }
