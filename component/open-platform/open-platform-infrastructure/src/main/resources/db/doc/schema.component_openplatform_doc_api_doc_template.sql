-- 建表语句sql
DROP TABLE IF EXISTS component_openplatform_doc_api_doc_template;
CREATE TABLE `component_openplatform_doc_api_doc_template` (
  `id` bigint NOT NULL COMMENT '主键',
  `name` varchar(100) NOT NULL COMMENT '模板名称',
  `request_url_prefix` varchar(300) DEFAULT NULL COMMENT '请求地址前缀，可全局配置',
  `request_url_intranet_prefix` varchar(300) DEFAULT NULL COMMENT '内网请求地址前缀，可全局配置',
  `request_type_dict_id` bigint DEFAULT NULL COMMENT '请求类型，字典id，如：post、get',
  `request_body_type_dict_id` bigint DEFAULT NULL COMMENT '请求体类型，字典id，如：json、xml',
  `request_param_type_dict_id` bigint default NULL COMMENT '请求参数类型，字典id，如：string、array',
  `request_param_nest_type_dict_id` bigint default NULL COMMENT '请求参数嵌套字段类型，字典id，一般用于字段类型为array时里面的类型，如：string、object',
  `response_body_type_dict_id` bigint DEFAULT NULL COMMENT '响应体类型，字典id，如：json、xml',
  `response_max_duration` int DEFAULT NULL COMMENT '最大响应时长，单位ms',
  `response_max_duration_desc` varchar(200) DEFAULT NULL COMMENT '响应时长文本，response_max_duration不支持时作为备用',
  `authentication_type` varchar(2000) DEFAULT NULL COMMENT '认证方式',
  `version` int NOT NULL COMMENT '乐观锁字段',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户id',
  `create_at` datetime NOT NULL COMMENT '创建时间的时间戳',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `update_at` datetime DEFAULT NULL COMMENT '修改时间的时间戳',
  `update_by` bigint DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `name` (`name`),
  KEY `create_at` (`create_at`) USING BTREE,
  KEY `update_at` (`update_at`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='开放接口文档模板表';
