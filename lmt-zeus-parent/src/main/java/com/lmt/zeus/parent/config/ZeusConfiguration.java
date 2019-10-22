package com.lmt.zeus.parent.config;

import com.lmt.zeus.parent.utils.SpringContextUtils;
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
public class ZeusConfiguration {

    /**
     * 注入SpringContextUtils
     * @return
     */
    @Bean
    @ConditionalOnMissingBean
    public SpringContextUtils springContextUtils() {
        return new SpringContextUtils();
    }

}
