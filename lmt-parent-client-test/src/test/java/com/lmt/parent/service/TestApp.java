package com.lmt.parent.service;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

/**
 * @description 测试启动类
 *
 * @author bazhandao
 * @date 2018/11/7 14:06
 * @since JDK1.8
 */
@MapperScan(value = {"com.lmt.parent.test.**.mapper", "sqlmapper.**"})
@ComponentScan("com.lmt.parent.test")
@SpringBootApplication
public class TestApp extends SpringBootServletInitializer {

    public static void main(String [] args) {
        SpringApplication.run(TestApp.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(TestApp.class);
    }

}
