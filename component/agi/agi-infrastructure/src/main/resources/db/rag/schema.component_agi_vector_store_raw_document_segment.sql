-- 建表语句sql
DROP TABLE IF EXISTS component_agi_vector_store_raw_document_segment;
CREATE TABLE `component_agi_vector_store_raw_document_segment` (
  `id` bigint NOT NULL COMMENT '主键ID',
  `agi_vector_store_raw_document_id` bigint NOT NULL COMMENT '知识存储原始文档表id',
  `content` longtext NOT NULL COMMENT '名称，可以是文件名，标题等',
  `metadata_json` longtext DEFAULT NULL COMMENT '元数据信息json',
  `is_embedded` tinyint(1) NOT NULL COMMENT '是否已嵌入',
  `remark` varchar(255) DEFAULT NULL COMMENT '描述',
  `version` int NOT NULL COMMENT '乐观锁字段',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户id',
  `create_at` datetime NOT NULL COMMENT '创建时间的时间戳',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `update_at` datetime DEFAULT NULL COMMENT '修改时间的时间戳',
  `update_by` bigint DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `agi_vector_store_raw_document_id` (`agi_vector_store_raw_document_id`) USING BTREE,
  KEY `version` (`version`) USING BTREE,
  KEY `create_at` (`create_at`) USING BTREE,
  KEY `update_at` (`update_at`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='知识存储原始文档片段表';
