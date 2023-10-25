-- 建表语句sql
DROP TABLE IF EXISTS component_usage_count_config;
CREATE TABLE `component_usage_count_config` (
  `id` bigint NOT NULL COMMENT 'id主键',
  `name` varchar(100) NOT NULL COMMENT '名称,模糊查询',
  `usage_count_define_id` bigint NOT NULL COMMENT '使用次数定义id',
  `limit_count` int DEFAULT NULL COMMENT '限制的最大使用次数，0或不填写不限制',
  `limit_rule_type_dict_id` bigint NOT NULL COMMENT '限制规则类型字典id',
  `limit_period_dict_id` bigint NOT NULL COMMENT '限制周期字典id',
  `limit_tenant_id` bigint DEFAULT NULL COMMENT '限制租户id，如果为空代表是全局的设置',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `version` int NOT NULL COMMENT '乐观锁字段',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户id',
  `create_at` datetime NOT NULL COMMENT '创建时间的时间戳',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `update_at` datetime DEFAULT NULL COMMENT '修改时间的时间戳',
  `update_by` bigint DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `usage_count_define_id_limit_tenant_id` (`usage_count_define_id`,`limit_tenant_id`),
  KEY `create_at` (`create_at`) USING BTREE,
  KEY `update_at` (`update_at`) USING BTREE,
  KEY `limit_period_dict_id` (`limit_period_dict_id`),
  KEY `limit_rule_type_dict_id` (`limit_rule_type_dict_id`),
  KEY `usage_count_define_id` (`usage_count_define_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='使用次数配置表';
