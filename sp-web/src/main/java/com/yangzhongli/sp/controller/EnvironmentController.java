package com.yangzhongli.sp.controller;

import com.yangzhongli.sp.annotation.LoginRequired;
import com.yangzhongli.sp.constants.JsonResult;
import com.yangzhongli.sp.service.api.DeviceService;
import com.yangzhongli.sp.service.api.NetworkEnvironmentService;
import com.yangzhongli.sp.service.api.RegionalAnalysisService;
import com.yangzhongli.sp.service.api.SystemService;
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
 * @ClassName EnvironmentController
 * @descripetion TODO
 * @Author liyanbing
 * @Date 2019-05-27
 */
@Controller
@RequestMapping("/bigData/en")
@Slf4j
public class EnvironmentController {

    @Autowired
    private RegionalAnalysisService regionalAnalysisService;
    @Autowired
    private SystemService systemService;
    @Autowired
    private NetworkEnvironmentService networkEnvironmentService;
    @Autowired
    private DeviceService deviceService;

    @CrossOrigin
//    @LoginRequired
    @ApiOperation(value = "地域分析  根据应用id和日期 获取地图信息的")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "appId", value = "应用ID"),
            @ApiImplicitParam(name = "strDate", value = "开始时间"),
            @ApiImplicitParam(name = "endDate", value = "结束时间"),
            @ApiImplicitParam(name = "type", value = "1=访问次数;2=访问人数;3=打开次数,4=新增人数")
    })
    @GetMapping("selectProvinceAmountList")
    @ResponseBody
    public JsonResult selectProvinceAmountList(String appId, String strDate, String endDate, int type) {
        try {
            return JsonResult.ok(regionalAnalysisService.selectProvinceAmountList(appId, strDate, endDate, type));
        } catch (Exception e) {
            log.error("selectProvinceAmountList():" + e);
            e.printStackTrace();
        }
        return JsonResult.defFail("获取地图信息数据图失败！");
    }

    @CrossOrigin
//    @LoginRequired
    @ApiOperation(value = "地域分析 根据应用id和日期 获取省份列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "appId", value = "应用ID"),
            @ApiImplicitParam(name = "pageNum", value = "大小"),
            @ApiImplicitParam(name = "pageSize", value = "尺寸")
    })
    @GetMapping("selectProvinceList")
    @ResponseBody
    public JsonResult selectProvinceList(Integer pageNum, Integer pageSize, String appId, String strDate, String endDate) {
        try {
            return JsonResult.ok(regionalAnalysisService.selectProvinceList(pageNum, pageSize, appId, strDate, endDate));
        } catch (Exception e) {
            log.error("selectProvinceList():" + e);
            e.printStackTrace();
        }
        return JsonResult.defFail("获取省份列表数据信息失败！");
    }

    @CrossOrigin
//    @LoginRequired
    @ApiOperation(value = "地域分析 根据应用id和日期 获取省份列表中的  市列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "appId", value = "应用ID"),
            @ApiImplicitParam(name = "strDate", value = "开始时间"),
            @ApiImplicitParam(name = "endDate", value = "结束时间"),
            @ApiImplicitParam(name = "province", value = "省名")
    })
    @GetMapping("selectCityList")
    @ResponseBody
    public JsonResult selectCityList(String appId, String strDate, String endDate, String province) {
        try {
            return JsonResult.ok(regionalAnalysisService.selectCityList(appId, strDate, endDate, province));
        } catch (Exception e) {
            log.error("selectCityList():" + e);
            e.printStackTrace();
        }
        return JsonResult.defFail("获取省份列表中的市列表信息失败！");
    }

    @CrossOrigin
//    @LoginRequired
    @ApiOperation(value = "地域分析 根据应用id、时间段、省份名字，查询当前省 省份趋势图")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "appId", value = "应用ID"),
            @ApiImplicitParam(name = "strDate", value = "开始时间"),
            @ApiImplicitParam(name = "endDate", value = "结束时间"),
            @ApiImplicitParam(name = "province", value = "省名"),
            @ApiImplicitParam(name = "type", value = "1=访问次数;2=访问人数;3=打开次数,4=新增人数")
    })
    @GetMapping("selectProAmountList")
    @ResponseBody
    public JsonResult selectProAmountList(String appId, String strDate, String endDate, String province, int type) {
        try {
            return JsonResult.ok(regionalAnalysisService.selectProAmountList(appId, strDate, endDate, province, type));
        } catch (Exception e) {
            log.error("selectProAmountList():" + e);
            e.printStackTrace();
        }
        return JsonResult.defFail("查询当前省份趋势图信息失败！");
    }

    @CrossOrigin
//    @LoginRequired
    @ApiOperation(value = "地域分析 根据应用id、时间段、省份名字，查询当前省 省份详细信息列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "appId", value = "应用ID"),
            @ApiImplicitParam(name = "pageNum", value = "大小"),
            @ApiImplicitParam(name = "pageSize", value = "尺寸"),
            @ApiImplicitParam(name = "strDate", value = "开始时间"),
            @ApiImplicitParam(name = "endDate", value = "结束时间"),
            @ApiImplicitParam(name = "province", value = "省名")
    })
    @GetMapping("selectProvinceDetailList")
    @ResponseBody
    public JsonResult selectProvinceDetailList(Integer pageNum, Integer pageSize, String appId, String strDate, String endDate, String province) {
        try {
            return JsonResult.ok(regionalAnalysisService.selectProvinceDetailList(pageNum, pageSize, appId, strDate, endDate, province));
        } catch (Exception e) {
            log.error("selectProvinceDetailList():" + e);
            e.printStackTrace();
        }
        return JsonResult.defFail("查询当前省份详细信息列表信息失败！");
    }


//    @LoginRequired
    @ApiOperation(value = " 地域分析 根据应用id、时间段、市名字，查询当前省 时间段信息 市趋势图")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "appId", value = "应用ID"),
            @ApiImplicitParam(name = "strDate", value = "开始时间"),
            @ApiImplicitParam(name = "endDate", value = "结束时间"),
            @ApiImplicitParam(name = "city", value = "city"),
            @ApiImplicitParam(name = "type", value = "1=访问次数;2=访问人数;3=打开次数,4=新增人数")
    })
    @GetMapping("selectCityDetailAmountList")
    @ResponseBody
    public JsonResult selectCityDetailAmountList(String appId, String strDate, String endDate, String city, int type) {
        try {
            return JsonResult.ok(regionalAnalysisService.selectCityDetailAmountList(appId, strDate, endDate, city, type));
        } catch (Exception e) {
            log.error("selectCityDetailAmountList():" + e);
            e.printStackTrace();
        }
        return JsonResult.defFail("查询当前省份趋势图信息失败！");
    }

    @CrossOrigin
//    @LoginRequired
    @ApiOperation(value = " 地域分析 根据应用id、时间段、市名字，查询当前省 市详情数据列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "appId", value = "应用ID"),
            @ApiImplicitParam(name = "pageNum", value = "大小"),
            @ApiImplicitParam(name = "pageSize", value = "尺寸"),
            @ApiImplicitParam(name = "strDate", value = "开始时间"),
            @ApiImplicitParam(name = "endDate", value = "结束时间"),
            @ApiImplicitParam(name = "city", value = "市")
    })
    @GetMapping("selectCityDetailList")
    @ResponseBody
    public JsonResult selectCityDetailList(Integer pageNum, Integer pageSize, String appId, String strDate, String endDate, String city) {
        try {
            return JsonResult.ok(regionalAnalysisService.selectCityDetailList(pageNum, pageSize, appId, strDate, endDate, city));
        } catch (Exception e) {
            log.error("selectCityDetailList():" + e);
            e.printStackTrace();
        }
        return JsonResult.defFail("查询当前省市详情数据列表信息失败！");
    }
    @CrossOrigin
//    @LoginRequired
    @ApiOperation(value = "终端分析 根据应用id、时间段,统计分析终端 趋势图")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "appId", value = "应用ID"),
            @ApiImplicitParam(name = "strDate", value = "开始时间"),
            @ApiImplicitParam(name = "endDate", value = "结束时间"),
            @ApiImplicitParam(name = "type", value = "1=访问次数;2=访问人数;3=打开次数")
    })
    @GetMapping("selectSystem")
    @ResponseBody
    public JsonResult selectSystem(String appId, String strDate, String endDate, int type) {
        try {
            return JsonResult.ok(systemService.selectSystem(appId, strDate, endDate, type));
        } catch (Exception e) {
            log.error("selectSystem():" + e);
            e.printStackTrace();
        }
        return JsonResult.defFail("获取统计分析终端趋势图信息失败！");
    }
    @CrossOrigin
//    @LoginRequired
    @ApiOperation(value = "终端分析 根据应用id、时间段,统计分析终端 详细数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "appId", value = "应用ID"),
            @ApiImplicitParam(name = "strDate", value = "开始时间"),
            @ApiImplicitParam(name = "endDate", value = "结束时间")
    })
    @GetMapping("selectSystemList")
    @ResponseBody
    public JsonResult selectSystemList(String appId, String strDate, String endDate) {
        try {
            return JsonResult.ok(systemService.selectSystemList(appId, strDate, endDate));
        } catch (Exception e) {
            log.error("selectSystemList():" + e);
            e.printStackTrace();
        }
        return JsonResult.defFail("获取统计分析终端详细数据信息失败！");
    }

    @CrossOrigin
//    @LoginRequired
    @ApiOperation(value = "终端分析 根据应用id、系统名称、时间段,统计分析终端详细信息 趋势图")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "appId", value = "应用ID"),
            @ApiImplicitParam(name = "strDate", value = "开始时间"),
            @ApiImplicitParam(name = "endDate", value = "结束时间"),
            @ApiImplicitParam(name = "systemPhone", value = "手机系统ios,安卓"),
            @ApiImplicitParam(name = "type", value = "1=访问次数;2=访问人数;3=打开次数,4=新增人数")
    })
    @GetMapping("selectSystemDetail")
    @ResponseBody
    public JsonResult selectSystemDetail(String appId, String strDate, String endDate, String systemPhone, int type) {
        try {
            return JsonResult.ok(systemService.selectSystemDetail(appId, strDate, endDate, systemPhone, type));
        } catch (Exception e) {
            log.error("selectSystemDetail():" + e);
            e.printStackTrace();
        }
        return JsonResult.defFail("获取统计分析终端详细信息趋势图失败！");
    }

    @CrossOrigin
//    @LoginRequired
    @ApiOperation(value = "终端分析 根据应用id、系统名称、时间段,统计分析终端详细信息 列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "appId", value = "应用ID"),
            @ApiImplicitParam(name = "pageNum", value = "大小"),
            @ApiImplicitParam(name = "pageSize", value = "尺寸"),
            @ApiImplicitParam(name = "strDate", value = "开始时间"),
            @ApiImplicitParam(name = "endDate", value = "结束时间"),
            @ApiImplicitParam(name = "systemPhone", value = "手机系统ios,安卓")
    })
    @GetMapping("selectSystemDetailList")
    @ResponseBody
    public JsonResult selectSystemDetailList(Integer pageNum, Integer pageSize, String appId, String strDate, String endDate, String systemPhone) {
        try {
            return JsonResult.ok(systemService.selectSystemDetailList(pageNum, pageSize, appId, strDate, endDate, systemPhone));
        } catch (Exception e) {
            log.error("selectSystemDetailList():" + e);
            e.printStackTrace();
        }
        return JsonResult.defFail("查询统计分析终端详细信息列表失败！");
    }

    @CrossOrigin
//    @LoginRequired
    @ApiOperation(value = "网络分析 根据应用id、时间段，查询应用网络环境 趋势图")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "appId", value = "应用ID"),
            @ApiImplicitParam(name = "strDate", value = "开始时间"),
            @ApiImplicitParam(name = "endDate", value = "结束时间"),
            @ApiImplicitParam(name = "type", value = "1=访问次数;2=访问人数;3=打开次数")
    })
    @GetMapping("selectNetwork")
    @ResponseBody
    public JsonResult selectNetwork(String appId, String strDate, String endDate, int type) {
        try {
            return JsonResult.ok(networkEnvironmentService.selectNetwork(appId, strDate, endDate, type));
        } catch (Exception e) {
            log.error("selectNetwork():" + e);
            e.printStackTrace();
        }
        return JsonResult.defFail("获取应用网络环境趋势图信息失败！");
    }
    @CrossOrigin
//    @LoginRequired
    @ApiOperation(value = "网络分析  根据应用id、时间段，查询应用网络环境 列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "appId", value = "应用ID"),
            @ApiImplicitParam(name = "strDate", value = "开始时间"),
            @ApiImplicitParam(name = "endDate", value = "结束时间")
    })
    @GetMapping("selectNetworkList")
    @ResponseBody
    public JsonResult selectNetworkList(String appId, String strDate, String endDate) {
        try {
            return JsonResult.ok(networkEnvironmentService.selectNetworkList(appId, strDate, endDate));
        } catch (Exception e) {
            log.error("selectNetworkList():" + e);
            e.printStackTrace();
        }
        return JsonResult.defFail("获取应用网络环境列表信息失败！");
    }

    @CrossOrigin
//    @LoginRequired
    @ApiOperation(value = "网络分析 根据应用id、时间段，查询应用网络环境详细信息 趋势图")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "appId", value = "应用ID"),
            @ApiImplicitParam(name = "strDate", value = "开始时间"),
            @ApiImplicitParam(name = "endDate", value = "结束时间"),
            @ApiImplicitParam(name = "systemPhone", value = "手机系统ios,安卓"),
            @ApiImplicitParam(name = "type", value = "1=访问次数;2=访问人数;3=打开次数,4=新增人数")
    })
    @GetMapping("selectNetworkDetail")
    @ResponseBody
    public JsonResult selectNetworkDetail(String appId, String strDate, String endDate, String systemPhone, int type) {
        try {
            return JsonResult.ok(networkEnvironmentService.selectNetworkDetail(appId, strDate, endDate, systemPhone, type));
        } catch (Exception e) {
            log.error("selectNetworkDetail():" + e);
            e.printStackTrace();
        }
        return JsonResult.defFail("获取应用网络环境详细信息趋势图失败！");
    }

    @CrossOrigin
//    @LoginRequired
    @ApiOperation(value = "网络分析  根据应用id、时间段，查询应用网络环境详细信息 列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "appId", value = "应用ID"),
            @ApiImplicitParam(name = "pageNum", value = "大小"),
            @ApiImplicitParam(name = "pageSize", value = "尺寸"),
            @ApiImplicitParam(name = "strDate", value = "开始时间"),
            @ApiImplicitParam(name = "endDate", value = "结束时间"),
            @ApiImplicitParam(name = "systemPhone", value = "手机系统ios,安卓")
    })
    @GetMapping("selectNetworkDetailList")
    @ResponseBody
    public JsonResult selectNetworkDetailList(Integer pageNum, Integer pageSize, String appId, String strDate, String endDate, String systemPhone) {
        try {
            return JsonResult.ok(networkEnvironmentService.selectNetworkDetailList(pageNum, pageSize, appId, strDate, endDate, systemPhone));
        } catch (Exception e) {
            log.error("selectNetworkDetailList():" + e);
            e.printStackTrace();
        }
        return JsonResult.defFail("查询应用网络环境详细信息列表失败！");
    }

//------------------- 机型分析  -------------------

    @CrossOrigin
//    @LoginRequired
    @ApiOperation(value = " 机型分析 根据应用id、时间段，查询应用机型分析 趋势图")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "appId", value = "应用ID"),
            @ApiImplicitParam(name = "strDate", value = "开始时间"),
            @ApiImplicitParam(name = "endDate", value = "结束时间"),
            @ApiImplicitParam(name = "type", value = "1=访问次数;2=访问人数;3=打开次数")
    })
    @GetMapping("selectDevice")
    @ResponseBody
    public JsonResult selectDevice(String appId, String strDate, String endDate, int type) {
        try {
            return JsonResult.ok(deviceService.selectDevice(appId, strDate, endDate, type));
        } catch (Exception e) {
            log.error("selectDevice():" + e);
            e.printStackTrace();
        }
        return JsonResult.defFail("获取应用机型分析趋势图信息失败！");
    }
    @CrossOrigin
//    @LoginRequired
    @ApiOperation(value = "机型分析  根据应用id、时间段，查询应用机型分析列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "appId", value = "应用ID"),
            @ApiImplicitParam(name = "strDate", value = "开始时间"),
            @ApiImplicitParam(name = "endDate", value = "结束时间")
    })
    @GetMapping("selectDeviceList")
    @ResponseBody
    public JsonResult selectDeviceList(Integer pageNum, Integer pageSize, String appId, String strDate, String endDate) {
        try {
            return JsonResult.ok(deviceService.selectDeviceList(pageNum, pageSize, appId, strDate, endDate));
        } catch (Exception e) {
            log.error("selectDeviceList():" + e);
            e.printStackTrace();
        }
        return JsonResult.defFail("获取应用机型分析列表信息失败！");
    }

    @CrossOrigin
//    @LoginRequired
    @ApiOperation(value = "机型分析 根据应用id、时间段，查询应用机型分析详细信息 趋势图")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "appId", value = "应用ID"),
            @ApiImplicitParam(name = "strDate", value = "开始时间"),
            @ApiImplicitParam(name = "endDate", value = "结束时间"),
            @ApiImplicitParam(name = "device", value = "手机机型"),
            @ApiImplicitParam(name = "type", value = "1=访问次数;2=访问人数;3=打开次数,4=新增人数")
    })
    @GetMapping("selectDeviceDetail")
    @ResponseBody
    public JsonResult selectDeviceDetail(String appId, String strDate, String endDate, String device, int type) {
        try {
            return JsonResult.ok(deviceService.selectDeviceDetail(appId, strDate, endDate, device, type));
        } catch (Exception e) {
            log.error("selectDeviceDetail():" + e);
            e.printStackTrace();
        }
        return JsonResult.defFail("获取应用机型分析详细信息趋势图失败！");
    }

    @CrossOrigin
//    @LoginRequired
    @ApiOperation(value = "机型分析  根据应用id、时间段，查询应用机型分析详细信息 列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "appId", value = "应用ID"),
            @ApiImplicitParam(name = "pageNum", value = "大小"),
            @ApiImplicitParam(name = "pageSize", value = "尺寸"),
            @ApiImplicitParam(name = "strDate", value = "开始时间"),
            @ApiImplicitParam(name = "endDate", value = "结束时间"),
            @ApiImplicitParam(name = "device", value = "机型")
    })
    @GetMapping("selectDeviceDetailList")
    @ResponseBody
    public JsonResult selectDeviceDetailList(Integer pageNum, Integer pageSize, String appId, String strDate, String endDate, String device) {
        try {
            return JsonResult.ok(deviceService.selectDeviceDetailList(pageNum, pageSize, appId, strDate, endDate, device));
        } catch (Exception e) {
            log.error("selectDeviceDetailList():" + e);
            e.printStackTrace();
        }
        return JsonResult.defFail("查询应用机型分析详细信息列表失败！");
    }


}
