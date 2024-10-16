-- 建表语句sql
DROP TABLE IF EXISTS component_openplatform_openapi_limit_rule;
CREATE TABLE `component_openplatform_openapi_limit_rule` (
  `id` bigint NOT NULL COMMENT '表主键',
  `name` varchar(100) NOT NULL COMMENT '限制名称',
  `limit_type_dict_id` bigint NOT NULL COMMENT '限制方式，字典id，如：按条限制，按金额费用限制，不限制有等',
  `limit_count` int NOT NULL COMMENT '限制条数',
  `limit_fee` int NOT NULL COMMENT '限制金额费用，单位分',
  `limit_period_dict_id` bigint NOT NULL COMMENT '限制周期，按天限制、按周限制等',
  `limit_rate` int NOT NULL COMMENT '限制速率，即qps',
  `remark` varchar(255) DEFAULT NULL COMMENT '描述',
  `version` int NOT NULL COMMENT '乐观锁字段',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户id',
  `create_at` datetime NOT NULL COMMENT '创建时间的时间戳',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `update_at` datetime DEFAULT NULL COMMENT '修改时间的时间戳',
  `update_by` bigint DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `name` (`name`),
  KEY `limit_type_dict_id` (`limit_type_dict_id`),
  KEY `limit_period_dict_id` (`limit_period_dict_id`),
  KEY `create_at` (`create_at`) USING BTREE,
  KEY `update_at` (`update_at`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='开放平台开放接口限制规则表';
