-- 建表语句sql
DROP TABLE IF EXISTS component_op_log_error_content;
CREATE TABLE `component_op_log_error_content` (
  `id` bigint NOT NULL COMMENT '主键ID',
  `op_log_error_id` bigint NOT NULL COMMENT '异常日志id',
  `content` longtext NOT NULL COMMENT '异常内容',
  `version` int NOT NULL COMMENT '乐观锁字段',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户id',
  `create_at` datetime NOT NULL COMMENT '创建时间的时间戳',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `update_at` datetime DEFAULT NULL COMMENT '修改时间的时间戳',
  `update_by` bigint DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `op_log_error_id` (`op_log_error_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='操作异常日志内容表';
