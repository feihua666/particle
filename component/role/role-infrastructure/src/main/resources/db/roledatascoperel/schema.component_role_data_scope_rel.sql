-- 建表语句sql
DROP TABLE IF EXISTS component_role_data_scope_rel;
CREATE TABLE `component_role_data_scope_rel` (
  `id` bigint NOT NULL COMMENT '主键',
  `role_id` bigint NOT NULL COMMENT '角色id',
  `data_object_id` bigint NOT NULL COMMENT '数据对象id',
  `data_scope_id` bigint NOT NULL COMMENT '数据范围id',
  `version` int NOT NULL COMMENT '乐观锁字段',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户id',
  `create_at` datetime NOT NULL COMMENT '创建时间的时间戳',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `update_at` datetime DEFAULT NULL COMMENT '修改时间的时间戳',
  `update_by` bigint DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uni_role_id_data_object_id_data_scope_id` (`role_id`,`data_object_id`,`data_scope_id`) USING BTREE,
  KEY `idx_role_id` (`role_id`) USING BTREE,
  KEY `idx_data_scope_id` (`data_scope_id`) USING BTREE,
  KEY `create_at` (`create_at`) USING BTREE,
  KEY `update_at` (`update_at`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='角色数据范围关系表';
