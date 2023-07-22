package com.lmt.zeus.jpa.config;

import com.lmt.zeus.jpa.annotation.JpaDto;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.FatalBeanException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.core.convert.support.GenericConversionService;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Map;

/**
 * @description
 *
 * @author bazhandao
 * @date 2020/3/26 17:51
 * @since JDK1.8
 */
@Configuration
public class ZeusJpaConfiguration {

    private final Logger log = LoggerFactory.getLogger(ZeusJpaConfiguration.class);

    @Resource
    private ApplicationContext applicationContext;

    /**
     * 初始化注入@JpaDto对应的Converter
     */
    @PostConstruct
    public void init() {
        Map<String, Object> map = applicationContext.getBeansWithAnnotation(JpaDto.class);
        for (Object o : map.values()) {
            Class c = o.getClass();
            log.info("Jpa添加Converter,class={}", c.getName());
            GenericConversionService genericConversionService = ((GenericConversionService) DefaultConversionService.getSharedInstance());
            genericConversionService.addConverter(Map.class, c, m -> {
                try {
                    Object obj = c.newInstance();
                    return copyMapToObj(m, obj);
                } catch (Exception e) {
                    throw new FatalBeanException("Jpa结果转换出错,class=" + c.getName(), e);
                }
            });
        }
    }

    /**
     * 将map中的值copy到bean中对应的字段上
     * @author bazhandao
     * @date 2020-03-26
     * @param map
     * @param target
     * @return
     */
    private Object copyMapToObj(Map<String, Object> map, Object target) {
        if(map == null || target == null || map.isEmpty()){
            return target;
        }
        Class<?> actualEditable = target.getClass();
        PropertyDescriptor[] targetPds = BeanUtils.getPropertyDescriptors(actualEditable);
        for (PropertyDescriptor targetPd : targetPds) {
            if(targetPd.getWriteMethod() == null) {
                continue;
            }
            try {
                String key = targetPd.getName();
                Object value = map.get(key);
                if (value == null) {
                    continue;
                }
                Method writeMethod = targetPd.getWriteMethod();
                if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
                    writeMethod.setAccessible(true);
                }
                writeMethod.invoke(target, value);
            } catch (Exception ex) {
                throw new FatalBeanException("Could not copy properties from source to target", ex);
            }
        }
        return target;
    }

}
