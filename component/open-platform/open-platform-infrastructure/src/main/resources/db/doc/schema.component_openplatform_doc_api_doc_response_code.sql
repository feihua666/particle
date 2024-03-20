-- 建表语句sql
DROP TABLE IF EXISTS component_openplatform_doc_api_doc_response_code;
CREATE TABLE `component_openplatform_doc_api_doc_response_code` (
  `id` bigint NOT NULL COMMENT '主键id',
  `code` varchar(100) NOT NULL COMMENT '编码，码值',
  `http_code` int default NULL COMMENT 'http响应码,如：200、500',
  `is_charge` tinyint(1) NOT NULL COMMENT '是否计费',
  `explanation` varchar(2000) COLLATE utf8_bin NOT NULL COMMENT '字段说明',
  `openplatform_doc_api_id` bigint NOT NULL COMMENT '开放接口文档接口id',
  `openplatform_doc_api_doc_id` bigint NOT NULL COMMENT '开放接口文档id',
  `seq` int NOT NULL COMMENT '排序,默认按该字段升序排序',
  `is_global` tinyint(1) NOT NULL COMMENT '是否全局通用码',
  `remark` varchar(255) DEFAULT NULL COMMENT '描述',
  `version` int NOT NULL COMMENT '乐观锁字段',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户id',
  `create_at` datetime NOT NULL COMMENT '创建时间的时间戳',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `update_at` datetime DEFAULT NULL COMMENT '修改时间的时间戳',
  `update_by` bigint DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `code` (`code`) USING BTREE,
  KEY `openplatform_doc_api_id` (`openplatform_doc_api_id`) USING BTREE,
  KEY `openplatform_doc_api_doc_id` (`openplatform_doc_api_doc_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='开放接口文档响应码表';
