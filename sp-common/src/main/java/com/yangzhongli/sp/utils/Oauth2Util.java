package com.yangzhongli.sp.utils;

public class Oauth2Util {

    /**
     * 小程序获取用户信息接口
     */
    public static final String wxLoginUrl =
            "https://api.weixin.qq.com/sns/jscode2session?" +
                    "appid=%s&" +
                    "secret=%s&" +
                    "js_code=%s&" +
                    "grant_type=authorization_code";

    /**
     * 小程序获token
     */
    public static final String WX_GET_USER_TOKEN =
            "https://api.weixin.qq.com/cgi-bin/token?" +
                    "appid=%s&" +
                    "secret=%s&" + "grant_type=client_credential";

    //微信小程序
    public static String getOauth2SessionKey(String appId, String appSecret, String code) throws Exception {
        String requestUrl = String.format(wxLoginUrl, appId, appSecret, code);
        String tokenJsonString = MessageUtil.sendWeiXinMessage(requestUrl, "GET", null);
        return tokenJsonString;
    }


    //小程序获取用户token
    public static String getOauthToken(String appId, String appSecret) throws Exception {
        String requestUrl = String.format(WX_GET_USER_TOKEN, appId, appSecret);
        String tokenJsonString = MessageUtil.sendWeiXinMessage(requestUrl, "GET", null);
        return tokenJsonString;
    }


}