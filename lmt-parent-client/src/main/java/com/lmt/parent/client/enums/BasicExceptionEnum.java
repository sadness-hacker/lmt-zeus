package com.lmt.parent.client.enums;

import lombok.Getter;

/**
 * @description 基础异常枚举类
 * @author bazhandao
 * @date 2018/11/8 17:41
 * @since JDK1.8
 */
@Getter
public enum  BasicExceptionEnum {

    SYS_ERR(9999, "系统异常"),
    DB_ERR(1000, "数据库异常");

    /**
     * 异常码
     */
    private int code;

    /**
     * 异常信息
     */
    private String msg;

    private BasicExceptionEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
