package com.yangzhongli.sp.service;

import com.github.pagehelper.PageInfo;
import com.yangzhongli.sp.WebApplicationTests;
import com.yangzhongli.sp.service.api.DayAnalysisService;
import com.yangzhongli.sp.service.api.DuringAnalysisService;
import com.yangzhongli.sp.service.api.UserAccessService;
import com.yangzhongli.sp.service.bo.DayAnalysisVO;
import com.yangzhongli.sp.service.bo.DuringAnalysisVO;
import com.yangzhongli.sp.service.bo.UserAccessVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * insert description here
 *
 * @author leven
 * @since 2019/1/4 21:02
 */
@Slf4j
public class AppTests extends WebApplicationTests {
    @Autowired
    private UserAccessService userAccessService;
    @Autowired
    private DuringAnalysisService duringAnalysisService;

    @Autowired
    private DayAnalysisService dayAnalysisService;

    @Test
    public void getUserAccess() {
        UserAccessVO  userAccessVO = userAccessService.get("500013092");
        log.info("userAccessVO========"+userAccessVO);
    }
    @Test
    public void selectDuringAnalysis() {
        Map<String, Object> map = duringAnalysisService.selectDuringAnalysis("500013092",1);
        log.info("map========"+map);
    }

    @Test
    public void selectHistoryDuring() {
        List<String> list = duringAnalysisService.selectHistoryDuring("500013092","2019-05-01","2019-05-31",1);
        log.info("list========"+list);
    }

    @Test
    public void selectDayList() {
        PageInfo<DayAnalysisVO> pageInfo= dayAnalysisService.selectDayList(1,10,"500013092","2019-05-01","2019-05-31");
        log.info("pageInfo========"+pageInfo);
    }
    @Test
    public void selectDuringAnalysisDetail() {
        List<DuringAnalysisVO> list= duringAnalysisService.selectDuringAnalysisDetail("500013092");
        log.info("list========"+list);
    }


}
