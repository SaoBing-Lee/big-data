package com.yangzhongli.sp.service;

import com.yangzhongli.sp.WebApplicationTests;
import com.yangzhongli.sp.service.api.ContestantService;
import com.yangzhongli.sp.service.bo.LikesRecordVO;
import com.yangzhongli.sp.service.bo.ContestantVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * insert description here
 *
 * @author leven
 * @since 2019/1/4 21:02
 */
@Slf4j
public class ContestantServiceTests extends WebApplicationTests {

    @Autowired
    private ContestantService contestantService;

    @Test
    public void save() {
        ContestantVO contestantVO = new ContestantVO();
        contestantVO.setDeclaration("我要变得更帅帅帅");
        contestantVO.setName("函数安");
        contestantVO.setOccupation("工程师");
        contestantVO.setPhone("13799090877");
        contestantVO.setUserId("1");
        contestantVO.setPlasticProject("oosisiii");
        contestantService.save(contestantVO);

    }

    @Test
    public void saveLikes() {
        LikesRecordVO  likesRecordVO = new LikesRecordVO();
        likesRecordVO.setContestantId("1");
        likesRecordVO.setUserId("c7017f2fa816486bbeafd98319736044");
        contestantService.saveLikesRecord(likesRecordVO);

    }



}
