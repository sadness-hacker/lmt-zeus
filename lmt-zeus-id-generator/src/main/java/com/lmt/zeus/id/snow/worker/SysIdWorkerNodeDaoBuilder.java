package com.lmt.zeus.id.snow.worker;

import com.lmt.zeus.id.snow.config.SnowFlakeDataSourceProperties;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @description SysIdWorkerNodeDaoBuilder
 *
 * @author bazhandao
 * @date 2022/3/30 17:47
 * @since JDK1.8
 */
@Component
public class SysIdWorkerNodeDaoBuilder {

    @Resource
    private JdbcTemplate jdbcTemplate;

    /**
     * 创建SysIdWorkerNodeDao
     * @author bazhandao
     * @date 2022-03-30
     * @param snowFlakeDataSourceProperties
     * @return
     */
    public SysIdWorkerNodeDao build(SnowFlakeDataSourceProperties snowFlakeDataSourceProperties) {
        if (SnowFlakeDataSourceProperties.TYPE_JDBC.equalsIgnoreCase(snowFlakeDataSourceProperties.getType())) {
            // jdbc配置
            HikariConfig config = new HikariConfig();
            config.setPoolName("hikari-snowflake-ds");
            config.setJdbcUrl(snowFlakeDataSourceProperties.getUrl());
            config.setUsername(snowFlakeDataSourceProperties.getUsername());
            config.setPassword(snowFlakeDataSourceProperties.getPassword());
            config.setDriverClassName(snowFlakeDataSourceProperties.getDriverClassName());
            config.addDataSourceProperty("cachePrepStmts", "true");
            config.addDataSourceProperty("prepStmtCacheSize", "50");
            config.addDataSourceProperty("prepStmtCacheSqlLimit", "128");
            config.setMaximumPoolSize(3);
            return new SysIdWorkerNodeDao(new JdbcTemplate(new HikariDataSource(config)));
        }
        // 默认配置
        return new SysIdWorkerNodeDao(jdbcTemplate);
    }

}
