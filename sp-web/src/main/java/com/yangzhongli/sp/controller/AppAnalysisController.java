package com.yangzhongli.sp.controller;

import com.yangzhongli.sp.constants.JsonResult;
import com.yangzhongli.sp.service.api.DayAnalysisService;
import com.yangzhongli.sp.service.api.DuringAnalysisService;
import com.yangzhongli.sp.service.api.UserAccessService;
import com.yangzhongli.sp.service.bo.DuringAnalysisVO;
import com.yangzhongli.sp.utils.ExportUtil;
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

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.*;

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

    @CrossOrigin
    //    @LoginRequired
    @ApiOperation(value = "获取应用实时数据总访问量")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "appId", value = "应用ID")
    })
    @GetMapping("getUserAccess")
    @ResponseBody
    public JsonResult getUserAccess(String appId) {
        log.info("=====" + appId);
        try {
            return JsonResult.ok(userAccessService.get(appId));
        } catch (Exception e) {
            log.error("getUserAccess():" + e);
            e.printStackTrace();
        }
        return JsonResult.defFail("获取访问总数据失败！");
    }

    //    @LoginRequired
    @ApiOperation(value = "获取应用实时数据 当天，昨天和7天前访问量趋势图；")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "appId", value = "应用ID"),
            @ApiImplicitParam(name = "type", value = "1=访问次数;2=访问人数;3=打开次数,4=新增人数,5=平均在线时长")
    })
    @GetMapping("selectDuringAnalysis")
    @ResponseBody
    public JsonResult selectDuringAnalysis(String appId, int type) {
        try {
            return JsonResult.ok(duringAnalysisService.selectDuringAnalysis(appId, type));
        } catch (Exception e) {
            log.error("selectDuringAnalysis():" + e);
            e.printStackTrace();
        }
        return JsonResult.defFail("获取访问详细数据失败！");
    }


    @CrossOrigin
    //    @LoginRequired
    @ApiOperation(value = "根据当天时间 获取应用分析的 实时数据 的详情数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "appId", value = "应用ID")
    })
    @GetMapping("selectDuringAnalysisDetail")
    @ResponseBody
    public JsonResult selectDuringAnalysisDetail(String appId) {
        try {
            return JsonResult.ok(duringAnalysisService.selectDuringAnalysisDetail(appId));
        } catch (Exception e) {
            log.error("selectDuringAnalysisDetail():" + e);
            e.printStackTrace();
        }
        return JsonResult.defFail("获取应用分析的实时数据的详情数据失败！");
    }


    @CrossOrigin
    //    @LoginRequired
    @ApiOperation(value = "获取应用历史数据 当天 趋势图")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "appId", value = "应用ID"),
            @ApiImplicitParam(name = "strDate", value = "开始时间"),
            @ApiImplicitParam(name = "endDate", value = "结束时间"),
            @ApiImplicitParam(name = "type", value = "1=访问次数;2=访问人数;3=打开次数,4=新增人数,5=平均在线时长")
    })
    @GetMapping("selectHistoryDuring")
    @ResponseBody
    public JsonResult selectHistoryDuring(String appId, String strDate, String endDate,int type) {
        try {
            return JsonResult.ok(duringAnalysisService.selectHistoryDuring(appId, strDate, endDate,type));
        } catch (Exception e) {
            log.error("selectHistoryDuring():" + e);
            e.printStackTrace();
        }
        return JsonResult.defFail("获取历史数据趋势图失败！");
    }

    @CrossOrigin
    //    @LoginRequired
    @ApiOperation(value = "获取应用历史数据 7天，14天等的数据 趋势图")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "appId", value = "应用ID"),
            @ApiImplicitParam(name = "strDate", value = "开始时间"),
            @ApiImplicitParam(name = "endDate", value = "结束时间"),
            @ApiImplicitParam(name = "type", value = "1=访问次数;2=访问人数;3=打开次数,4=新增人数,5=平均在线时长")
    })
    @GetMapping("selectHistoryDay")
    @ResponseBody
    public JsonResult selectHistoryDay(String appId, String strDate, String endDate,int type) {
        try {
            return JsonResult.ok(dayAnalysisService.selectDay(appId, strDate, endDate,type));
        } catch (Exception e) {
            log.error("selectHistoryDay():" + e);
            e.printStackTrace();
        }
        return JsonResult.defFail("获取历史数据趋势图失败！");
    }


    @CrossOrigin
    //    @LoginRequired
    @ApiOperation(value = "获取应用分析的历史数据的详情数据--分页")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "appId", value = "应用ID"),
            @ApiImplicitParam(name = "pageNum", value = "大小"),
            @ApiImplicitParam(name = "pageSize", value = "尺寸")
    })
    @GetMapping("selectDayList")
    @ResponseBody
    public JsonResult selectDayList(Integer pageNum, Integer pageSize, String appId, String strDate, String endDate) {
        try {
            return JsonResult.ok(dayAnalysisService.selectDayList(pageNum, pageSize, appId,strDate,endDate));
        } catch (Exception e) {
            log.error("selectDayList():" + e);
            e.printStackTrace();
        }
        return JsonResult.defFail("获取应用分析的实时数据的详情数据失败！");
    }

    @CrossOrigin
    @GetMapping("/file")
    @ResponseBody
    public ExportUtil download(HttpServletResponse response, String appId) {
        List<Map<String, Object>> dataList;

        List<DuringAnalysisVO> list = duringAnalysisService.selectDuringAnalysisDetail(appId);// 查询到要导出的信息

        if (list.size() == 0) {
            JsonResult.defFail("无数据导出");
        }
        String sTitle = "时间段,访问次数,打开次数,访问人数,新增人数,平均在线时长";
        String fName = "log_";
        String mapKey = "time,visits,opens,accessPerson,newPerson,onlineMean";
        dataList = new ArrayList<>();
        Map<String, Object> map;
        for (DuringAnalysisVO duringAnalysisVO : list) {
            map = new HashMap<>();

            map.put("time", duringAnalysisVO.getTime());
            map.put("visits", duringAnalysisVO.getVisits());
            map.put("opens", duringAnalysisVO.getOpens());
            map.put("accessPerson", duringAnalysisVO.getAccessPerson());
            map.put("newPerson", duringAnalysisVO.getNewPerson());
            map.put("onlineMean", duringAnalysisVO.getOnlineMean());

            dataList.add(map);
        }
        try (final OutputStream os = response.getOutputStream()) {
            ExportUtil.responseSetProperties(fName, response);
            ExportUtil.doExport(dataList, sTitle, mapKey, os);
        } catch (Exception e) {
            log.error("生成csv文件失败", e);
        }
        return null;
    }

//    public ExcelBean exportJobVacancy(SpecialIDReq req) {
//        ExcelBean excelBean = new ExcelBean();
//        List<FairJobVacancyResp> respList = findJobFairDetailsList(req.getFairId(), req.getCompanyId());
//        Map<String,String> header = new LinkedHashMap<>();
//        header.put("jobName","职位名称");
//        header.put("city","坐标城市");
//        header.put("salary","薪资范围");
//        header.put("nature","职位性质");
//        header.put("edu","学历要求");
//        header.put("subject","专业要求");
//        header.put("jobNum","招聘人数");
//        header.put("number","收到简历");
//
//        List<Map<String,Object>> dataList = new ArrayList<>();
//        for (FairJobVacancyResp resp : respList) {
//            Map<String,Object> data = new LinkedHashMap<>();
//            data.put("jobName",resp.getJobName());
//            data.put("city",resp.getCity());
//            data.put("salary", SalaryLevel.getSalary(resp.getSalary()));
//            data.put("nature", JobNature.getNature(resp.getNature()));
//            data.put("edu", Education.getEducation(resp.getEdu()).getName());
//            data.put("subject",resp.getSubject());
//            data.put("jobNum",resp.getJobNum());
//            data.put("number",resp.getNumber());
//            dataList.add(data);
//        }
//
//        excelBean.setName("大型招聘会企业招聘职位列表");
//        excelBean.setHeader(header);
//        excelBean.setData(dataList);
//        return excelBean;
//    }

}
