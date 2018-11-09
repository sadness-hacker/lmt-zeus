package com.lmt.parent.client.exception;

import com.lmt.parent.client.enums.BasicExceptionEnum;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * @description 基础异常类
 * @author bazhandao
 * @date 2018/11/8 17:40
 * @since JDK1.8
 */
@Getter
public class BasicException extends RuntimeException{
    /**
     * 异常码
     */
    private int code;

    /**
     * 异常信息
     */
    private String msg;

    /**
     * 异常附件信息
     */
    private Map<String, Object> attachment = new HashMap<String, Object>();

    private BasicException() {
        this.code = BasicExceptionEnum.SYS_ERR.getCode();
        this.msg = BasicExceptionEnum.SYS_ERR.getMsg();
    }

    private BasicException(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private BasicException(int code, String msg, Throwable e) {
        super(e);
        this.code = code;
        this.msg = msg;
    }

    private BasicException(Throwable t) {
        super(t);
        this.code = BasicExceptionEnum.SYS_ERR.getCode();
        this.msg = BasicExceptionEnum.SYS_ERR.getMsg();
    }

    /**
     * 创建默认异常
     * @author bazhandao
     * @date 2018-11-05
     * @return
     */
    public static BasicException wrap() {
        return new BasicException();
    }
    /**
     * 根据code/msg创建异常
     * @author bazhandao
     * @date 2018-11-05
     * @return
     */
    public static BasicException wrap(int code, String msg) {
        return new BasicException(code, msg);
    }
    /**
     * 根据code/msg/exception创建异常
     * @author bazhandao
     * @date 2018-11-05
     * @return
     */
    public static BasicException wrap(int code, String msg, Throwable e) {
        return new BasicException(code, msg, e);
    }
    /**
     * 根据exception创建异常,code/msg为默认值(9999,"系统异常")
     * @author bazhandao
     * @date 2018-11-05
     * @return
     */
    public static BasicException wrap(Throwable e) {
        return new BasicException(e);
    }

    /**
     * 设置附加异常信息
     * @param key
     * @param value
     */
    public void set(String key, String value) {
        attachment.put(key, value);
    }

    /**
     * 覆盖toString方法
     * @author bazhandao
     * @date 2018-11-05
     * @return
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("code=").append(code).append("\n")
                .append("msg=").append(msg).append("\n");
        attachment.forEach((k, v) -> {
            builder.append(k).append("=").append(v).append("\n");
        });
        return builder.append(super.toString()).toString();
    }
}
