package com.lmt.zeus.jpa.annotation;

/**
 * @description 自定义注解表示,加在类上表示是一个JpaDto类
 *
 * @author bazhandao
 * @date 2020/3/26 16:39
 * @since JDK1.8
 */

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Documented
@Component
@Target(value = {ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface JpaDto {

}
