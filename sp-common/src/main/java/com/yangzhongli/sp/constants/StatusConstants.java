package com.yangzhongli.sp.constants;

public enum StatusConstants {

    FAILURE(300, "请求出错"),
    SUCCESS(200, "请求成功"),
    PARAM_ERROR(301, "请求参数错误"),


    STATUS_SIGN_ERROR(100, "用户不存在"),

    USER_NOT_LOGIN(401, "用户未登录"),
    USER_NOT_AUTH(403, "用户权限不足"),

    EQUIPMENT_DETAIL_ERROR(10002, "获取设备信息失败"),


    FILE_UPLOAD_ERROR(20000, "文件上传失败，请重试"),
    FILE_SIZE_BEYOND(20001, "文件大小超过限制"),

    USER_NOT_FOND(30000, "获取用户信息失败，请重新登录"),
    USER_CONSUMPTION_DETAIL_ERROR(30001, "获取用户消费详情失败！"),
    MEMBER_CARD_DETAIL_ERROR(30002, "获取用户会员信息失败！"),
    LOGIN_CODE_ERROR(30003, "登录失败: code无效"),
    USER_INFO_PASER_ERROR(30004, "解析用户信息失败"),
    MEMBER_CARD_APPLY(30005, "申请会员卡失败"),
    MEMBER_CARD_BIND(30006, "绑定会员卡失败"),
    USERNAME_OR_PWD_ERROR(30007, "用户名或密码出错"),

    VALIDATE_CODE_LIMIT_60(40000, "60秒内不能重复申请验证码"),
    VALIDATE_CODE_GET_ERROR(40001, "获取手机验证码失败"),
    VALIDATE_CODE_ERROR_BEYOND(40002, "验证码输入错误或超时"),
    VALIDATE_ERROR(40003, "验证手机验证码失败"),

    EQUIPMENT_START_ERROR(50000, "设备启动失败"),
    WECHAT_BROWER_OPEN(50001, "请使用微信浏览器打开"),
    WECHAT_PAYMENT_ERROR(50002, "调起微信支付失败"),
    USER_RECHARGE_RET(50003, "有用户正在充值，请稍后重试");

    private int status;
    private String description;

    StatusConstants(int status, String description) {
        this.status = status;
        this.description = description;
    }

    public int getStatus() {
        return status;
    }

    public String getDescription() {
        return description;
    }
}