package com.yangzhongli.sp.dao.instance;

import com.yangzhongli.sp.dao.base.MyMapper;
import com.yangzhongli.sp.dao.dto.ContestantDTO;
import com.yangzhongli.sp.dao.po.Contestant;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @ClassName ShareMapper
 * @descripetion TODO
 * @Author liyanbing
 * @Date 2019-04-03
 */

@Mapper
@Component
public interface ContestantMapper extends MyMapper<Contestant> {

    /**
     * 查询所有列表
     * @param userId
     * @return
     */
    List<ContestantDTO> selectContestantList(@Param("userId") String userId);
    /**
     * 查询排名前15列表
     * @param userId
     * @return
     */
    List<ContestantDTO> selectLimit(@Param("userId") String userId);

    /**
     * 获取单个信息
     * @param id
     * @param userId 用户ID
     * @return
     */
    ContestantDTO getContestant(@Param("id") String id, @Param("userId") String userId);

    /**
     * 获取列表最大的code
     * @return
     */
    Integer getCode();

    /**
     * 后台列表
     * @return
     */
    List<ContestantDTO> backList();
}
