package com.yangzhongli.sp.constants;

/**
 * insert description here
 *
 * @author liuxiaohua
 * @since 2018/8/17 22:19
 */
public class JsonResult {

    private int status;
    private String description;
    private Object data;

    public JsonResult(StatusConstants result) {
        this.status = result.getStatus();
        this.description = result.getDescription();
    }

    public JsonResult() { }

    public static JsonResult result(int status, String description, Object data) {
        return new ResultBuilder(new JsonResult())
                .status(status).description(description).data(data).build();
    }

    public static JsonResult ok() {
        return new JsonResult(StatusConstants.SUCCESS);
    }

    public static JsonResult ok(Object data) {
        return result(StatusConstants.SUCCESS, data);
    }

    public static JsonResult result(int status, String description) {
        return result(status, description, null);
    }

    public static JsonResult result(StatusConstants constants) {
        return result(constants.getStatus(), constants.getDescription());
    }

    public static JsonResult result(StatusConstants constants, Object data) {
        return result(constants.getStatus(), constants.getDescription(), data);
    }

    public static JsonResult defFail() {
        return result(StatusConstants.FAILURE);
    }

    public static JsonResult defFail(String description) {
        return result(StatusConstants.FAILURE.getStatus(), description);
    }

    public static JsonResult paramFail() {
        return result(StatusConstants.PARAM_ERROR);
    }

    public static JsonResult paramFail(String description) {
        return result(StatusConstants.PARAM_ERROR.getStatus(), description);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
