package com.yangzhongli.sp.utils;

import java.io.Serializable;

/**
 * OSS
 */
public class OssResult implements Serializable {

    private static final long serialVersionUID = 8703316024680489379L;

    private static final OssResult ERROR             = new OssResult(Type.ERROR);
    private static final OssResult SUCCESS           = new OssResult(Type.SUCCESS);
    private static final OssResult PERMISSION_DENIED = new OssResult(Type.PERMISSION_DENIED);

    /**
     * 结果码
     */
    private int code;

    /**
     * 提示信息
     */
    private String msg;

    /**
     * 相关数据
     */
    private Object data;

    /**
     * 时间戳
     */
    private long time;

    private OssResult() {
    }

    private OssResult(Type type) {
        this.code = type.code;
        this.msg = type.defaultMsg;
    }

    public static OssResult error() {
        return ERROR.setTime(System.currentTimeMillis());
    }

    public static OssResult success() {
        return SUCCESS.setTime(System.currentTimeMillis());
    }

    public static OssResult permissionDenied() {
        return PERMISSION_DENIED.setTime(System.currentTimeMillis());
    }

    public int getCode() {
        return code;
    }

    private void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public OssResult setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public Object getData() {
        return data;
    }

    public OssResult setData(Object data) {
        this.data = data;
        return this;
    }

    public long getTime() {
        return time;
    }

    public OssResult setTime(long time) {
        this.time = time;
        return this;
    }

    private enum Type {
        ERROR(0, "系统异常"),
        SUCCESS(1, "操作成功!"),
        PERMISSION_DENIED(2, "没有这个权限");
        private int    code; // 访问结果类型编码
        private String defaultMsg; // 默认提示信息

        Type(int code, String defaultMsg) {
            this.code = code;
            this.defaultMsg = defaultMsg;
        }
    }
}
