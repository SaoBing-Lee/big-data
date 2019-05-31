package com.yangzhongli.sp.controller;

import com.yangzhongli.sp.annotation.LoginRequired;
import com.yangzhongli.sp.constants.JsonResult;
import com.yangzhongli.sp.service.api.EventService;
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
 * @ClassName EventController
 * @descripetion TODO
 * @Author liyanbing
 * @Date 2019-05-29
 */
@Controller
@RequestMapping("/bigData/event")
@Slf4j
public class EventController {

    @Autowired
    private EventService eventService;
    @CrossOrigin
    //@LoginRequired
    @ApiOperation(value = "自定义事件  根据应用id、时间段，查询应用自定义事件列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "appId", value = "应用ID"),
            @ApiImplicitParam(name = "pageNum", value = "大小"),
            @ApiImplicitParam(name = "pageSize", value = "尺寸"),
            @ApiImplicitParam(name = "strDate", value = "开始时间"),
            @ApiImplicitParam(name = "endDate", value = "结束时间")
    })
    @GetMapping("selectEventAllList")
    @ResponseBody
    public JsonResult selectEventAllList(Integer pageNum, Integer pageSize, String appId, String strDate, String endDate) {
        try {
            return JsonResult.ok(eventService.selectEventAllList(pageNum, pageSize, appId, strDate, endDate));
        } catch (Exception e) {
            log.error("selectEventAllList():" + e);
            e.printStackTrace();
        }
        return JsonResult.defFail("查询应用自定义事件列表失败！");
    }

    @CrossOrigin
    //@LoginRequired
    @ApiOperation(value = "自定义事件  根据应用id、时间段，查询应用自定义事件详情趋势图")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "appId", value = "应用ID"),
            @ApiImplicitParam(name = "id", value = "id"),
            @ApiImplicitParam(name = "type", value = "1=触发次数;2=触发人数"),
            @ApiImplicitParam(name = "strDate", value = "开始时间"),
            @ApiImplicitParam(name = "endDate", value = "结束时间")
    })
    @GetMapping("selectEventAllDetail")
    @ResponseBody
    public JsonResult selectEventAllList(String appId, String strDate, String endDate, String id, int type) {
        try {
            return JsonResult.ok(eventService.selectEventAllDetail(appId, strDate, endDate, id, type));
        } catch (Exception e) {
            log.error("selectEventAllDetail():" + e);
            e.printStackTrace();
        }
        return JsonResult.defFail("查询应用自定义事件详情趋势图失败！");
    }
    @CrossOrigin
//    @LoginRequired
    @ApiOperation(value = "自定义事件  根据应用id、时间段，查询应用自定义事件详情数据列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "appId", value = "应用ID"),
            @ApiImplicitParam(name = "pageNum", value = "大小"),
            @ApiImplicitParam(name = "pageSize", value = "尺寸"),
            @ApiImplicitParam(name = "strDate", value = "开始时间"),
            @ApiImplicitParam(name = "endDate", value = "结束时间")
    })
    @GetMapping("selectEventAllDetailList")
    @ResponseBody
    public JsonResult selectEventAllDetailList(Integer pageNum, Integer pageSize, String appId, String strDate, String endDate, String id) {
        try {
            return JsonResult.ok(eventService.selectEventAllDetailList(pageNum, pageSize, appId, strDate, endDate, id));
        } catch (Exception e) {
            log.error("selectEventAllDetailList():" + e);
            e.printStackTrace();
        }
        return JsonResult.defFail("查询应用自定义事件详情数据列表失败！");
    }

}
