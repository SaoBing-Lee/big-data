package com.yangzhongli.sp.controller;

import com.yangzhongli.sp.annotation.LoginRequired;
import com.yangzhongli.sp.constants.JsonResult;
import com.yangzhongli.sp.service.api.DayAnalysisService;
import com.yangzhongli.sp.service.api.DuringAnalysisService;
import com.yangzhongli.sp.service.api.UserAccessService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName AppAnalysisController
 * @descripetion 应用分析模块
 * @Author liyanbing
 * @Date 2019-05-25
 */
@Controller
@RequestMapping("/bigData/app")
@Slf4j
public class AppAnalysisController {

    @Autowired
    private UserAccessService userAccessService;
    @Autowired
    private DuringAnalysisService duringAnalysisService;

    @Autowired
    private DayAnalysisService dayAnalysisService;


    @LoginRequired
    @ApiOperation(value = "获取应用实时数据总访问量")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "appId", value = "应用ID")
    })
    @GetMapping("getUserAccess")
    @ResponseBody
    public JsonResult getUserAccess(String appId) {
        try {
            return JsonResult.ok(userAccessService.get(appId));
        } catch (Exception e) {
            log.error("getUserAccess():" + e);
            e.printStackTrace();
        }
        return JsonResult.defFail("获取访问总数据失败！");
    }

    @LoginRequired
    @ApiOperation(value = "获取应用实时数据 当天，昨天和7天前访问量趋势图；详情数据--获取map:todayList")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "appId", value = "应用ID")
    })
    @GetMapping("selectDuringAnalysis")
    @ResponseBody
    public JsonResult selectDuringAnalysis(String appId) {
        try {
            return JsonResult.ok(duringAnalysisService.selectDuringAnalysis(appId));
        } catch (Exception e) {
            log.error("selectDuringAnalysis():" + e);
            e.printStackTrace();
        }
        return JsonResult.defFail("获取访问详细数据失败！");
    }


    @LoginRequired
    @ApiOperation(value = "获取应用历史数据 当天，7天前，14、30天的访问量 趋势图")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "appId", value = "应用ID"),
            @ApiImplicitParam(name = "beforeDay", value = "几天前（0=今天，，7=7天前，14=14天前，30=30天前）")
    })
    @GetMapping("selectHistoryDuring")
    @ResponseBody
    public JsonResult selectHistoryDuring(String appId, int beforeDay) {
        try {
            return JsonResult.ok(duringAnalysisService.selectHistoryDuring(appId, beforeDay));
        } catch (Exception e) {
            log.error("selectHistoryDuring():" + e);
            e.printStackTrace();
        }
        return JsonResult.defFail("获取历史数据趋势图失败！");
    }


    @LoginRequired
    @ApiOperation(value = "获取应用历史数据 当天，7天前，14、30天的访问量 趋势图")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "appId", value = "应用ID"),
            @ApiImplicitParam(name = "pageNum", value = "大小"),
            @ApiImplicitParam(name = "pageSize", value = "尺寸")
    })
    @GetMapping("selectDayList")
    @ResponseBody
    public JsonResult selectDayList(Integer pageNum, Integer pageSize, String appId) {
        try {
            return JsonResult.ok(dayAnalysisService.selectDayList(pageNum, pageSize, appId));
        } catch (Exception e) {
            log.error("selectDayList():" + e);
            e.printStackTrace();
        }
        return JsonResult.defFail("获取历史数据详情信息失败！");
    }


}
