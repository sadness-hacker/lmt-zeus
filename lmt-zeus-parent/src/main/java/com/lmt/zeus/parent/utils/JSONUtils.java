package com.lmt.zeus.parent.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lmt.zeus.parent.exception.ZeusExceptionEnum;
import com.lmt.zeus.parent.exception.ZeusException;

/**
 * @description 基于Jackson库的json工具类
 * @author bazhandao
 * @date 2018/11/8 17:52
 * @since JDK1.8
 */
public class JSONUtils {

    public static final ObjectMapper mapper = new ObjectMapper();

    /**
     * 对象转json字符串
     * @param obj
     * @return
     */
    public static String toJson(Object obj) {
        try {
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw ZeusException.wrap(ZeusExceptionEnum.BEAN_2_JSON_ERR.getCode(), ZeusExceptionEnum.BEAN_2_JSON_ERR.getMsg(), e)
                    .set("object", obj);
        }
    }

    /**
     * json字符串转对象
     * @param json
     * @param clazz
     * @return
     */
    public static <T> T fromJson(String json, Class<T> clazz) {
        try {
            return mapper.readValue(json, clazz);
        } catch (Exception e) {
            throw ZeusException.wrap(ZeusExceptionEnum.JSON_2_BEAN_ERR.getCode(), ZeusExceptionEnum.JSON_2_BEAN_ERR.getMsg(), e)
                    .set("json", json)
                    .set("clazz", clazz);
        }
    }

    /**
     * json字符串转对象
     * @param json
     * @param typeReference
     * @return
     */
    public static <T> T fromJson(String json, TypeReference<T> typeReference) {
        try {
            return mapper.readValue(json, typeReference);
        } catch (Exception e) {
            throw ZeusException.wrap(ZeusExceptionEnum.JSON_2_BEAN_ERR.getCode(), ZeusExceptionEnum.JSON_2_BEAN_ERR.getMsg(), e)
                    .set("json", json)
                    .set("clazz", typeReference);
        }
    }
}
