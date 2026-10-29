package com.lmt.zeus.redis;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.*;
import tools.jackson.databind.DefaultTyping;
import tools.jackson.databind.DeserializationFeature;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.json.JsonMapper;
import tools.jackson.databind.jsontype.BasicPolymorphicTypeValidator;

/**
 * @description 增强Redis配置
 *
 * @author bazhandao
 * @date 2019/10/29 17:17
 * @since JDK1.8
 */
@Configuration
public class RedisConfigurationExt {

    /**
     * 设置RedisTemplate,设置key、hashkey序列化方式为StringRedisSerializer
     * 设置value、hashvalue序列化方式为GenericJackson2JsonRedisSerializer
     * @author bazhandao
     * @date 2019-10-29
     * @param redisConnectionFactory
     * @return
     */
    @Bean
    public RedisTemplate redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        // 🟢 1. 显式定制专属的 ObjectMapper，解决序列化死穴
        ObjectMapper objectMapper = JsonMapper.builder()
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                .activateDefaultTyping(
                        BasicPolymorphicTypeValidator.builder().allowIfBaseType(Object.class).build(),
                        DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY
                ).build();

        // 🟢 2. 【核心改变】：使用官方推荐的工业级替代工厂方法，彻底干掉旧的类
        // 传入我们定制好的 objectMapper，它会自动在底层构建一个类型安全、免疫 RCE 攻击的 JSON 序列化器
        RedisSerializer<Object> jsonSerializer = new JacksonJsonRedisSerializer(objectMapper, Object.class);

        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(jsonSerializer);
        redisTemplate.setHashValueSerializer(jsonSerializer);
        return redisTemplate;
    }

}
