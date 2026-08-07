-- 建表语句sql
DROP TABLE IF EXISTS component_crawler_raw_store_content;
CREATE TABLE `component_crawler_raw_store_content` (
  `id` bigint NOT NULL COMMENT '主键ID',
  `crawler_raw_store_id` bigint NOT NULL COMMENT '爬虫原始数据存储ID',
  `content` longtext COLLATE utf8mb4_bin COMMENT '存储内容文本',
  `version` int NOT NULL COMMENT '乐观锁字段',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户id',
  `create_at` datetime NOT NULL COMMENT '创建时间的时间戳',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `update_at` datetime DEFAULT NULL COMMENT '修改时间的时间戳',
  `update_by` bigint DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_crawler_raw_store_id` (`crawler_raw_store_id`),
  KEY `idx_create_at` (`create_at`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='爬虫原始数据存储内容表';
