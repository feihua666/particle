-- 建表语句sql
DROP TABLE IF EXISTS component_workflow_execution_node;
CREATE TABLE `component_workflow_execution_node` (
  `id` bigint NOT NULL COMMENT '主键ID',
  `workflow_execution_id` bigint NOT NULL COMMENT '工作流执行ID',
  `node_id` varchar(100) NOT NULL COMMENT '节点ID（对应graph里的id）',
  `status_dict_id` bigint NOT NULL COMMENT '状态：pending/running/success/failed',
  `input_json` json DEFAULT NULL COMMENT '节点输入',
  `output_json` json DEFAULT NULL COMMENT '节点输出',
  `error_msg` text COMMENT '错误信息',
  `retry_count` int DEFAULT '0' COMMENT '重试次数',
  `start_at` datetime NOT NULL COMMENT '运行开始时间',
  `finish_at` datetime DEFAULT NULL COMMENT '运行结束时间',
  `version` int NOT NULL COMMENT '乐观锁字段',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户id',
  `create_at` datetime NOT NULL COMMENT '创建时间的时间戳',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `update_at` datetime DEFAULT NULL COMMENT '修改时间的时间戳',
  `update_by` bigint DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uni_execution_node` (`workflow_execution_id`,`node_id`),
  KEY `idx_execution_id` (`workflow_execution_id`),
  KEY `idx_status_dict_id` (`status_dict_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='工作流执行实例节点表';
