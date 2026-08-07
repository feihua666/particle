-- 建表语句sql
DROP TABLE IF EXISTS component_workflow_definition;
CREATE TABLE `component_workflow_definition` (
  `id` bigint NOT NULL COMMENT '主键ID',
  `name` varchar(150) NOT NULL COMMENT '工作流名称',
  `cover_image_url` varchar(500) DEFAULT NULL COMMENT '封面图地址',
  `workflow_project_id` bigint NOT NULL COMMENT '项目id',
  `latest_publish_workflow_definition_history_id` bigint DEFAULT NULL COMMENT '最新发布版本流程定义id',
  `draft_workflow_definition_history_id` bigint DEFAULT NULL COMMENT '草稿版本流程定义id',
  `remark` varchar(255) DEFAULT NULL COMMENT '描述',
  `version` int NOT NULL COMMENT '乐观锁字段',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户id',
  `create_at` datetime NOT NULL COMMENT '创建时间的时间戳',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `update_at` datetime DEFAULT NULL COMMENT '修改时间的时间戳',
  `update_by` bigint DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `create_at` (`create_at`) USING BTREE,
  KEY `update_at` (`update_at`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='工作流定义表';
