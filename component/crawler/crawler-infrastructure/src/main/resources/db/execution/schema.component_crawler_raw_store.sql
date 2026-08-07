-- 建表语句sql
DROP TABLE IF EXISTS component_crawler_raw_store;
CREATE TABLE `component_crawler_raw_store` (
  `id` bigint NOT NULL COMMENT '主键ID',
  `crawler_execution_id` bigint DEFAULT NULL COMMENT '爬虫执行实例ID',
  `crawler_definition_id` bigint DEFAULT NULL COMMENT '爬虫定义ID',
  `crawler_definition_history_id` bigint DEFAULT NULL COMMENT '执行时使用的版本ID',
  `crawler_definition_name` varchar(200) COLLATE utf8mb4_bin NOT NULL COMMENT '爬虫定义名称',
  `page_url` varchar(300) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '页面地址',
  `page_title` varchar(300) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '页面标题',
  `status_dict_id` bigint NOT NULL COMMENT '状态字典id：stored/processed/processing',
  `version` int NOT NULL COMMENT '乐观锁字段',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户id',
  `create_at` datetime NOT NULL COMMENT '创建时间的时间戳',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `update_at` datetime DEFAULT NULL COMMENT '修改时间的时间戳',
  `update_by` bigint DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_crawler_execution_id` (`crawler_execution_id`),
  KEY `idx_status_dict_id` (`status_dict_id`),
  KEY `idx_create_at` (`create_at`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='爬虫原始数据存储表';
