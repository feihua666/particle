-- 建表语句sql
DROP TABLE IF EXISTS component_func_application_func_rel;
CREATE TABLE `component_func_application_func_rel` (
  `id` bigint NOT NULL COMMENT '主键',
  `func_application_id` bigint NOT NULL COMMENT '功能应用id',
  `func_id` bigint NOT NULL COMMENT '功能id',
  `version` int NOT NULL COMMENT '乐观锁字段',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户id',
  `create_at` datetime NOT NULL COMMENT '创建时间的时间戳',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `update_at` datetime DEFAULT NULL COMMENT '修改时间的时间戳',
  `update_by` bigint DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uni_func_application_id_func_id` (`func_application_id`,`func_id`) USING BTREE,
  KEY `idx_func_application_id` (`func_application_id`) USING BTREE,
  KEY `idx_func_id` (`func_id`) USING BTREE,
  KEY `create_at` (`create_at`) USING BTREE,
  KEY `update_at` (`update_at`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='功能应用功能关系表';
