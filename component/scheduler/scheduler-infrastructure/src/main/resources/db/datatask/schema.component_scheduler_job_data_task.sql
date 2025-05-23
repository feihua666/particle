-- 建表语句sql
DROP TABLE IF EXISTS component_scheduler_job_data_task;
CREATE TABLE `component_scheduler_job_data_task` (
  `id` bigint NOT NULL COMMENT '主键ID',
  `group_identifier` varchar(50) NOT NULL COMMENT '任务分组标识，用于区分不同的任务数据',
  `unique_identifier` varchar(300) DEFAULT NULL COMMENT '唯一标识，用于唯一区分不同的任务数据',
  `params` text CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '执行参数',
  `execute_status_dict_id` bigint NOT NULL COMMENT '执行状态，字典id',
  `error_message` varchar(500) DEFAULT NULL COMMENT '执行错误时提示信息',
  `start_at` datetime DEFAULT NULL COMMENT '运行开始时间',
  `finish_at` datetime DEFAULT NULL COMMENT '运行结束时间',
  `local_host_ip` varchar(500) DEFAULT NULL COMMENT '本地主机ip，用来表明是在哪个机器上运行的',
  `local_host_name` varchar(500) DEFAULT NULL COMMENT '本地主机名称，用来表明是在哪个机器上运行的',
  `trace_id` varchar(100) DEFAULT NULL COMMENT '链路追踪id',
  `result` longtext COMMENT '运行结果，运行成果物',
  `data_expire_at` datetime NOT NULL COMMENT '数据过期时间，用于清理过期的无用数据',
  `version` int NOT NULL COMMENT '乐观锁字段',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户id',
  `create_at` datetime NOT NULL COMMENT '创建时间的时间戳',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `update_at` datetime DEFAULT NULL COMMENT '修改时间的时间戳',
  `update_by` bigint DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `unique_identifier` (`unique_identifier`) USING BTREE,
  KEY `group_identifier` (`group_identifier`) USING BTREE,
  KEY `create_at` (`create_at`) USING BTREE,
  KEY `update_at` (`update_at`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='任务计划任务数据表';
