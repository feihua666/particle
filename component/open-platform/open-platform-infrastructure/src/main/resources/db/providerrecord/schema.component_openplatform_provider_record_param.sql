-- 建表语句sql
DROP TABLE IF EXISTS component_openplatform_provider_record_param;
CREATE TABLE `component_openplatform_provider_record_param` (
  `id` bigint NOT NULL COMMENT '表主键',
  `openplatform_provider_record_id` bigint NOT NULL COMMENT '供应商调用记录id',
  `request_param` text COMMENT '请求参数',
  `response_result` longtext COMMENT '响应结果',
  `version` int NOT NULL COMMENT '乐观锁字段',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户id',
  `create_at` datetime NOT NULL COMMENT '创建时间的时间戳',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `update_at` datetime DEFAULT NULL COMMENT '修改时间的时间戳',
  `update_by` bigint DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `openplatform_openapi_supplier_record_id` (`openplatform_provider_record_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='开放平台开放接口供应商调用记录参数表';
