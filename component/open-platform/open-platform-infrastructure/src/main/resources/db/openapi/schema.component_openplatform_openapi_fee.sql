-- 建表语句sql
DROP TABLE IF EXISTS component_openplatform_openapi_fee;
CREATE TABLE `component_openplatform_openapi_fee` (
  `id` bigint NOT NULL COMMENT '表主键',
  `name` varchar(100) NOT NULL COMMENT '费用名称，可以理解为类似一个套餐',
  `price` int NOT NULL COMMENT '单价，分/条',
  `fee_type_dict_id` bigint NOT NULL COMMENT '计费方式，字典id，如：按条，按月，按阶梯等',
  `fee_type_json` text COMMENT '按计费方式配置',
  `deduplicate_type_dict_id` bigint NOT NULL COMMENT '去重方式，字典id，如：按月去重，按周去重，不去重等',
  `deduplicate_count` int DEFAULT NULL COMMENT '去重条数，如果去重表示每多少条算一条',
  `is_deduplicate_by_parameter` tinyint(1) NOT NULL COMMENT '是否按请求参数去重，1=按参数去重，0=按接口去重',
  `is_check_has_value` tinyint(1) NOT NULL COMMENT '是否检查是否返回值，1=检查，如果没有返回值不计费，0=不检查，直接计费',
  `is_check_handle_duration` tinyint(1) NOT NULL COMMENT '是否检查处理时长，1=检查，0=不检查',
  `handle_duration` int DEFAULT NULL COMMENT '处理时长，单位毫秒，如果检查处理时长，超过该时长不计费',
  `remark` varchar(255) DEFAULT NULL COMMENT '描述',
  `version` int NOT NULL COMMENT '乐观锁字段',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户id',
  `create_at` datetime NOT NULL COMMENT '创建时间的时间戳',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `update_at` datetime DEFAULT NULL COMMENT '修改时间的时间戳',
  `update_by` bigint DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `fee_type_dict_id` (`fee_type_dict_id`),
  KEY `price` (`price`) USING BTREE,
  KEY `create_at` (`create_at`) USING BTREE,
  KEY `update_at` (`update_at`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='开放平台开放接口费用表';
