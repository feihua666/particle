-- 建表语句sql
DROP TABLE IF EXISTS component_openplatform_provider;
CREATE TABLE `component_openplatform_provider` (
  `id` bigint NOT NULL COMMENT '表主键',
  `code` varchar(50) DEFAULT NULL COMMENT '编码，唯一',
  `name` varchar(50) NOT NULL COMMENT '供应商名称',
  `data_query_provider_id` bigint DEFAULT NULL COMMENT '数据查询供应商id，兼容一下数据查询数据进行统一供应商',
  `remark` varchar(255) DEFAULT NULL COMMENT '描述',
  `version` int NOT NULL COMMENT '乐观锁字段',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户id',
  `create_at` datetime NOT NULL COMMENT '创建时间的时间戳',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `update_at` datetime DEFAULT NULL COMMENT '修改时间的时间戳',
  `update_by` bigint DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `code` (`code`),
  UNIQUE KEY `data_query_provider_id` (`data_query_provider_id`),
  KEY `name` (`name`) USING BTREE,
  KEY `create_at` (`create_at`) USING BTREE,
  KEY `update_at` (`update_at`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='开放平台开放接口供应商表';
