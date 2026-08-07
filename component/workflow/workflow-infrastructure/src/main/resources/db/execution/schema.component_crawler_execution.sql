-- 建表语句sql
DROP TABLE IF EXISTS component_crawler_execution;
CREATE TABLE `component_crawler_execution` (
  `id` bigint NOT NULL COMMENT '主键ID',
  `crawler_definition_id` bigint NOT NULL COMMENT '爬虫定义ID',
  `crawler_definition_history_id` bigint NOT NULL COMMENT '执行时使用的版本ID',
  `status_dict_id` bigint NOT NULL COMMENT '执行状态字典id：running/success/failed',
  `trigger_type_dict_id` bigint NOT NULL COMMENT '触发方式字典id：manual/api/schedule',
  `context_json` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin COMMENT '全局上下文数据json',
  `start_at` datetime NOT NULL COMMENT '运行开始时间',
  `finish_at` datetime DEFAULT NULL COMMENT '运行结束时间',
  `error_msg` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin COMMENT '错误信息',
  `version` int NOT NULL COMMENT '乐观锁字段',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户id',
  `create_at` datetime NOT NULL COMMENT '创建时间的时间戳',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `update_at` datetime DEFAULT NULL COMMENT '修改时间的时间戳',
  `update_by` bigint DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_definition_id` (`crawler_definition_id`),
  KEY `idx_history_id` (`crawler_definition_history_id`),
  KEY `idx_status_dict_id` (`status_dict_id`),
  KEY `idx_create_at` (`create_at`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='爬虫执行实例表';
