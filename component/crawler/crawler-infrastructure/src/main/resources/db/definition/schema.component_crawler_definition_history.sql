-- 建表语句sql
DROP TABLE IF EXISTS component_crawler_definition_history;
CREATE TABLE `component_crawler_definition_history` (
  `id` bigint NOT NULL COMMENT '主键ID',
  `crawler_definition_id` bigint NOT NULL COMMENT '爬虫定义id',
  `crawler_definition_version` int NOT NULL COMMENT '定义版本号，从1开始递增',
  `definition_json` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin COMMENT '流程图数据',
  `config_json` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin COMMENT '爬虫级配置json',
  `is_publish` tinyint(1) NOT NULL COMMENT '是否发布，1=已发布，0=未发布，草稿',
  `version` int NOT NULL COMMENT '乐观锁字段',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户id',
  `create_at` datetime NOT NULL COMMENT '创建时间的时间戳',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `update_at` datetime DEFAULT NULL COMMENT '修改时间的时间戳',
  `update_by` bigint DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uni_crawler_definition_id__crawler_definition_version` (`crawler_definition_id`,`crawler_definition_version`) USING BTREE,
  KEY `crawler_definition_id` (`crawler_definition_id`) USING BTREE,
  KEY `create_at` (`create_at`) USING BTREE,
  KEY `update_at` (`update_at`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='爬虫定义历史表';
