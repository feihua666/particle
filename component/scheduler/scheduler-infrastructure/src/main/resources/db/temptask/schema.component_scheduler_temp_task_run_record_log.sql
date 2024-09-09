-- 建表语句sql
DROP TABLE IF EXISTS component_scheduler_temp_task_run_record_log;
CREATE TABLE `component_scheduler_temp_task_run_record_log` (
  `id` bigint NOT NULL COMMENT '主键ID',
  `scheduler_temp_task_run_record_id` bigint NOT NULL COMMENT '临时任务运行记录id',
  `level` varchar(10) NOT NULL COMMENT '日志级别，建议遵循java日志级别，info、warn、debug等',
  `content` text NOT NULL COMMENT '日志内容',
  `version` int NOT NULL COMMENT '乐观锁字段',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户id',
  `create_at` datetime NOT NULL COMMENT '创建时间的时间戳',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `update_at` datetime DEFAULT NULL COMMENT '修改时间的时间戳',
  `update_by` bigint DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `scheduler_temp_task_run_record_id` (`scheduler_temp_task_run_record_id`) USING BTREE,
  KEY `create_at` (`create_at`) USING BTREE,
  KEY `update_at` (`update_at`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='任务计划临时任务运行记录日志表';
