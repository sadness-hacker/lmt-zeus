package com.lmt.zeus.parent;

import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @description 接口调用返回统一的Result类
 * @author bazhandao
 * @date 2018/11/8 17:34
 * @since JDK1.8
 */
@Getter
@ToString(callSuper = true)
public class Result<T> implements Serializable {

    /**
     * 调用是否成功:
     * false-调用出错，服务异常，data值为null,code值不为1,msg为错误信息;
     * true-调用正常，服务正常，data有值（未查到数据时为null值）code为1,msg为成功或无值;
     */
    private boolean success = true;
    /**
     * 错误码:
     * 1-成功
     * 非1-失败
     */
    private int code = 1;
    /**
     * 错误信息
     */
    private String msg = "success";
    /**
     * 返回值:
     * 失败时为null
     * 成功时为返回值
     */
    private T data;

    public Result() {

    }

    public Result(T data) {
        this.data = data;
    }

    /**
     * 设置返回数据
     * @param data
     */
    public void setData(T data) {
        this.data = data;
    }

    /**
     * 设置错误码，错误信息
     * @param code
     * @param msg
     */
    public void setError(int code, String msg) {
        this.success = false;
        this.code = code;
        this.msg = msg;
    }

}
