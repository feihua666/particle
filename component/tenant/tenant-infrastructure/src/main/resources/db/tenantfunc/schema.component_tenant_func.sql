-- 建表语句sql
DROP TABLE IF EXISTS component_tenant_func;
CREATE TABLE `component_tenant_func` (
  `id` bigint NOT NULL COMMENT '主键',
  `func_id` bigint NOT NULL COMMENT '功能id',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `func_application_id` bigint NOT NULL COMMENT '功能应用id',
  `version` int NOT NULL COMMENT '乐观锁字段',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户id',
  `create_at` datetime NOT NULL COMMENT '创建时间的时间戳',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `update_at` datetime DEFAULT NULL COMMENT '修改时间的时间戳',
  `update_by` bigint DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uni_ func_id_tenant_id` (`func_id`,`tenant_id`),
  KEY `idx_func_id` (`func_id`) USING BTREE,
  KEY `create_at` (`create_at`) USING BTREE,
  KEY `update_at` (`update_at`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='租户功能菜单表';
