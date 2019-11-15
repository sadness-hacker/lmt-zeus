drop table if exists lmt_sys_id_worker_node;
create table lmt_sys_id_worker_node
(
  id bigint not null auto_increment comment 'id',
  host_name varchar(64) not null comment 'host name',
  port varchar(64) not null comment 'port',
  type int not null comment 'node type: actual or container',
  launch_date date not null comment 'launch date',
  modified timestamp not null comment 'modified time',
  created timestamp not null comment 'created time',
  primary key(id)
) COMMENT='雪花算法id生成器节点表', ENGINE = INNODB;
