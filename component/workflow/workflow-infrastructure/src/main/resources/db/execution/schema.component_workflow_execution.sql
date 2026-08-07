-- 建表语句sql
DROP TABLE IF EXISTS component_workflow_execution;
CREATE TABLE `component_workflow_execution` (
  `id` bigint NOT NULL COMMENT '主键ID',
  `workflow_definition_id` bigint NOT NULL COMMENT '工作流定义ID',
  `workflow_definition_history_id` bigint NOT NULL COMMENT '执行时使用的版本ID',
  `status_dict_id` bigint NOT NULL COMMENT '执行状态字典id：running/success/failed',
  `trigger_type_dict_id` bigint NOT NULL COMMENT '触发方式字典id：manual/api/schedule',
  `node_id` varchar(100) DEFAULT NULL COMMENT '当前执行节点ID（用于断点续跑，对应graph里的id）',
  `copied_workflow_execution_id` bigint DEFAULT NULL COMMENT '数据来源执行ID（从此节点运行时拷贝该实例数据）',
  `context_json` text COMMENT '全局上下文数据json',
  `start_at` datetime NOT NULL COMMENT '运行开始时间',
  `finish_at` datetime DEFAULT NULL COMMENT '运行结束时间',
  `error_msg` text COMMENT '错误信息',
  `version` int NOT NULL COMMENT '乐观锁字段',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户id',
  `create_at` datetime NOT NULL COMMENT '创建时间的时间戳',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `update_at` datetime DEFAULT NULL COMMENT '修改时间的时间戳',
  `update_by` bigint DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_definition_id` (`workflow_definition_id`),
  KEY `idx_history_id` (`workflow_definition_history_id`),
  KEY `idx_status_dict_id` (`status_dict_id`),
  KEY `idx_create_at` (`create_at`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='工作流执行实例表';
