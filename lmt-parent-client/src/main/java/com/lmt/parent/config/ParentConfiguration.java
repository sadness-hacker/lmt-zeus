package com.lmt.parent.config;

import com.lmt.parent.lang.id.IdGenerator;
import com.lmt.parent.lang.id.NullIdGenerator;
import com.lmt.parent.lang.utils.SpringContextUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description ParentConfiguration
 *
 * @author bazhandao
 * @date 2019/4/8 15:02
 * @since JDK1.8
 */
@Configuration
public class ParentConfiguration {

    /**
     * 注入SpringContextUtils
     * @return
     */
    @Bean
    @ConditionalOnMissingBean
    public SpringContextUtils springContextUtils() {
        return new SpringContextUtils();
    }

    /**
     * 注入默认id生成器,不生成id,依赖数据库自增
     * @return
     */
    @Bean
    @ConditionalOnMissingBean
    public IdGenerator idGenerator() {
        return new NullIdGenerator<>();
    }

}
