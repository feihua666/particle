-- 建表语句sql
DROP TABLE IF EXISTS component_data_query_datasource_api;
CREATE TABLE `component_data_query_datasource_api` (
  `id` bigint NOT NULL COMMENT 'ID',
  `code` varchar(50) DEFAULT NULL COMMENT '编码',
  `name` varchar(100) NOT NULL COMMENT '名称',
  `category_dict_id` bigint NOT NULL COMMENT '分类，字典id，一般仅用来分类',
  `data_query_provider_id` bigint NOT NULL COMMENT '数据查询供应商id,服务接口必填',
  `data_query_provider_doc_link_url` varchar(500) DEFAULT NULL COMMENT '文档链接,应该填写供应商提供的链接，以查阅相关文档，一般以http(s)开头',
  `data_query_datasource_id` bigint NOT NULL COMMENT '数据查询数据源id,服务接口必填',
  `in_param_type_dict_id` bigint DEFAULT NULL COMMENT '入参类型，字典id，为空表示无入参',
  `in_param_example_config_json` mediumtext COMMENT '入参示例，纯文本展示',
  `in_param_test_case_data_config_json` mediumtext COMMENT '入参测试用例数据，该数据应该配合入参配置json或json配置识别',
  `in_param_doc_config_json` mediumtext COMMENT '入参文档配置json',
  `in_param_validate_config_json` mediumtext COMMENT '入参校验配置json',
  `in_param_ext_config_json` mediumtext COMMENT '入参扩展配置json',
  `out_param_type_dict_id` bigint NOT NULL COMMENT '出参类型，字典id',
  `out_param_example_config_json` mediumtext COMMENT '出参示例，纯文本展示',
  `out_param_doc_config_json` mediumtext COMMENT '出参文档配置json',
  `out_param_success_config_json` mediumtext COMMENT '出参成功或失败配置json',
  `out_param_trans_config_json` mediumtext COMMENT '出参翻译配置json',
  `out_param_ext_config_json` mediumtext COMMENT '出参扩展配置json',
  `response_type_dict_id` bigint NOT NULL COMMENT '输出类型，字典id，用来定义响应数据格式',
  `pageable_adapter_config_json` mediumtext COMMENT '分页适配信息配置json',
  `config_json` text COMMENT '基础配置json，根据数据源类型对应不同的配置信息',
  `dict_config_json` text COMMENT '字典配置json，为入参和出参字典提供支持',
  `rate_limit_control_config_json` text COMMENT '流量控制配置json',
  `circuit_breaker_control_config_json` text COMMENT '熔断控制配置json',
  `read_timeout` int DEFAULT NULL COMMENT '读取等待时间，单位ms，超过该时间将会放弃',
  `connect_timeout` int DEFAULT NULL COMMENT '连接等待时间，单位ms，超过该时间将会放弃',
  `same_tag` varchar(255) DEFAULT NULL COMMENT '等同标签，如果两个api的入参和出参相同，对接口打一个标签，同时另一个相同的接口打同样的标签，以代表两个接口相同',
  `remark` varchar(255) DEFAULT NULL COMMENT '描述,注意事项等',
  `version` int NOT NULL COMMENT '乐观锁字段',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户id',
  `create_at` datetime NOT NULL COMMENT '创建时间的时间戳',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `update_at` datetime DEFAULT NULL COMMENT '修改时间的时间戳',
  `update_by` bigint DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `code` (`code`),
  KEY `version` (`version`) USING BTREE,
  KEY `name` (`name`) USING BTREE,
  KEY `category_dict_id` (`category_dict_id`),
  KEY `data_query_provider_id` (`data_query_provider_id`),
  KEY `data_query_datasource_id` (`data_query_datasource_id`),
  KEY `response_type_dict_id` (`response_type_dict_id`),
  KEY `same_tag` (`same_tag`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='数据查询数据源接口表';