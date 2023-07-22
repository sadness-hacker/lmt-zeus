package com.lmt.zeus.id.snow.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/**
 * @description 雪花算法配置项
 *
 * @author bazhandao
 * @date 2019/11/15 17:00
 * @since JDK1.8
 */
@Primary
@Component
@ConfigurationProperties(prefix = "lmt.zeus.id.snowflake")
public class SnowFlakeProperties {

    /**
     * 默认雪花算法生成器类型,defaultUidGenerator
     */
    private String generatorType = "defaultUidGenerator";

    /**
     * 时间位数
     */
    private int timeBits = 30;

    /**
     * worker节点位数
     */
    private int workerBits = 20;

    /**
     * 序列位数
     */
    private int seqBits = 13;

    /**
     * 公元年起始时间
     */
    private String epochStr = "2019-10-24";

    /**
     * RingBuffer size, to improve the throughput.
     * Default as 3. Sample: original bufferSize=8192, after boosting the new bufferSize= 8192 << 3 = 65536
     */
    private int boostPower = 3;

    /**
     * In-time padding, available UIDs percentage(0, 100) of the RingBuffer, default as 50
     * Sample: bufferSize=1024, paddingFactor=50 -> threshold=1024 * 50 / 100 = 512.
     * When the rest available UIDs < 512, RingBiffer will be padded in-time
     */
    private int paddingFactor = 50;

    /**
     * Periodic padding
     * Default is disabled. Enable as below, scheduleInterval unit as Seconds.
     * 单位：秒
     */
    private int scheduleInterval = 300;

    /**
     * Policy for rejecting put on RingBuffer
     */
    private String rejectedPutBufferHandler;

    /**
     * Policy for rejecting take from RingBuffer
     */
    private String rejectedTakeBufferHandler;

    public String getGeneratorType() {
        return generatorType;
    }

    public void setGeneratorType(String generatorType) {
        this.generatorType = generatorType;
    }

    public int getTimeBits() {
        return timeBits;
    }

    public void setTimeBits(int timeBits) {
        this.timeBits = timeBits;
    }

    public int getWorkerBits() {
        return workerBits;
    }

    public void setWorkerBits(int workerBits) {
        this.workerBits = workerBits;
    }

    public int getSeqBits() {
        return seqBits;
    }

    public void setSeqBits(int seqBits) {
        this.seqBits = seqBits;
    }

    public String getEpochStr() {
        return epochStr;
    }

    public void setEpochStr(String epochStr) {
        this.epochStr = epochStr;
    }

    public int getBoostPower() {
        return boostPower;
    }

    public void setBoostPower(int boostPower) {
        this.boostPower = boostPower;
    }

    public int getPaddingFactor() {
        return paddingFactor;
    }

    public void setPaddingFactor(int paddingFactor) {
        this.paddingFactor = paddingFactor;
    }

    public int getScheduleInterval() {
        return scheduleInterval;
    }

    public void setScheduleInterval(int scheduleInterval) {
        this.scheduleInterval = scheduleInterval;
    }

    public String getRejectedPutBufferHandler() {
        return rejectedPutBufferHandler;
    }

    public void setRejectedPutBufferHandler(String rejectedPutBufferHandler) {
        this.rejectedPutBufferHandler = rejectedPutBufferHandler;
    }

    public String getRejectedTakeBufferHandler() {
        return rejectedTakeBufferHandler;
    }

    public void setRejectedTakeBufferHandler(String rejectedTakeBufferHandler) {
        this.rejectedTakeBufferHandler = rejectedTakeBufferHandler;
    }
}
