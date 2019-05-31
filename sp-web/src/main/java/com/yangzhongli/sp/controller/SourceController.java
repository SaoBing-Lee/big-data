package com.yangzhongli.sp.controller;

import com.yangzhongli.sp.constants.JsonResult;
import com.yangzhongli.sp.service.api.SourceAnalysisService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName SourceController
 * @descripetion TODO
 * @Author liyanbing
 * @Date 2019-05-30
 */
@Slf4j
@Controller
@RequestMapping("/bigData/source")
public class SourceController {

    @Autowired
    private SourceAnalysisService sourceAnalysisService;


    @CrossOrigin
//    @LoginRequired
    @ApiOperation(value = "来源分析  根据应用id、时间段，查询应用来源分析 趋势图的集合")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "appId", value = "应用ID"),
            @ApiImplicitParam(name = "strDate", value = "开始时间"),
            @ApiImplicitParam(name = "endDate", value = "结束时间")
    })
    @GetMapping("selectSource")
    @ResponseBody
    public JsonResult selectSource(String appId, String strDate, String endDate) {
        try {
            return JsonResult.ok(sourceAnalysisService.selectSource(appId, strDate, endDate));
        } catch (Exception e) {
            log.error("selectSource():" + e);
            e.printStackTrace();
        }
        return JsonResult.defFail("获取应用来源分析趋势图失败！");
    }


    @CrossOrigin
//    @LoginRequired
    @ApiOperation(value = "来源分析  根据应用id、时间段，查询应用来源分析 列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "appId", value = "应用ID"),
            @ApiImplicitParam(name = "pageNum", value = "大小"),
            @ApiImplicitParam(name = "pageSize", value = "尺寸"),
            @ApiImplicitParam(name = "strDate", value = "开始时间"),
            @ApiImplicitParam(name = "endDate", value = "结束时间")
    })
    @GetMapping("selectSourceList")
    @ResponseBody
    public JsonResult selectSourceList(Integer pageNum, Integer pageSize, String appId, String strDate, String endDate) {
        try {
            return JsonResult.ok(sourceAnalysisService.selectSourceList(pageNum, pageSize, appId, strDate, endDate));
        } catch (Exception e) {
            log.error("selectSourceList():" + e);
            e.printStackTrace();
        }
        return JsonResult.defFail("查询应用来源分析 列表失败！");
    }
}
