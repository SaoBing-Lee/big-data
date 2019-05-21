package com.yangzhongli.sp.controller;

import com.yangzhongli.sp.annotation.LoginRequired;
import com.yangzhongli.sp.constants.BaseConstants;
import com.yangzhongli.sp.constants.JsonResult;
import com.yangzhongli.sp.service.api.WechatUserService;
import com.yangzhongli.sp.service.bo.LoginVO;
import com.yangzhongli.sp.service.bo.WechatUserVO;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping("/beauty/weChatUser")
@Slf4j
public class WechatUserController {

    @Autowired
    private WechatUserService wechatUserService;

    @ApiOperation(value = "后台登陆")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName", value = "用户名"),
            @ApiImplicitParam(name = "password", value = "密码")
    })
    @PostMapping(value = "backLogin",consumes = "application/json;charset=UTF-8")
    @ResponseBody
    public JsonResult backLogin(@RequestBody LoginVO loginVO, HttpServletRequest request) {
        log.info(loginVO.getUserName()+"============");
        log.info(loginVO.getPassword()+"============");
        if ( null == loginVO || StringUtils.isEmpty(loginVO.getUserName()) || StringUtils.isEmpty(loginVO.getPassword())) {
            return JsonResult.defFail("账号和密码必须填写!");
        }
        try {
            if("new_beauty_admin".equals(loginVO.getUserName())){
                String userId = wechatUserService.userLogin(loginVO.getUserName(), loginVO.getPassword());
                if (!StringUtils.isEmpty(userId)) {
                    request.getSession().setAttribute(BaseConstants.LOGIN_USER, userId);
                    return JsonResult.ok(1);
                }
            }
        } catch (Exception e) {
            log.info("backLogin()" + e);
        }
        return JsonResult.defFail("账号或者密码错误！");
    }
    @LoginRequired
    @GetMapping("getWechatUser")
    @ResponseBody
    public JsonResult getWechatUser(String openId, HttpServletRequest request) {
        WechatUserVO wechatUser = null;
        String id = request.getSession().getId();
        if (StringUtils.isEmpty(id)) {
            try {
                wechatUser = wechatUserService.getOpenId(openId);
                request.setAttribute("wechatUser", wechatUser);
                return JsonResult.ok(wechatUser);
            } catch (Exception e) {
                log.error("getWechatUser():" + e);
                e.printStackTrace();
            }
        }
        return JsonResult.defFail("获取用户信息失败");
    }
    @LoginRequired
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页码", dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", value = "大小", dataType = "Integer"),
            @ApiImplicitParam(name = "str", value = "用户姓名和电话=str、用户创建时间段（strTime,endTime）", dataType = "Map<String, Object>")
    })
    @GetMapping("selectBackUserList")
    @ResponseBody
    public JsonResult selectBackUserList(Integer pageNum, Integer pageSize, String str) {
        Map<String, Object> map = new HashMap<>(3);
        if (!StringUtils.isEmpty(str)) {
            map.put("str", str);
        }
        try {
            return JsonResult.ok(wechatUserService.selectBackUserList(pageNum, pageSize, map));
        } catch (Exception e) {
            log.error("selectBackUserList():" + e);
            e.printStackTrace();
        }
        return JsonResult.defFail("获取用户信息失败");
    }
}
