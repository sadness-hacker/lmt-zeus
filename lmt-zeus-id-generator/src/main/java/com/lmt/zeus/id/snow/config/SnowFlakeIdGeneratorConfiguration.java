package com.lmt.zeus.id.snow.config;

import com.lmt.zeus.id.snow.buffer.RejectedPutBufferHandler;
import com.lmt.zeus.id.snow.buffer.RejectedTakeBufferHandler;
import com.lmt.zeus.id.snow.impl.CachedUidGenerator;
import com.lmt.zeus.id.snow.worker.SysIdWorkerNodeDao;
import com.lmt.zeus.id.snow.worker.SysIdWorkerNodeDaoBuilder;
import com.lmt.zeus.id.snow.worker.WorkerIdAssigner;
import com.lmt.zeus.parent.utils.SpringContextUtils;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @description id生成器配置类
 *
 * @author bazhandao
 * @date 2019/11/15 16:44
 * @since JDK1.8
 */
@ConditionalOnProperty(value = "lmt.zeus.id.generator-type", havingValue = "zeusIdGenerator")
@ComponentScan(value = "com.lmt.zeus.id")
@Configuration
public class SnowFlakeIdGeneratorConfiguration {

    private static Logger log = LoggerFactory.getLogger(SnowFlakeIdGeneratorConfiguration.class);

    @Resource
    private SnowFlakeProperties snowFlakeProperties;

    @Resource
    private SnowFlakeDataSourceProperties snowFlakeDataSourceProperties;

    @Resource
    private WorkerIdAssigner workerIdAssigner;

    /**
     * 根据数据源类型配置SysIdWorkerNodeDao
     * @author bazhandao
     * @date 2022-03-30
     * @return
     */
    @Bean
    public SysIdWorkerNodeDao sysIdWorkerNodeDao(@Autowired SysIdWorkerNodeDaoBuilder sysIdWorkerNodeDaoBuilder) {
        return sysIdWorkerNodeDaoBuilder.build(snowFlakeDataSourceProperties);
    }

    /**
     * 配置雪花算法CachedUidGenerator
     * 当lmt.zeus.id.snowflake.generator-type=cachedUidGenerator时配置
     * @author bazhandao
     * @date 2019-11-15
     * @return
     */
    @Bean(value = "uidGenerator")
    public CachedUidGenerator cachedUidGenerator() {
        log.info("雪花算法uidGenerator启动...");
        CachedUidGenerator cachedUidGenerator = new CachedUidGenerator();
        cachedUidGenerator.setBoostPower(snowFlakeProperties.getBoostPower());
        cachedUidGenerator.setScheduleInterval(snowFlakeProperties.getScheduleInterval());
        if (StringUtils.isNotBlank(snowFlakeProperties.getRejectedPutBufferHandler())) {
            cachedUidGenerator.setRejectedPutBufferHandler(SpringContextUtils.getBean(snowFlakeProperties.getRejectedPutBufferHandler(), RejectedPutBufferHandler.class));
        }
        if (StringUtils.isNotBlank(snowFlakeProperties.getRejectedTakeBufferHandler())) {
            cachedUidGenerator.setRejectedTakeBufferHandler(SpringContextUtils.getBean(snowFlakeProperties.getRejectedTakeBufferHandler(), RejectedTakeBufferHandler.class));
        }
        cachedUidGenerator.setEpochStr(snowFlakeProperties.getEpochStr());
        cachedUidGenerator.setTimeBits(snowFlakeProperties.getTimeBits());
        cachedUidGenerator.setSeqBits(snowFlakeProperties.getSeqBits());
        cachedUidGenerator.setWorkerBits(snowFlakeProperties.getWorkerBits());
        cachedUidGenerator.setPaddingFactor(snowFlakeProperties.getPaddingFactor());
        cachedUidGenerator.setWorkerIdAssigner(workerIdAssigner);
        return cachedUidGenerator;
    }

}
