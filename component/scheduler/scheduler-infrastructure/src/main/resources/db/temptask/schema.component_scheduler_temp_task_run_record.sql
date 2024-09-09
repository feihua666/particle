-- 建表语句sql
DROP TABLE IF EXISTS component_scheduler_temp_task_run_record;
CREATE TABLE `component_scheduler_temp_task_run_record` (
  `id` bigint NOT NULL COMMENT '主键ID',
  `scheduler_temp_task_id` bigint NOT NULL COMMENT '临时任务id',
  `execute_status_dict_id` bigint NOT NULL COMMENT '临时任务状态，字典id，如：执行中、执行完成',
  `is_has_error` tinyint(1) NOT NULL COMMENT '是否有异常，有异常=1，否则为0',
  `is_allow_run_switch` tinyint(1) NOT NULL COMMENT '是否允许运行开关，允许运行=1，停止运行=0',
  `start_at` datetime NOT NULL COMMENT '运行开始时间',
  `finish_at` datetime DEFAULT NULL COMMENT '运行结束时间',
  `local_host_ip` varchar(500) DEFAULT NULL COMMENT '本地主机ip，用来表明是在哪个机器上运行的',
  `local_host_name` varchar(500) DEFAULT NULL COMMENT '本地主机名称，用来表明是在哪个机器上运行的',
  `trace_id` varchar(50) DEFAULT NULL COMMENT '日志追踪id',
  `result` varchar(2000) DEFAULT NULL COMMENT '运行结果，运行成果物',
  `version` int NOT NULL COMMENT '乐观锁字段',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户id',
  `create_at` datetime NOT NULL COMMENT '创建时间的时间戳',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `update_at` datetime DEFAULT NULL COMMENT '修改时间的时间戳',
  `update_by` bigint DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `scheduler_temp_task_id` (`scheduler_temp_task_id`) USING BTREE,
  KEY `execute_status_dict_id` (`execute_status_dict_id`) USING BTREE,
  KEY `create_at` (`create_at`) USING BTREE,
  KEY `update_at` (`update_at`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='任务计划临时任务运行记录表';
