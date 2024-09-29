-- 建表语句sql
DROP TABLE IF EXISTS component_openplatform_openapi_batch_query_record_detail;
CREATE TABLE `component_openplatform_openapi_batch_query_record_detail` (
  `id` bigint NOT NULL COMMENT '主键',
  `openplatform_openapi_batch_query_record_id` bigint NOT NULL COMMENT '开放接口批量查询记录id',
  `execute_status_dict_id` bigint NOT NULL COMMENT '执行状态，字典id',
  `is_success` tinyint(1) DEFAULT NULL COMMENT '是否成功，查询后有值',
  `request_nonce` varchar(255) DEFAULT NULL COMMENT '请求流水号，查询后有值',
  `query_at` datetime DEFAULT NULL COMMENT '查询时间，查询后有值',
  `trace_id` varchar(50) DEFAULT NULL COMMENT '追踪id，查询后有值',
  `span_id` varchar(50) DEFAULT NULL COMMENT '追踪分支id，查询后有值',
  `request_param` longtext COMMENT '请求参数，解析文件后有值',
  `query_string` varchar(2000) DEFAULT NULL COMMENT '请求查询字符串，解析文件后有值',
  `response_result` longtext COMMENT '响应结果',
  `version` int NOT NULL COMMENT '乐观锁字段',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户id',
  `create_at` datetime NOT NULL COMMENT '创建时间的时间戳',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `update_at` datetime DEFAULT NULL COMMENT '修改时间的时间戳',
  `update_by` bigint DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `openplatform_openapi_batch_query_record_id` (`openplatform_openapi_batch_query_record_id`),
  KEY `execute_status_dict_id` (`execute_status_dict_id`),
  KEY `create_at` (`create_at`) USING BTREE,
  KEY `update_at` (`update_at`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='开放接口批量查询记录明细表';
