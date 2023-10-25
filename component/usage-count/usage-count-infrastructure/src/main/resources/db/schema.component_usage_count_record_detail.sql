-- 建表语句sql
DROP TABLE IF EXISTS component_usage_count_record_detail;
CREATE TABLE `component_usage_count_record_detail` (
  `id` bigint NOT NULL COMMENT 'id主键',
  `usage_count_record_id` bigint NOT NULL COMMENT '使用次数记录id',
  `usage_count_define_id` bigint NOT NULL COMMENT '使用次数定义id',
  `usage_user_id` bigint DEFAULT NULL COMMENT '使用用户id',
  `usage_tenant_id` bigint DEFAULT NULL COMMENT '使用租户id',
  `version` int NOT NULL COMMENT '乐观锁字段',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户id',
  `create_at` datetime NOT NULL COMMENT '创建时间的时间戳',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `update_at` datetime DEFAULT NULL COMMENT '修改时间的时间戳',
  `update_by` bigint DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `usage_count_record_id` (`usage_count_record_id`) USING BTREE,
  KEY `usage_count_define_id` (`usage_count_define_id`) USING BTREE,
  KEY `usage_user_id` (`usage_user_id`) USING BTREE,
  KEY `usage_tenant_id` (`usage_tenant_id`) USING BTREE,
  KEY `create_at` (`create_at`) USING BTREE,
  KEY `update_at` (`update_at`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='使用次数记录明细表';
