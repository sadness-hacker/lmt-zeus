package com.lmt.zeus.id.snow.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/**
 * @description 雪花算法数据源注入
 *
 * @author bazhandao
 * @date 2022/3/30 16:50
 * @since JDK1.8
 */
@Primary
@Component
@ConfigurationProperties(prefix = "lmt.zeus.id.snowflake.datasource")
public class SnowFlakeDataSourceProperties {

    public static final String TYPE_JDBC = "jdbc";

    /**
     * 数据源注入方式：
     * default-默认注入系统统一的数据源
     * jdbc-雪花算法另外配置数据源
     */
    private String type = "default";

    private String url;

    private String username;

    private String password;

    private String driverClassName;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }
}
