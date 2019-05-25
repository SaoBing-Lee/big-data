package com.yangzhongli.sp.controller;

import com.yangzhongli.sp.annotation.LoginRequired;
import com.yangzhongli.sp.constants.BaseConstants;
import com.yangzhongli.sp.constants.JsonResult;
import com.yangzhongli.sp.service.api.AppRoleService;
import com.yangzhongli.sp.service.api.UserService;
import com.yangzhongli.sp.service.bo.*;
import com.yangzhongli.sp.utils.MD5Util;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
@RequestMapping("/bigData/weChatUser")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AppRoleService appRoleService;

    @ApiOperation(value = "添加权限")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName", value = "用户名"),
            @ApiImplicitParam(name = "password", value = "密码")
    })
    @PostMapping(value = "saveAppRole")
    @ResponseBody
    @LoginRequired
    public JsonResult saveAppRole(@RequestBody AppRoleVO appRoleVO) {
        try {
            return JsonResult.ok(appRoleService.save(appRoleVO));
        } catch (Exception e) {
            log.info("saveAppRole()" + e);
        }
        return JsonResult.defFail("添加小程序失败！");
    }

    @LoginRequired
    @ApiOperation(value = "批量删除小程序")
    @ApiImplicitParam(name = "ids", value = "String [] ids")
    @PostMapping(value = "delAppRole")
    @ResponseBody
    public JsonResult delAppRole(@RequestBody DelVO delVO) {
        if (null == delVO) {
            return JsonResult.defFail("参数为空");
        }
        try {
            return JsonResult.ok(appRoleService.delAppRole(delVO.getIds()));
        } catch (Exception e) {
            log.error("delAppRole():" + e);
            e.printStackTrace();
        }
        return JsonResult.defFail("批量删除小程序失败");
    }

    @LoginRequired
    @ApiOperation(value = "获取所有小程序信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页码", dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", value = "大小", dataType = "Integer")
    })
    @GetMapping("selectAppRole")
    @ResponseBody
    public JsonResult selectAppRole(Integer pageNum, Integer pageSize) {
        try {
            return JsonResult.ok(appRoleService.select(pageNum, pageSize));
        } catch (Exception e) {
            log.error("selectAppRole():" + e);
            e.printStackTrace();
        }
        return JsonResult.defFail("获取所有小程序信息失败");
    }

    @ApiOperation(value = "添加用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName", value = "用户名"),
            @ApiImplicitParam(name = "password", value = "密码")
    })
    @PostMapping(value = "saveUser")
    @ResponseBody
    public JsonResult saveUser(@RequestBody UserVO userVO, HttpServletRequest request) {
        String userId = (String) request.getSession().getAttribute(BaseConstants.LOGIN_USER);
        UserVO u = userService.getUserById(userId);
        if(null == u){
            return JsonResult.defFail("当前用户非超级管理员！无添加用户权限");
        }
        log.info(userVO.getName() + "============");
        log.info(userVO.getPassword() + "============");
        if (null == userVO || StringUtils.isEmpty(userVO.getName()) || StringUtils.isEmpty(userVO.getPassword())) {
            return JsonResult.defFail("账号和密码必须填写!");
        }
        try {
            boolean nameFlag = userService.nameIsNot(userVO.getName());//判断用户名是否存在
            if (nameFlag) {
                return JsonResult.defFail("用户名已存在");
            } else {
                userVO.setPassword(MD5Util.getMD5(userVO.getPassword(), false, 32)); //密码加密
                userService.saveUser(userVO);
                return JsonResult.ok();
            }
        } catch (Exception e) {
            log.info("saveUser()" + e);
        }
        return JsonResult.defFail("用户添加失败！");
    }

    @ApiOperation(value = "获取编辑用户和权限")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户id")
    })
    @PostMapping(value = "selectByKey")
    @ResponseBody
    public JsonResult selectByKey(String id) {
        try {
            return JsonResult.ok(userService.selectByKey(id));
        } catch (Exception e) {
            log.info("selectByKey()" + e);
        }
        return JsonResult.defFail("获取用户信息失败！");
    }


    @ApiOperation(value = "编辑用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id"),
            @ApiImplicitParam(name = "appRoleId", value = "权限id(小程序id)")
    })
    @PostMapping(value = "updateRole")
    @ResponseBody
    public JsonResult updateRole(@RequestBody List<UserAppRoleVO> list) {
        try {
            userService.update(list);
        } catch (Exception e) {
            log.info("updateRole()" + e);
        }
        return JsonResult.defFail("编辑用户失败！");
    }


    @ApiOperation(value = "后台登陆")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName", value = "用户名"),
            @ApiImplicitParam(name = "password", value = "密码")
    })
    @PostMapping(value = "backLogin", consumes = "application/json;charset=UTF-8")
    @ResponseBody
    public JsonResult backLogin(@RequestBody LoginVO loginVO, HttpServletRequest request) {
        log.info(loginVO.getUserName() + "============");
        log.info(loginVO.getPassword() + "============");
        if (null == loginVO || StringUtils.isEmpty(loginVO.getUserName()) || StringUtils.isEmpty(loginVO.getPassword())) {
            return JsonResult.defFail("账号和密码必须填写!");
        }
        try {
            String userId = userService.userLogin(loginVO.getUserName(), loginVO.getPassword());
            if (!StringUtils.isEmpty(userId)) {
                request.getSession().setAttribute(BaseConstants.LOGIN_USER, userId);
                return JsonResult.ok(1);
            }
        } catch (Exception e) {
            log.info("backLogin()" + e);
        }
        return JsonResult.defFail("账号或者密码错误！");
    }

    @ApiOperation(value = "登陆后获取用户对应的权限")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id")
    })
    @PostMapping(value = "loginRole", consumes = "application/json;charset=UTF-8")
    @ResponseBody
    public JsonResult loginRole(String userId) {
        try {
            if (!StringUtils.isEmpty(userId)) {
                return JsonResult.ok(appRoleService.getUserRoleList(userId));
            }
        } catch (Exception e) {
            log.info("loginRole()" + e);
        }
        return JsonResult.defFail("登陆后获取用户对应的权限失败");
    }
//
//    @ApiOperation(value = "确认用户使用权限，存应用ID")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "appId", value = "应用ID")
//    })
//    @PostMapping(value = "getRole", consumes = "application/json;charset=UTF-8")
//    @ResponseBody
//    public JsonResult getRole(String appId , HttpServletRequest request) {
//        try {
//            if (!StringUtils.isEmpty(appId)) {
//                request.getSession().setAttribute(BaseConstants.LOGIN_USER_ROLE, appId);
//                return JsonResult.ok();
//            }
//        } catch (Exception e) {
//            log.info("getRole()" + e);
//        }
//        return JsonResult.defFail("确认用户使用权限");
//    }

    @LoginRequired
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页码", dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", value = "大小", dataType = "Integer")
    })
    @GetMapping("selectUserList")
    @ResponseBody
    public JsonResult selectUserList(Integer pageNum, Integer pageSize) {
        try {
            return JsonResult.ok(userService.selectUserList(pageNum, pageSize));
        } catch (Exception e) {
            log.error("selectUserList():" + e);
            e.printStackTrace();
        }
        return JsonResult.defFail("获取用户信息失败");
    }


}
