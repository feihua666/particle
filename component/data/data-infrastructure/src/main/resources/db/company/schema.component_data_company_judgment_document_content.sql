-- 建表语句sql
DROP TABLE IF EXISTS component_data_company_judgment_document_content;
CREATE TABLE `component_data_company_judgment_document_content` (
  `id` bigint NOT NULL COMMENT '主键ID',
  `company_judgment_document_id` bigint DEFAULT NULL COMMENT '裁判文书表id',
  `content` longtext COMMENT '裁判内容',
  `data_md5` varchar(32) NOT NULL COMMENT '数据md5,content',
  `latest_handle_at` datetime DEFAULT NULL COMMENT '最后处理时间，不代表数据有变动，用来表示数据处理过，但可能无需处理，不影响版本号变动',
  `version` int NOT NULL COMMENT '乐观锁字段',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户id',
  `create_at` datetime NOT NULL COMMENT '创建时间的时间戳',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `update_at` datetime DEFAULT NULL COMMENT '修改时间的时间戳',
  `update_by` bigint DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `company_judgment_document_id` (`company_judgment_document_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='企业裁判文书内容表';
