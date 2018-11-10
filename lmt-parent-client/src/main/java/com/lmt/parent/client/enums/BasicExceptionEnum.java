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

    ERROR(9999, "系统异常"),
    UNKNOWN(9998, "未知异常"),
    SYS_ERR(9000, "系统异常"),
    JSON_2_BEAN_ERR(9001, "JSON转换为Bean异常"),
    BEAN_2_JSON_ERR(9002, "Bean转JSON字符串异常"),
    GEN_FILE_MD5_ERR(9003, "计算文件md5值异常"),
    DATE_PARSE_ERROR(9004, "日期转换异常"),

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
