-- 建表语句sql
DROP TABLE IF EXISTS component_openplatform_provider_api;
CREATE TABLE `component_openplatform_provider_api` (
  `id` bigint NOT NULL COMMENT '表主键',
  `code` varchar(50) DEFAULT NULL COMMENT '编码，唯一',
  `name` varchar(50) NOT NULL COMMENT '供应商名称',
  `data_query_datasource_api_id` bigint DEFAULT NULL COMMENT '数据查询数据源接口id，兼容一下数据查询数据进行统一',
  `openplatform_openapi_fee_id` bigint DEFAULT NULL COMMENT '计费id，不配置不计费',
  `request_url` varchar(500) DEFAULT NULL COMMENT '请求地址，一般为http开关的绝对地址',
  `script_type_dict_value` varchar(100) DEFAULT NULL COMMENT '脚本类型，字典值，如：groovy_script',
  `script_content` text COMMENT '脚本内容',
  `read_timeout` int DEFAULT NULL COMMENT '读取等待时间，单位ms，超过该时间将会放弃',
  `connect_timeout` int DEFAULT NULL COMMENT '连接等待时间，单位ms，超过该时间将会放弃',
  `remark` varchar(255) DEFAULT NULL COMMENT '描述',
  `version` int NOT NULL COMMENT '乐观锁字段',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户id',
  `create_at` datetime NOT NULL COMMENT '创建时间的时间戳',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `update_at` datetime DEFAULT NULL COMMENT '修改时间的时间戳',
  `update_by` bigint DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `code` (`code`) USING BTREE,
  UNIQUE KEY `data_query_datasource_api_id` (`data_query_datasource_api_id`) USING BTREE,
  KEY `name` (`name`) USING BTREE,
  KEY `create_at` (`create_at`) USING BTREE,
  KEY `update_at` (`update_at`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='开放平台供应商接口表';
