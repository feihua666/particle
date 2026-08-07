-- 建表语句sql
DROP TABLE IF EXISTS component_crawler_definition;
CREATE TABLE `component_crawler_definition` (
  `id` bigint NOT NULL COMMENT '主键ID',
  `name` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '爬虫名称',
  `crawler_project_id` bigint NOT NULL COMMENT '项目id',
  `latest_publish_crawler_definition_history_id` bigint DEFAULT NULL COMMENT '最新发布版本爬虫定义id',
  `draft_crawler_definition_history_id` bigint DEFAULT NULL COMMENT '草稿版本爬虫定义id',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '描述',
  `version` int NOT NULL COMMENT '乐观锁字段',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户id',
  `create_at` datetime NOT NULL COMMENT '创建时间的时间戳',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `update_at` datetime DEFAULT NULL COMMENT '修改时间的时间戳',
  `update_by` bigint DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `create_at` (`create_at`) USING BTREE,
  KEY `update_at` (`update_at`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='爬虫定义表';
