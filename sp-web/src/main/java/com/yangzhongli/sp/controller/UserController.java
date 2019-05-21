package com.yangzhongli.sp.controller;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.yangzhongli.sp.constants.BaseConstants;
import com.yangzhongli.sp.constants.JsonResult;
import com.yangzhongli.sp.constants.StatusConstants;
import com.yangzhongli.sp.service.api.WechatUserService;
import com.yangzhongli.sp.service.bo.WechatUserVO;
import com.yangzhongli.sp.service.dto.wechat.WxAuthDTO;
import com.yangzhongli.sp.service.dto.wechat.WxPhoneDTO;
import com.yangzhongli.sp.service.dto.wechat.WxUserInfo;
import com.yangzhongli.sp.service.dto.wechat.WxUserInfoDetail;
import com.yangzhongli.sp.utils.*;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/beauty/user")
@Slf4j
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Value("${miniprogram.appid:default}")
    private String MINIPROGRAM_APPID;

    @Value("${miniprogram.appsecret:default}")
    private String MINIPROGRAM_APPSECRET;

    @Autowired
    private WechatUserService wechatUserService;

    @ApiOperation(value = "登陆测试")
    @RequestMapping(value = "/loginTest", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public JsonResult microAppLogin(HttpServletRequest request) {
        logger.info("microAppLogin=================loginTest");
        request.getSession().setAttribute(BaseConstants.LOGIN_USER, "c7017f2fa816486bbeafd98319736044");
        return JsonResult.ok(request.getSession().getId());
    }


    @PostMapping(value = "/phone")
    @ResponseBody
    public JsonResult notifyResult(@RequestBody String xmlPhoneData, HttpServletRequest request) {
        String userId = (String) request.getSession().getAttribute(BaseConstants.LOGIN_USER);
        if (StringUtils.isEmpty(userId)) {
            return JsonResult.result(StatusConstants.USER_NOT_FOND);
        }
        WechatUserVO w  = wechatUserService.selectByKey(userId);
        if(null !=w &&!StringUtils.isEmpty(w.getPhone())){
            return JsonResult.ok(w.getPhone());
        }
        String sessionKey = (String) request.getSession().getAttribute(BaseConstants.SESSION_KEY);
        log.info("sessionKey-------------"+sessionKey);
        Gson gson = new Gson();
        log.info("--{}--", xmlPhoneData);
        WxUserInfo wxUserInfo = gson.fromJson(xmlPhoneData, WxUserInfo.class);
        log.info("--{}--", wxUserInfo);
        if (null == wxUserInfo
                || StringUtils.isTrimEmpty(wxUserInfo.getIv())
                || wxUserInfo.getEncryptedData() == null)
            return JsonResult.paramFail();
        try {
            AES aes = new AES();
            byte[] resultByte = aes.decrypt(Base64.decodeBase64(wxUserInfo.getEncryptedData()),
                    Base64.decodeBase64(sessionKey),
                    Base64.decodeBase64(wxUserInfo.getIv()));

            if (null == resultByte || resultByte.length <= 0)
                return JsonResult.result(StatusConstants.USER_INFO_PASER_ERROR);

            String encPhoneInfo = new String(WxPKCS7Encoder.decode(resultByte));
            WxPhoneDTO  wxPhoneDTO = gson.fromJson(encPhoneInfo, WxPhoneDTO.class);
            log.info("wxPhoneDTO==================" + wxPhoneDTO);
            if (wxPhoneDTO == null) {
                log.info("--wxPhoneDTO--", "解析失败");
                return JsonResult.result(StatusConstants.USER_INFO_PASER_ERROR);
            }
            wechatUserService.update(userId, wxPhoneDTO.getPhoneNumber());//国外的手机有区号
            return JsonResult.ok(wxPhoneDTO.getPhoneNumber());
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        return JsonResult.defFail("操作失败！");
    }

    /**
     * 小程序的登陆
     * 2018年07月05日 创建用户的时候不保存头像等信息 可以让小程序在用户授权之前先登陆
     *
     * @param params
     * @return
     */
    @ApiOperation(value = "小程序的登陆",notes = "用户信息")
    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public JsonResult microAppLogin(@RequestBody Map<String, Object> params, HttpServletRequest request) {
        logger.info("microAppLogin=================login");
        String code = params.get("code").toString();
        try {
            //根据appid、appsecret、code，调用为微信接口获取：session_key 和openid等信息
            logger.info("请求参数=================MINIPROGRAM_APPID"+MINIPROGRAM_APPID+"；MINIPROGRAM_APPSECRET"+MINIPROGRAM_APPSECRET+"；code"+code);
            String oauth2SessionKey = Oauth2Util.getOauth2SessionKey(MINIPROGRAM_APPID, MINIPROGRAM_APPSECRET, code);
            logger.info("oauth2SessionKey================="+oauth2SessionKey);
            Gson gson = new Gson();
            //解析微信返回的json数据
            WxAuthDTO wxAuthDTO = gson.fromJson(oauth2SessionKey, WxAuthDTO.class);
            logger.info("================解析微信返回的json数据:"+wxAuthDTO);
            if (null == wxAuthDTO
                    || null != wxAuthDTO.getErrcode()
                    || StringUtils.isTrimEmpty(wxAuthDTO.getOpenid()))
                return JsonResult.result(StatusConstants.LOGIN_CODE_ERROR, request.getSession().getId());

            WechatUserVO info = null;
            if (!StringUtils.isTrimEmpty(wxAuthDTO.getOpenid())) {
                info = wechatUserService.getOpenId(wxAuthDTO.getOpenid());
                logger.info("================查询服务器是否有用户:" + info);
            }
            request.getSession().setAttribute(BaseConstants.SESSION_KEY, wxAuthDTO.getSession_key());
            if (null == info) {
                // 如果账户不存在，走创建登录流程
                return signUp(params, request);
            }
            // 自动登录
            request.getSession().setAttribute(BaseConstants.LOGIN_USER, info.getId());
            logger.info("================查询服务器有用户LOGIN_USER:" + request.getSession().getAttribute(BaseConstants.LOGIN_USER));
            return JsonResult.ok(getLoginResult(info, request));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return JsonResult.defFail();
    }

    public JsonResult signUp(Map<String, Object> params, HttpServletRequest request) {
        try {
            String wxUserInfoStr = params.get("userInfo").toString();
            Gson gson = new Gson();
            logger.info("--{}--", wxUserInfoStr);
            //String myJson = gson.toJson(wxUserInfoStr);//将gson转化为json
            WxUserInfo wxUserInfo = gson.fromJson(wxUserInfoStr, WxUserInfo.class);
            logger.info("--{}--", wxUserInfo);
            if (null == wxUserInfo
                    || StringUtils.isTrimEmpty(wxUserInfo.getIv())
                    || wxUserInfo.getEncryptedData() == null)
                return JsonResult.paramFail();

            WxUserInfoDetail userInfoDetail = null;
            String sessionKey = request.getSession().getAttribute(BaseConstants.SESSION_KEY).toString();
            try {
                AES aes = new AES();
                byte[] resultByte = aes.decrypt(Base64.decodeBase64(wxUserInfo.getEncryptedData()),
                        Base64.decodeBase64(sessionKey),
                        Base64.decodeBase64(wxUserInfo.getIv()));

                if (null == resultByte || resultByte.length <= 0)
                    return JsonResult.result(StatusConstants.USER_INFO_PASER_ERROR);

                String encUserInfo = new String(WxPKCS7Encoder.decode(resultByte));
                logger.info("encUserInfo=================="+encUserInfo);
                userInfoDetail = gson.fromJson(encUserInfo, WxUserInfoDetail.class);
                logger.info("userInfoDetail=================="+userInfoDetail);
            } catch (Exception e) {
                logger.error(e.getMessage());
            }

            if (userInfoDetail == null) {
                logger.info("--WxUserInfoDTO--", "解析失败");
                return JsonResult.result(StatusConstants.USER_INFO_PASER_ERROR);
            }

            WechatUserVO info = wechatUserService.getOpenId(userInfoDetail.getOpenId());
            if(null == info) {
                info = new WechatUserVO();
                // 创建用户
                info.setNickName(userInfoDetail.getNickName());
                info.setOpenId(userInfoDetail.getOpenId());
                info.setAvatarUrl(userInfoDetail.getAvatarUrl());
                info.setCountry(userInfoDetail.getCountry());
                info.setProvince(userInfoDetail.getProvince());
                info.setCity(userInfoDetail.getCity());
                info.setGender(Integer.parseInt(userInfoDetail.getGender()));
                info.setCreateTime(new Date());
                info.setUpdateTime(new Date());
                info.setId(IdUtils.creatUUID());
                info = wechatUserService.saveWechatUser(info);
            }
            //  自动登录
            request.getSession().setAttribute(BaseConstants.LOGIN_USER, info.getId());
            logger.info("新添加LOGIN_USER==========="+request.getSession().getAttribute(BaseConstants.LOGIN_USER));
            return JsonResult.ok(getLoginResult(info, request));
        } catch (JsonSyntaxException e) {
            logger.error(e.getMessage(), e);
        }
        return JsonResult.result(StatusConstants.STATUS_SIGN_ERROR, request.getSession().getId());
    }

    private Map<String, Object> getLoginResult(WechatUserVO info, HttpServletRequest request) {
        Map<String, Object> result = new HashMap<>();
        result.put("token", request.getSession().getId());
        result.put("userId", info.getId());
        result.put("isNewUser", false);
        result.put("headImg", info.getAvatarUrl());
        result.put("nickname", info.getNickName());
        result.put("city", info.getCity());
        result.put("phone", info.getPhone());
        return result;
    }

}
