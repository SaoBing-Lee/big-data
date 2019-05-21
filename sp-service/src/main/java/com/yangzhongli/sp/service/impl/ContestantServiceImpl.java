package com.yangzhongli.sp.service.impl;

import com.github.pagehelper.PageInfo;
import com.yangzhongli.sp.dao.dto.ContestantDTO;
import com.yangzhongli.sp.dao.instance.ContestantMapper;
import com.yangzhongli.sp.dao.instance.LikesRecordMapper;
import com.yangzhongli.sp.dao.po.Contestant;
import com.yangzhongli.sp.dao.po.LikesRecord;
import com.yangzhongli.sp.service.api.ContestantService;
import com.yangzhongli.sp.service.bo.ContestantVO;
import com.yangzhongli.sp.service.bo.LikesRecordVO;
import com.yangzhongli.sp.utils.ConverterUtil;
import com.yangzhongli.sp.utils.IdUtils;
import com.yangzhongli.sp.utils.PageUtils;
import com.yangzhongli.sp.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @ClassName ShareServiceImpl
 * @descripetion TODO
 * @Author liyanbing
 * @Date 2019-04-03
 */
@Service
@Slf4j
public class ContestantServiceImpl implements ContestantService {

    @Autowired
    private ContestantMapper contestantMapper;
    @Autowired
    private LikesRecordMapper likesRecordMapper;

    @Override
    public Map<String,Object> save(ContestantVO contestantVO) {
        String resCode = "NO.";
        Map<String,Object> map = new HashMap<String, Object>(2);
        if (null != contestantVO) {
            contestantVO.setUpdateTime(new Date());
            contestantVO.setInfoPicture(ConverterUtil.changeToString(contestantVO.getInfoPictureStr()));
            if (StringUtils.isEmpty(contestantVO.getId())) {
                log.info("contestantVO如何:------ "+ contestantVO.getId());
                //获取最大的code
                Integer code = contestantMapper.getCode();
                if (null != code) {
                    contestantVO.setCode(code + 1);
                } else {
                    contestantVO.setCode(1);
                }
                contestantVO.setId(IdUtils.creatUUID());
                contestantVO.setCreateTime(new Date());
                contestantVO.setLikesAmount(0L);
                contestantVO.setReadAmount(0L);
                contestantMapper.insert(ConverterUtil.convert(Contestant.class, contestantVO));
                log.info("contestantVO------:" + contestantVO.getId());
            } else {
                contestantMapper.updateByPrimaryKeySelective(ConverterUtil.convert(Contestant.class, contestantVO));
            }
        }
        resCode += contestantVO.getCode();
        map.put("resCode",resCode);
        map.put("id",contestantVO.getId());
        return map;
    }

    @Override
    public Long saveLikesRecord(LikesRecordVO likesRecordVO) {
        Long likesCount = 0L;
        Long readAmount = 0L;
        if (null != likesRecordVO) {
            //查询用户点赞数量
            Contestant contestant = contestantMapper.selectByPrimaryKey(likesRecordVO.getContestantId());
            Random r = new Random();
            int random = r.nextInt(6);
            System.out.println("随机数：" + r.nextInt(6));
            if (null != contestant) {
                likesCount = contestant.getLikesAmount();
                if (StringUtils.isEmpty(likesRecordVO.getId())) {
                    likesRecordVO.setUpdateTime(new Date());
                    likesRecordVO.setId(IdUtils.creatUUID());
                    likesRecordVO.setCreateTime(new Date());
                    int i = likesRecordMapper.insert(ConverterUtil.convert(LikesRecord.class, likesRecordVO));
                    if (i > 0) {
                        likesCount = likesCount + 1;
                    }
                    readAmount = contestant.getReadAmount() + random;
                } else {
                    //根据用户id和分享id删除分享记录
                    int d = likesRecordMapper.delete(ConverterUtil.convert(LikesRecord.class, likesRecordVO));
                    if (d > 0) {
                        likesCount = likesCount - 1;
                    }
                }
            }
            contestant.setLikesAmount(likesCount);
            contestant.setReadAmount(readAmount);
            contestantMapper.updateByPrimaryKeySelective(contestant);
        }
        return likesCount;
    }

    @Override
    public List<ContestantVO> selectLimit(String userId) {
        List<ContestantDTO> list = contestantMapper.selectLimit(userId);
        return ConverterUtil.convert(ContestantVO.class, list);
    }

    @Override
    public PageInfo<ContestantVO> selectContestantList(String userId, Integer pageNum, Integer pageSize) {
        PageUtils.startPage(pageNum, pageSize);
        List<ContestantDTO> contestantDTOS = contestantMapper.selectContestantList(userId);
        return PageUtils.getPageInfo(contestantDTOS, ContestantVO.class);
    }

    @Override
    public ContestantVO getContestant(String id, String userId) {
        ContestantDTO contestantDTO = contestantMapper.getContestant(id, userId);
        return ConverterUtil.convert(ContestantVO.class, contestantDTO);
    }

    @Override
    public ContestantVO get(String id) {
        Contestant contestant = contestantMapper.selectByPrimaryKey(id);
        return ConverterUtil.convert(ContestantVO.class, contestant);
    }

    @Override
    public int del(String id) {
        return contestantMapper.deleteByPrimaryKey(id);
    }

    @Override
    public PageInfo<ContestantVO> backList(Integer pageNum, Integer pageSize) {
        PageUtils.startPage(pageNum, pageSize);
        List<ContestantDTO> contestantDTOS = contestantMapper.backList();
        return PageUtils.getPageInfo(contestantDTOS, ContestantVO.class);
    }


}
