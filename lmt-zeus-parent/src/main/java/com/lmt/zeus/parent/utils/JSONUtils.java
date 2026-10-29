package com.lmt.zeus.parent.utils;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.DefaultTyping;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.DeserializationFeature;
import com.lmt.zeus.parent.exception.ZeusExceptionEnum;
import com.lmt.zeus.parent.exception.ZeusException;
import tools.jackson.databind.json.JsonMapper;
import tools.jackson.databind.jsontype.BasicPolymorphicTypeValidator;

/**
 * @description 基于Jackson库的json工具类
 * @author bazhandao
 * @date 2018/11/8 17:52
 * @since JDK1.8
 */
public class JSONUtils {

    // ✅ 方案一：标准不可变实例（推荐，完全符合 Jackson 3 工业设计美学）
    public static final ObjectMapper mapper = JsonMapper.builder()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .build();

    // 2. 携带类信息的 mapper
    public static final ObjectMapper mapperWithClassInfo = JsonMapper.builder()
            .activateDefaultTyping(
                    BasicPolymorphicTypeValidator.builder().allowIfBaseType(Object.class).build(),
                    DefaultTyping.NON_FINAL,
                    JsonTypeInfo.As.PROPERTY
            )
            .build();

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
     * 对象转json字符串,可以设置是否包含class信息
     * @param obj
     * @param withClassInfo 是否包含class信息
     * @return
     */
    public static String toJson(Object obj, boolean withClassInfo) {
        if (withClassInfo) {
            try {
                return mapperWithClassInfo.writeValueAsString(obj);
            } catch (Exception e) {
                throw ZeusException.wrap(ZeusExceptionEnum.BEAN_2_JSON_ERR.getCode(), ZeusExceptionEnum.BEAN_2_JSON_ERR.getMsg(), e)
                        .set("object", obj);
            }
        } else {
            return toJson(obj);
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

    /**
     * 解析字符串为JsonNode
     * if (node.isArray()) {
     *     ArrayNode arrayNode = (ArrayNode) node;
     * }
     * if (node.isObject()) {
     *     ObjectNode objectNode = (ObjectNode) node;
     * }
     * @param json
     * @return
     */
    public static JsonNode parse(String json) {
        try {
            return mapper.readTree(json);
        } catch (Exception e) {
            throw ZeusException.wrap(ZeusExceptionEnum.JSON_2_BEAN_ERR.getCode(), ZeusExceptionEnum.JSON_2_BEAN_ERR.getMsg(), e)
                    .set("json", json);
        }
    }
    
}
