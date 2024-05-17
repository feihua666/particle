-- 建表语句sql
DROP TABLE IF EXISTS component_data_query_datasource;
CREATE TABLE `component_data_query_datasource` (
  `id` bigint NOT NULL COMMENT 'ID',
  `code` varchar(50) DEFAULT NULL COMMENT '编码',
  `name` varchar(100) NOT NULL COMMENT '名称',
  `type_dict_id` bigint NOT NULL COMMENT '类型，字典id',
  `config_json` text NOT NULL COMMENT 'json配置，根据数据源类型对应不同的配置信息',
  `username` varchar(50) DEFAULT NULL COMMENT '用户名',
  `password` varchar(200) DEFAULT NULL COMMENT '密码',
  `data_query_provider_id` bigint NOT NULL COMMENT '数据查询供应商id',
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
  KEY `create_at` (`create_at`) USING BTREE,
  KEY `update_at` (`update_at`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='数据查询数据源表';
