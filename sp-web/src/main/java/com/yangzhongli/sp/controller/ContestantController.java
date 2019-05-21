package com.yangzhongli.sp.controller;

import com.yangzhongli.sp.annotation.LoginRequired;
import com.yangzhongli.sp.constants.BaseConstants;
import com.yangzhongli.sp.constants.JsonResult;
import com.yangzhongli.sp.constants.StatusConstants;
import com.yangzhongli.sp.service.api.ContestantService;
import com.yangzhongli.sp.service.api.RuleService;
import com.yangzhongli.sp.service.bo.ContestantVO;
import com.yangzhongli.sp.service.bo.EditVO;
import com.yangzhongli.sp.service.bo.LikesRecordVO;
import com.yangzhongli.sp.service.bo.RuleVO;
import com.yangzhongli.sp.utils.AliOssUtil;
import com.yangzhongli.sp.utils.ImgUtils;
import com.yangzhongli.sp.utils.StringUtils;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;

/**
 * @ClassName ShareController
 * @descripetion TODO
 * @Author liyanbing
 * @Date 2019-04-03
 */
@Controller
@RequestMapping("/beauty/contestant")
@Slf4j
public class ContestantController {

    @Value("${miniprogram.appid:default}")
    private String MINIPROGRAM_APPID;

    @Value("${miniprogram.appsecret:default}")
    private String MINIPROGRAM_APPSECRET;

    @Autowired
    private ContestantService contestantService;
    @Autowired
    private RuleService ruleService;

    @ApiOperation(value = "文件上传", notes = "根据MultipartFile来上传文件")
    @ApiImplicitParam(name = "file", value = "文件", required = true, dataType = "MultipartFile", paramType = "MultipartFile")
    @PostMapping(value = "/uploadFile")
    @ResponseBody
    public JsonResult uploadBlog(@RequestBody MultipartFile file) {
        log.info("uploadFile()文件上传");
        //服务器文件地址
        String fileUrl = "/usr/local/application/new-beauty-8087/img/" + file.getOriginalFilename();
        //存放在oss的路径
        String ossUrl = System.currentTimeMillis() + ".png";
        System.out.println("----------new ----" + fileUrl);
        try {
            if (file != null) {
                if (!"".equals(fileUrl.trim())) {
                    File newFile = new File(fileUrl);
                    FileOutputStream os = new FileOutputStream(newFile);
                    os.write(file.getBytes());
                    os.close();
                    file.transferTo(newFile);
                    String newFilePath = ImgUtils.changSmall(newFile);//压缩后的图片
                    // 上传到OSS
                    String uploadUrl = AliOssUtil.upload(ossUrl, new File(newFilePath));
                    log.info("uploadUrl======" + uploadUrl);
                    AliOssUtil.deleteServerFile(fileUrl);//删除服务器图片
                    AliOssUtil.deleteServerFile(newFilePath);//删除服务器图片
                    return JsonResult.ok(uploadUrl);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return JsonResult.defFail("文件上传失败!文件不能超过5M");
    }


    @ApiOperation(value = "添加", notes = "picture=照片；name=昵称；phone=电话；occupation=职业；declaration=宣言；likesAmount=点赞总数；readAmount=访问量")
    @ApiImplicitParam(name = "ContestantVO", value = "实体类")
    @LoginRequired
    @ResponseBody
    @PostMapping("save")
    public JsonResult save(@RequestBody ContestantVO contestantVO, HttpServletRequest request) {
        log.info("contestantVO:"+contestantVO);
        String userId = (String) request.getSession().getAttribute(BaseConstants.LOGIN_USER);
        if (!StringUtils.isEmpty(userId)) {
            contestantVO.setUserId(userId);
        } else {
            return JsonResult.result(StatusConstants.USER_NOT_FOND);
        }
        try {
            return JsonResult.ok(contestantService.save(contestantVO));
        } catch (Exception e) {
            log.error("save()" + e);
            e.printStackTrace();
        }
        return JsonResult.defFail("发布失败");
    }

    @ApiOperation(value = "查询前15列表", notes = "id;name=昵称；shareUrl=图片；likesAmount=点赞总数；readAmount=访问量;isLikes=(0未点赞，1已经点赞)")
    @LoginRequired
    @ResponseBody
    @GetMapping("selectLimit")
    public JsonResult selectLimit(HttpServletRequest request) {
        String userId = (String) request.getSession().getAttribute(BaseConstants.LOGIN_USER);
        if (!StringUtils.isEmpty(userId)) {
            try {
                return JsonResult.ok(contestantService.selectLimit(userId));
            } catch (Exception e) {
                log.error("selectLimit()" + e);
                e.printStackTrace();
            }
        } else {
            return JsonResult.result(StatusConstants.USER_NOT_FOND);
        }

        return JsonResult.defFail("查询失败");
    }

    @ApiOperation(value = "查询所有列表", notes = "id;name=昵称；shareUrl=图片；likesAmount=点赞总数；readAmount=访问量;isLikes=(0未点赞，1已经点赞)")
    @LoginRequired
    @ResponseBody
    @GetMapping("list")
    public JsonResult selectContestantList(HttpServletRequest request, Integer pageNum, Integer pageSize) {
        String userId = (String) request.getSession().getAttribute(BaseConstants.LOGIN_USER);
        if (!StringUtils.isEmpty(userId)) {
            try {
                return JsonResult.ok(contestantService.selectContestantList(userId, pageNum, pageSize));
            } catch (Exception e) {
                log.error("selectContestantList()" + e);
                e.printStackTrace();
            }
        } else {
            return JsonResult.result(StatusConstants.USER_NOT_FOND);
        }

        return JsonResult.defFail("查询失败");
    }


    @ApiOperation(value = "获取单个信息", notes = "id;name=昵称；phone=电话；occupation=职业；declaration=宣言；likesAmount=点赞总数；readAmount=访问量;isLikes=(0未点赞，1已经点赞)")
    @LoginRequired
    @ResponseBody
    @GetMapping("get")
    public JsonResult getContestant(HttpServletRequest request, String id) {
        String userId = (String) request.getSession().getAttribute(BaseConstants.LOGIN_USER);
        if (!StringUtils.isEmpty(userId)) {
            try {
                ContestantVO contestantVO = contestantService.getContestant(id, userId);
                return JsonResult.ok(contestantVO);
            } catch (Exception e) {
                log.error("getContestant()" + e);
                e.printStackTrace();
            }
        } else {
            return JsonResult.result(StatusConstants.USER_NOT_FOND);
        }
        return JsonResult.defFail("获取失败");
    }


    @ApiOperation(value = "更新点赞", notes = "id=列表likesRecordId；ContestantId=列表的id;")
    @ApiImplicitParam(name = "LikesRecordVO", value = "实体类")
    @LoginRequired
    @ResponseBody
    @PostMapping("updateLikes")
    public JsonResult updateLikes(@RequestBody LikesRecordVO likesRecordVO, HttpServletRequest request) {
        String userId = (String) request.getSession().getAttribute(BaseConstants.LOGIN_USER);
        if (!StringUtils.isEmpty(userId)) {
            likesRecordVO.setUserId(userId);
            try {
                return JsonResult.ok(contestantService.saveLikesRecord(likesRecordVO));
            } catch (Exception e) {
                log.error("updateLikes()" + e);
                e.printStackTrace();
            }
        } else {
            return JsonResult.result(StatusConstants.USER_NOT_FOND);
        }
        return JsonResult.defFail("更新点赞失败");
    }

    @ApiOperation(value = "添加规则", notes = "reward=奖励；participation=参与方式；conditions=条件；voteRule=投票规则；rewardRule=奖励规则；promulgate=活动公布时间地点；活动开始时间=strTime；活动结束时间=endTime")
    @ApiImplicitParam(name = "RuleVO", value = "实体类")
    @LoginRequired
    @ResponseBody
    @PostMapping("saveRule")
    public JsonResult saveRule(@RequestBody RuleVO ruleVO, HttpServletRequest request) {
        try {
            return JsonResult.ok(ruleService.save(ruleVO));
        } catch (Exception e) {
            log.error("saveRule()" + e);
            e.printStackTrace();
        }
        return JsonResult.defFail("添加规则失败");
    }


    @ApiOperation(value = "查询规则", notes = "reward=奖励；participation=参与方式；conditions=条件；voteRule=投票规则；rewardRule=奖励规则；promulgate=活动公布时间地点；活动开始时间=strTime；活动结束时间=endTime")
    @ApiImplicitParam(name = "RuleVO", value = "实体类")
    @LoginRequired
    @ResponseBody
    @GetMapping("getRule")
    public JsonResult getRule() {
        try {
            return JsonResult.ok(ruleService.getRule());
        } catch (Exception e) {
            log.error("getRule()" + e);
            e.printStackTrace();
        }
        return JsonResult.defFail("获取规则失败");
    }

    @ApiOperation(value = "后台查询所有列表", notes = "id;name=昵称；shareUrl=图片；likesAmount=点赞总数；readAmount=访问量;isLikes=(0未点赞，1已经点赞)")
    @LoginRequired
    @ResponseBody
    @GetMapping("backList")
    public JsonResult backList(Integer pageNum, Integer pageSize) {
        try {
            return JsonResult.ok(contestantService.backList(pageNum, pageSize));
        } catch (Exception e) {
            log.error("backList()" + e);
            e.printStackTrace();
        }

        return JsonResult.defFail("查询失败");
    }

    @ApiOperation(value = "编辑规则", notes = "reward=奖励；participation=参与方式；conditions=条件；voteRule=投票规则；rewardRule=奖励规则；promulgate=活动公布时间地点；活动开始时间=strTime；活动结束时间=endTime")
    @ApiImplicitParam(name = "id", value = "id")
    @LoginRequired
    @ResponseBody
    @GetMapping("ruleEdit")
    public JsonResult ruleEdit(String id) {
        try {
            return JsonResult.ok(ruleService.ruleEdit(id));
        } catch (Exception e) {
            log.error("ruleEdit()" + e);
            e.printStackTrace();
        }
        return JsonResult.defFail("获取规则失败");
    }

    @ApiOperation(value = "删除")
    @ApiImplicitParam(name = "id", value = "id")
    @LoginRequired
    @ResponseBody
    @PostMapping("delContestant")
    public JsonResult delContestant(@RequestBody EditVO editVO) {
        try {
            return JsonResult.ok(contestantService.del(editVO.getId()));
        } catch (Exception e) {
            log.error("delContestant()" + e);
            e.printStackTrace();
        }
        return JsonResult.defFail("删除失败");
    }
}
