-- 建表语句sql
DROP TABLE IF EXISTS component_data_scope_custom_data_rel;
CREATE TABLE `component_data_scope_custom_data_rel` (
  `id` bigint NOT NULL COMMENT '主键',
  `data_scope_id` bigint NOT NULL COMMENT '数据范围id',
  `data_id` bigint NOT NULL COMMENT '自定义数据id',
  `version` int NOT NULL COMMENT '乐观锁字段',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户id',
  `create_at` datetime NOT NULL COMMENT '创建时间的时间戳',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `update_at` datetime DEFAULT NULL COMMENT '修改时间的时间戳',
  `update_by` bigint DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uni_data_scope_id_data_id` (`data_scope_id`,`data_id`) USING BTREE,
  KEY `data_scope_id` (`data_scope_id`) USING BTREE,
  KEY `data_id` (`data_id`) USING BTREE,
  KEY `create_at` (`create_at`) USING BTREE,
  KEY `update_at` (`update_at`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='数据范围和自定义数据关系表';
