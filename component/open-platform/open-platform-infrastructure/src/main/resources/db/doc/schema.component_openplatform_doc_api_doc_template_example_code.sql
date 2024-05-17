-- 建表语句sql
DROP TABLE IF EXISTS component_openplatform_doc_api_doc_template_example_code;
CREATE TABLE `component_openplatform_doc_api_doc_template_example_code` (
  `id` bigint NOT NULL COMMENT '主键id',
  `lang_dict_id` bigint NOT NULL COMMENT '开发语言，字典id',
  `example_code` longtext NOT NULL COMMENT '示例代码片段',
  `example_download_url` varchar(300) DEFAULT NULL COMMENT '示例代码下载地址',
  `openplatform_doc_api_doc_template_id` bigint NOT NULL COMMENT '开放接口文档模板id',
  `seq` int NOT NULL COMMENT '排序,默认按该字段升序排序',
  `remark` varchar(255) DEFAULT NULL COMMENT '描述',
  `version` int NOT NULL COMMENT '乐观锁字段',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户id',
  `create_at` datetime NOT NULL COMMENT '创建时间的时间戳',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `update_at` datetime DEFAULT NULL COMMENT '修改时间的时间戳',
  `update_by` bigint DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `lang_dict_id` (`lang_dict_id`) USING BTREE,
  KEY `openplatform_doc_api_doc_template_id` (`openplatform_doc_api_doc_template_id`) USING BTREE,
  KEY `create_at` (`create_at`) USING BTREE,
  KEY `update_at` (`update_at`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='开放接口文档模板示例代码表';
