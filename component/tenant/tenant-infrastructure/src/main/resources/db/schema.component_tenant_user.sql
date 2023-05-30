-- 建表语句sql
DROP TABLE IF EXISTS component_tenant_user;
CREATE TABLE `component_tenant_user` (
  `id` bigint NOT NULL COMMENT '主键',
  `user_id` bigint NOT NULL COMMENT '用户id',
  `name` varchar(255) DEFAULT NULL COMMENT '真实姓名',
  `is_expired` tinyint(1) NOT NULL COMMENT '是否过期，过期后该密码不能登录',
  `expired_reason` varchar(255) DEFAULT NULL COMMENT '过期原因',
  `expire_at` datetime DEFAULT NULL COMMENT '到期时间，为空永不到期',
  `is_leave` tinyint(1) NOT NULL COMMENT '是否离职或退出',
  `leave_reason` varchar(255) DEFAULT NULL COMMENT '离职或退出原因',
  `leave_at` datetime DEFAULT NULL COMMENT '离职或退出时间',
  `join_at` datetime DEFAULT NULL COMMENT '用户加入时间',
  `is_formal` tinyint(1) NOT NULL COMMENT '是否正式，1=正式，0=试用',
  `version` int NOT NULL COMMENT '乐观锁字段',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户id',
  `create_at` datetime NOT NULL COMMENT '创建时间的时间戳',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `update_at` datetime DEFAULT NULL COMMENT '修改时间的时间戳',
  `update_by` bigint DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uni_user_id_tenant_id` (`user_id`,`tenant_id`),
  KEY `idx_func_id` (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='租户用户表';
