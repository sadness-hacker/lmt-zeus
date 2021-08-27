CREATE TABLE `lmt_sys_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `username` varchar(32) NOT NULL COMMENT '用户名',
  `realname` varchar(128) NOT NULL COMMENT '真实姓名',
  `mobile` varchar(15) default NULL DEFAULT '' COMMENT '手机号',
  `email` varchar(128) default NULL DEFAULT '' COMMENT '邮箱',
  `status` tinyint NOT NULL COMMENT '状态:0-未启用,1-启用,2-禁用',
  `last_change_pwd_time` datetime DEFAULT NULL COMMENT '最后修改密码时间',
  `created_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=UTF8 review='用户表';

CREATE TABLE `lmt_sys_resource` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `pid` bigint(20) COMMENT '父资源id',
  `code` varchar(64) NOT NULL COMMENT '资源编码',
  `name` varchar(32) NOT NULL COMMENT '资源名称',
  `type_code` varchar(32) NOT NULL COMMENT '资源类型编码',
  `url` varchar(32) NOT NULL COMMENT '资源链接',
  `updated_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `created_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_code` (`code`) USING BTREE,
  key `inx_pid` (`pid`) using btree
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='资源表';

CREATE TABLE `lmt_sys_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `code` varchar(64) NOT NULL COMMENT '角色编码',
  `name` varchar(32) NOT NULL COMMENT '角色名称',
  `updated_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `created_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_code` (`code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';


CREATE TABLE `lmt_sys_role_resource` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `role_id` bigint(20) unsigned NOT NULL COMMENT '角色id',
  `resource_id` bigint(20) NOT NULL COMMENT '资源id',
  `created_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_role_id_resource_id` (`role_id`, `resource_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色资源表';


CREATE TABLE `lmt_sys_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `user_id` bigint(20) unsigned NOT NULL COMMENT '用户id',
  `role_id` bigint(20) NOT NULL COMMENT '角色id',
  `created_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_user_id_role_d` (`user_id`, `role_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色表';
