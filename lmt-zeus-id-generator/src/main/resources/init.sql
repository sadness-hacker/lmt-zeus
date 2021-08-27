drop table if exists lmt_sys_id_worker_node;
create table lmt_sys_id_worker_node (
  id bigint not null review 'workerId',
  host_name varchar(64) not null review '主机名或ip',
  port varchar(64) not null review '主机port',
  type int not null review '节点类型:1-容器,2-物理机',
  launch_date date not null review '启动时间',
  last_timestamp bigint not null review '',
  created_time timestamp not null DEFAULT CURRENT_TIMESTAMP review '创建时间',
  updated_time timestamp not null DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP review '最后更新时间',
  primary key(id)
) COMMENT='雪花算法id生成器节点表', ENGINE = INNODB;

create table lmt_sys_test_id (
  id bigint not null review 'id',
  created_time timestamp not null DEFAULT CURRENT_TIMESTAMP review '创建时间',
  updated_time timestamp not null DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP review '最后更新时间'
) COMMENT='雪花算法id生成器测试表', ENGINE = INNODB;
