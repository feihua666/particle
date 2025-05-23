-- 建表语句sql
DROP TABLE IF EXISTS component_scheduler_execute_record;
CREATE TABLE `component_scheduler_execute_record` (
  `id` bigint NOT NULL COMMENT '主键id',
  `scheduler_name` varchar(255) NOT NULL COMMENT 'schedulerName',
  `scheduler_instance_id` varchar(255) NOT NULL COMMENT 'schedulerInstanceId',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '任务名称',
  `group_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '任务组',
  `params` text CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '执行参数，json',
  `execute_status_dict_id` bigint NOT NULL COMMENT '执行状态，字典id',
  `start_at` datetime NOT NULL COMMENT '运行开始时间',
  `finish_at` datetime DEFAULT NULL COMMENT '运行结束时间',
  `local_host_ip` varchar(500) DEFAULT NULL COMMENT '本地主机ip，用来表明是在哪个机器上运行的',
  `local_host_name` varchar(500) DEFAULT NULL COMMENT '本地主机名称，用来表明是在哪个机器上运行的',
  `trace_id` varchar(100) DEFAULT NULL COMMENT '链路追踪id',
  `result` longtext DEFAULT NULL COMMENT '运行结果，运行成果物',
  `version` int NOT NULL COMMENT '乐观锁字段',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户id',
  `create_at` datetime NOT NULL COMMENT '创建时间的时间戳',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `update_at` datetime DEFAULT NULL COMMENT '修改时间的时间戳',
  `update_by` bigint DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `scheduler_name` (`scheduler_name`) USING BTREE,
  KEY `scheduler_instance_id` (`scheduler_instance_id`) USING BTREE,
  KEY `name` (`name`) USING BTREE,
  KEY `group_name` (`group_name`) USING BTREE,
  KEY `trace_id` (`trace_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='任务计划执行记录表';
