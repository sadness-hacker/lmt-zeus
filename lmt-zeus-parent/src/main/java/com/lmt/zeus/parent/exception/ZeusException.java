package com.lmt.zeus.parent.exception;

import java.util.HashMap;
import java.util.Map;

/**
 * @description 基础异常类
 * @author bazhandao
 * @date 2018/11/8 17:40
 * @since JDK1.8
 */
public class ZeusException extends RuntimeException{
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

    private ZeusException() {
        this.code = ZeusExceptionEnum.SYS_ERR.getCode();
        this.msg = ZeusExceptionEnum.SYS_ERR.getMsg();
    }

    private ZeusException(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private ZeusException(int code, String msg, Throwable e) {
        super(e);
        this.code = code;
        this.msg = msg;
    }

    private ZeusException(Throwable t) {
        super(t);
        this.code = ZeusExceptionEnum.SYS_ERR.getCode();
        this.msg = ZeusExceptionEnum.SYS_ERR.getMsg();
    }

    /**
     * 创建默认异常
     * @author bazhandao
     * @date 2018-11-05
     * @return
     */
    public static ZeusException wrap() {
        return new ZeusException();
    }
    /**
     * 根据code/msg创建异常
     * @author bazhandao
     * @date 2018-11-05
     * @return
     */
    public static ZeusException wrap(int code, String msg) {
        return new ZeusException(code, msg);
    }
    /**
     * 根据code/msg/exception创建异常
     * @author bazhandao
     * @date 2018-11-05
     * @return
     */
    public static ZeusException wrap(int code, String msg, Throwable e) {
        return new ZeusException(code, msg, e);
    }
    /**
     * 根据exception创建异常,code/msg为默认值(9999,"系统异常")
     * @author bazhandao
     * @date 2018-11-05
     * @return
     */
    public static ZeusException wrap(Throwable e) {
        return new ZeusException(e);
    }

    /**
     * 设置附加异常信息
     * @author bazhandao
     * @date 2018-11-12
     * @param key
     * @param value
     */
    public ZeusException set(String key, Object value) {
        this.attachment.put(key, value);
        return this;
    }

    /**
     * 设置所有附加信息
     * @author bazhandao
     * @date 2018-11-12
     * @param map
     * @return
     */
    public ZeusException setAll(Map<String, Object> map) {
        this.attachment.putAll(map);
        return this;
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

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public Map<String, Object> getAttachment() {
        return attachment;
    }
}
