package com.lmt.zeus.id;

import com.lmt.zeus.mybatis.BasicMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @description 测试id生成器
 *
 * @author bazhandao
 * @date 2019/11/17 14:03
 * @since JDK1.8
 */
@SpringBootApplication(scanBasePackages = "com.lmt.zeus.id")
@MapperScan(basePackages = {"com.lmt.zeus.id"}, markerInterface = BasicMapper.class)
@EnableTransactionManagement
@ServletComponentScan
public class ZeusIdApp {

    public static void main(String [] args) {
        SpringApplication.run(ZeusIdApp.class, args);
    }

}
