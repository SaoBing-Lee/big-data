package com.yangzhongli.sp.service;

import com.github.pagehelper.PageInfo;
import com.yangzhongli.sp.WebApplicationTests;
import com.yangzhongli.sp.service.api.AppRoleService;
import com.yangzhongli.sp.service.api.UserService;
import com.yangzhongli.sp.service.bo.AppRoleVO;
import com.yangzhongli.sp.service.bo.UserAppRoleVO;
import com.yangzhongli.sp.service.bo.UserVO;
import com.yangzhongli.sp.utils.IdUtils;
import com.yangzhongli.sp.utils.MD5Util;
import com.yangzhongli.sp.utils.QRCodeUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * insert description here
 *
 * @author leven
 * @since 2019/1/4 21:02
 */
@Slf4j
public class UserTests extends WebApplicationTests {

    @Autowired
    private UserService userService;
    @Autowired
    private AppRoleService  appRoleService;

    @Test
    public void selectUser(){
        PageInfo<UserVO> pageInfo = userService.selectUserList(1,10);
        log.info("==========="+pageInfo.getList().get(0));
    }
    @Test
    public void saveUser(){
        UserVO userVO = new UserVO();
        userVO.setName("test");
        userVO.setPassword(MD5Util.getMD5("123456",false,16));
        List<UserAppRoleVO> list = new ArrayList<>();
        UserAppRoleVO userAppRoleVO = new UserAppRoleVO();
        userAppRoleVO.setAppRoleId("2");
        userAppRoleVO.setUserId("1");
        list.add(userAppRoleVO);
        userVO.setUserAppRoleVOS(list);
        userService.saveUser(userVO);

    }

    @Test
    public void saveAppRole(){
        AppRoleVO appRoleVO = new AppRoleVO();
        appRoleVO.setAppId("10002");
        appRoleVO.setName("点赞小程序测试");
        appRoleVO.setPicture("https://likes.com");
        appRoleVO.setCreateTime(new Date());
        appRoleVO.setUpdateTime(new Date());
        appRoleService.save(appRoleVO);

    }

    @Test
    public void t(){
        String text = "pages/details/details?id=0ab3e2a4883343cdad4ebbc0cb7dc8ce"; // 随机生成验证码
        System.out.println("随机码： " + text);
        int width = 225; // 二维码图片的宽
        int height = 225; // 二维码图片的高
        String format = "jpg"; // 二维码图片的格式

        try {
            // 生成二维码图片，并返回图片路径
//            String pathName = QRCodeUtil.generateQRCode(text, width, height, format, "/usr/local/application/kitchen-8084/img/");
//            System.out.println("生成二维码的图片路径： " + pathName);

            String content = QRCodeUtil.parseQRCode("/Users/ydm12/ideaProjects/WechatIMG1073.jpg");
            System.out.println("解析出二维码的图片的内容为： " + content);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void check(){
        String regex = "^[1-9](\\d){10}$";
        Pattern pat = Pattern.compile(regex);// 返回Pattern对象
        Matcher mat = pat.matcher("1376785009");
        System.out.println(mat.matches());

    }

    @Test
    public void selectBackUserList(){
        Random r = new Random();

        System.out.println("随机数："+r.nextInt(6));
        System.out.println(IdUtils.creatUUID());
//        PageInfo<WechatUserVO> pageInfo = wechatUserService.selectBackUserList(1,10,"你猜猜");
//        log.info("page============================="+pageInfo.getList().get(0));
    }


}
