-- 建表语句sql
DROP TABLE IF EXISTS component_openplatform_doc_api_doc;
CREATE TABLE `component_openplatform_doc_api_doc` (
  `id` bigint NOT NULL COMMENT '主键',
  `openplatform_doc_api_id` bigint NOT NULL COMMENT '开放接口文档接口id',
  `request_url_prefix` varchar(300) DEFAULT NULL COMMENT '请求地址前缀，可全局配置',
  `request_url` varchar(300) NOT NULL COMMENT '请求地址',
  `request_type_dict_id` bigint DEFAULT NULL COMMENT '请求类型，字典id，如：post、get',
  `request_body_type_dict_id` bigint DEFAULT NULL COMMENT '请求体类型，字典id，如：json、xml',
  `response_body_type_dict_id` bigint DEFAULT NULL COMMENT '响应体类型，字典id，如：json、xml',
  `response_max_duration` int DEFAULT NULL COMMENT '最大响应时长，单位ms',
  `response_max_duration_desc` varchar(200) DEFAULT NULL COMMENT '响应时长文本，response_max_duration不支持时作为备用',
  `authentication_type` varchar(2000) DEFAULT NULL COMMENT '认证方式',
  `description_detail` text COMMENT '详细描述，详细描述文档内容或返回参数信息',
  `request_param_example` varchar(2000) NOT NULL COMMENT '请求参数示例',
  `response_param_example` longtext NOT NULL COMMENT '响应参数示例',
  `openplatform_doc_api_doc_template_id` bigint default NULL COMMENT '开放接口文档模板id',
  `version` int NOT NULL COMMENT '乐观锁字段',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户id',
  `create_at` datetime NOT NULL COMMENT '创建时间的时间戳',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `update_at` datetime DEFAULT NULL COMMENT '修改时间的时间戳',
  `update_by` bigint DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `openplatform_doc_api_id` (`openplatform_doc_api_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='开放接口文档表';
