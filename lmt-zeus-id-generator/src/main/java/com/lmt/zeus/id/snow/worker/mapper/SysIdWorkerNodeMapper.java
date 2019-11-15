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
package com.lmt.zeus.id.snow.worker.mapper;

import com.lmt.zeus.id.snow.worker.entity.SysIdWorkerNodeEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * DAO for M_WORKER_NODE
 *
 * @author yutianbao
 */
@Repository
public interface SysIdWorkerNodeMapper {

    /**
     * Get {@link SysIdWorkerNodeEntity} by node host
     *
     * @param host
     * @param port
     * @return
     */
    SysIdWorkerNodeEntity getWorkerNodeByHostPort(@Param("host") String host, @Param("port") String port);

    /**
     * Add {@link SysIdWorkerNodeEntity}
     *
     * @param workerNodeEntity
     */
    void addWorkerNode(SysIdWorkerNodeEntity workerNodeEntity);

}
