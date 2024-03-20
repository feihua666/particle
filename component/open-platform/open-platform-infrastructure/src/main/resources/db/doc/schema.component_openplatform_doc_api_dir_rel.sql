-- 建表语句sql
DROP TABLE IF EXISTS component_openplatform_doc_api_dir_rel;
CREATE TABLE `component_openplatform_doc_api_dir_rel` (
  `id` bigint NOT NULL COMMENT '主键',
  `openplatform_doc_api_id` bigint NOT NULL COMMENT '开放接口文档接口id',
  `openplatform_doc_dir_id` bigint NOT NULL COMMENT '开放接口文档目录id',
  `version` int NOT NULL COMMENT '乐观锁字段',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户id',
  `create_at` datetime NOT NULL COMMENT '创建时间的时间戳',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `update_at` datetime DEFAULT NULL COMMENT '修改时间的时间戳',
  `update_by` bigint DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uni_openplatform_doc_api_id__openplatform_doc_dir_id` (`openplatform_doc_api_id`,`openplatform_doc_dir_id`) USING BTREE,
  KEY `openplatform_doc_api_id` (`openplatform_doc_api_id`) USING BTREE,
  KEY `openplatform_doc_dir_id` (`openplatform_doc_dir_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='开放接口文档接口与目录关系表';
