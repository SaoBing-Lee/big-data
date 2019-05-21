package com.yangzhongli.sp.constants;

/**
 * insert description here
 *
 * @author liuxiaohua
 * @since 2018/8/17 22:50
 */
public class ResultBuilder<T extends JsonResult> implements BaseBuilder {

    private T result;

    public ResultBuilder(T result) {
        this.result = result;
    }

    public ResultBuilder status(int status) {
        result.setStatus(status);
        return this;
    }

    public ResultBuilder description(String description) {
        result.setDescription(description);
        return this;
    }

    public ResultBuilder data(Object data) {
        result.setData(data);
        return this;
    }

    @Override
    public T build() {
        return this.result;
    }

}
