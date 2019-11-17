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
package com.lmt.zeus.id.snow.worker.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.lmt.zeus.id.snow.worker.WorkerNodeType;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Entity for M_WORKER_NODE
 *
 * @author yutianbao
 */
@Getter
@Setter
@ToString(callSuper = true)
@Table(name = "lmt_sys_id_worker_node")
public class SysIdWorkerNode {

    /**
     * Entity unique id (table unique)
     */
    @Id
    @Column(name = "id")
    private Long id;

    /**
     * Type of CONTAINER: HostName, ACTUAL : IP.
     */
    @Column(name = "host_name")
    private String hostName;

    /**
     * Type of CONTAINER: Port, ACTUAL : Timestamp + Random(0-10000)
     */
    @Column(name = "port")
    private String port;

    /**
     * type of {@link WorkerNodeType}
     */
    @Column(name = "type")
    private Integer type;

    /**
     * Worker launch date, default now
     */
    @Column(name = "launch_date")
    private Date launchDate;

    /**
     * Created time
     */
    @Column(name = "created_time")
    private Date createdTime;

    /**
     * Last modified
     */
    @Column(name = "updated_time")
    private Date updatedTime;

    /**
     * 最后更新时间戳
     */
    @Column(name = "last_timestamp")
    private Long lastTimestamp;

}
