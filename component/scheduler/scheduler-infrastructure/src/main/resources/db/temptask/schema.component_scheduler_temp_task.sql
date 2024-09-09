-- 建表语句sql
DROP TABLE IF EXISTS component_scheduler_temp_task;
CREATE TABLE `component_scheduler_temp_task` (
  `id` bigint NOT NULL COMMENT '主键ID',
  `code` varchar(50) NOT NULL COMMENT '临时任务编码',
  `name` varchar(50) NOT NULL COMMENT '临时任务名称',
  `version` int NOT NULL COMMENT '乐观锁字段',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户id',
  `create_at` datetime NOT NULL COMMENT '创建时间的时间戳',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `update_at` datetime DEFAULT NULL COMMENT '修改时间的时间戳',
  `update_by` bigint DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `code` (`code`) USING BTREE,
  KEY `name` (`name`) USING BTREE,
  KEY `create_at` (`create_at`) USING BTREE,
  KEY `update_at` (`update_at`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='任务计划临时任务表';
