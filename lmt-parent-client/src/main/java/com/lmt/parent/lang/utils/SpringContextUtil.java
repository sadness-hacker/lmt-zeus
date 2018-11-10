package com.lmt.parent.lang.utils;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @description SpringContext工具类
 * @author bazhandao
 * @date 2018/11/8 17:54
 * @since JDK1.8
 */
@Component
public class SpringContextUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    /**
     * 设置ApplicationContext
     * @author bazhandao
     * @date 2018-11-10
     * @param applicationContext
     * @throws BeansException
     */
    @Autowired
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextUtil.applicationContext = applicationContext;
    }

    /**
     * 获取当前Spring容器
     * @author bazhandao
     * @date 2018-11-10
     * @return ApplicationContext
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 根据bean名称/id获取bean
     * @author bazhandao
     * @date 2018-11-10
     * @param name
     * @return bean
     */
    public static Object getBean(String name) {
        return applicationContext.getBean(name);
    }

    /**
     * 根据bean名称/id、bean类型获取bean
     * @author bazhandao
     * @date 2018-11-10
     * @param name
     * @param requiredType
     * @param <T>
     * @return bean
     */
    public static <T> T getBean(String name, Class<T> requiredType) {
        return applicationContext.getBean(name, requiredType);
    }

    /**
     * 根据bean类型获取bean
     * @author bazhandao
     * @date 2018-11-10
     * @param requiredType
     * @param <T>
     * @return
     */
    public static <T> T getBean(Class<T> requiredType) {
        return applicationContext.getBean(requiredType);
    }

}
