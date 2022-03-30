/*
 * Copyright (c) 2017 Baidu, Inc. All Rights Reserve.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.lmt.zeus.id.snow.worker;

import com.lmt.zeus.id.snow.utils.DockerUtils;
import com.lmt.zeus.id.snow.utils.NetUtils;
import com.lmt.zeus.id.snow.worker.entity.SysIdWorkerNode;
import com.lmt.zeus.parent.common.Constants;
import com.lmt.zeus.parent.exception.ZeusException;
import com.lmt.zeus.parent.exception.ZeusExceptionEnum;
import com.lmt.zeus.parent.utils.FileUtils;
import com.lmt.zeus.parent.utils.JSONUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.File;
import java.util.Date;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Represents an implementation of {@link WorkerIdAssigner},
 * the worker id will be discarded after assigned to the UidGenerator
 *
 * @author yutianbao
 */
@Slf4j
@Service(value = "workerIdAssigner")
public class DisposableWorkerIdAssigner implements WorkerIdAssigner {

    @Resource
    private SysIdWorkerNodeDao sysIdWorkerNodeDao;

    @Value("${spring.application.name:default}")
    private String applicationName;

    private SysIdWorkerNode workerNode;

    @PostConstruct
    private void init() {
        log.info("初始化定时更新workerId任务,每10s执行一次...");
        ScheduledExecutorService executor = new ScheduledThreadPoolExecutor(1);
        executor.scheduleAtFixedRate(
                () -> updateWorkerId(),
                10,
                10,
                TimeUnit.SECONDS);
    }

    /**
     * Assign worker id base on database.<p>
     * If there is host name & port in the environment, we considered that the node runs in Docker container<br>
     * Otherwise, the node runs on an actual machine.
     *
     * @return assigned worker id
     *
     * @author bazhandao
     * @date 2019-11-15
     * 优化workerId分配算法，缓存id到本地目录，下次启动时先判断id是否可用
     *
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public long assignWorkerId() {
        // 1.读取本地缓存的id
        String path = getLocalWorkerIdPath();
        workerNode = readLocalWorkerId(path);
        if (workerNode != null && workerNode.getId() != null) {
            long id = workerNode.getId();
            SysIdWorkerNode dbEntity = sysIdWorkerNodeDao.get(id);
            if (dbEntity != null
                    && workerNode.getLastTimestamp().longValue() >= dbEntity.getLastTimestamp().longValue()
                    && workerNode.getPort().equals(dbEntity.getPort())
                    && workerNode.getHostName().equals(dbEntity.getHostName())
                    && workerNode.getType().intValue() == dbEntity.getType()) {
                if (workerNode.getLastTimestamp() >= System.currentTimeMillis()) {
                    throw ZeusException.wrap(ZeusExceptionEnum.WORK_ID_INIT_ERROR.getCode(), ZeusExceptionEnum.WORK_ID_INIT_ERROR.getMsg())
                            .set("msg", "时间戳出错,时间可能出现回拔,系统时间小于上次workerId最后更新时间!!!")
                            .set("currTimestamp", System.currentTimeMillis())
                            .set("lastTimestamp", workerNode.getLastTimestamp());
                }
                workerNode.setLaunchDate(new Date());
                updateWorkerId();
                return id;
            }
        }
        // 则重新申请id
        // build worker node entity
        workerNode = buildWorkerNode();

        // add worker node for new (ignore the same IP + PORT)
        insert(workerNode);
        log.info("Add worker node:" + workerNode);

        updateWorkerId();
        return workerNode.getId();
    }

    /**
     * Build worker node entity by IP and PORT
     */
    private SysIdWorkerNode buildWorkerNode() {
        SysIdWorkerNode workerNode = new SysIdWorkerNode();
        if (DockerUtils.isDocker()) {
            workerNode.setType(WorkerNodeType.CONTAINER.value());
            workerNode.setHostName(DockerUtils.getDockerHost());
            workerNode.setPort(DockerUtils.getDockerPort());
        } else {
            workerNode.setType(WorkerNodeType.ACTUAL.value());
            workerNode.setHostName(NetUtils.getLocalAddress());
            workerNode.setPort(System.currentTimeMillis() + "-" + RandomUtils.nextInt(0, 100000));
        }
        workerNode.setLaunchDate(new Date());
        workerNode.setLastTimestamp(System.currentTimeMillis());
        workerNode.setCreatedTime(workerNode.getLaunchDate());
        workerNode.setUpdatedTime(workerNode.getLaunchDate());
        return workerNode;
    }

    /**
     * 更新db/本地的workerId
     * @author bazhandao
     * @date 2019-11-17
     */
    private void updateWorkerId() {
        workerNode.setLastTimestamp(System.currentTimeMillis());
        workerNode.setUpdatedTime(new Date());
        FileUtils.write(getLocalWorkerIdPath(), JSONUtils.toJson(workerNode));
        sysIdWorkerNodeDao.updateById(workerNode);
        log.debug("更新db/本地workerId,path={},wokerNode={}", getLocalWorkerIdPath(), workerNode);
    }

    /**
     * 读取本地workerId
     * @author bazhandao
     * @date 2019-11-17
     * @param path
     * @return
     */
    private SysIdWorkerNode readLocalWorkerId(String path) {
        try {
            if (!new File(path).exists()) {
                return null;
            }
            return JSONUtils.fromJson(FileUtils.readAll(path), SysIdWorkerNode.class);
        } catch (Exception e) {
            log.error("读取本地workerId出错,path={},{}", path, e);
        }
        return null;
    }

    /**
     * 获取本地workerId文件路径
     * @author bazhandao
     * @date 2019-11-17
     * @return
     */
    private String getLocalWorkerIdPath() {
        return System.getProperty("user.home") + Constants.SLASH_STR + Constants.DOT_STR + applicationName + "-worker-id.json";
    }

    /**
     * 插入workerId
     * @author bazhandao
     * @date 2019-11-17
     * @param sysIdWorkerNode
     */
    private int insert(SysIdWorkerNode sysIdWorkerNode) {
        for (int i = 0; i < 10; i++) {
            try {
                Long id = sysIdWorkerNodeDao.selectMaxId();
                id = id == null ? 0 : id;
                sysIdWorkerNode.setId(id + 1);
                return sysIdWorkerNodeDao.insert(sysIdWorkerNode);
            } catch (Exception e) {
                log.error("插入workerId出错!", e);
            }
        }
        throw ZeusException.wrap(ZeusExceptionEnum.WORK_ID_INIT_ERROR.getCode(), ZeusExceptionEnum.WORK_ID_INIT_ERROR.getMsg())
            .set("msg", "尝试新增workerId出错,尝试10失败,不再尝试!");
    }

}
