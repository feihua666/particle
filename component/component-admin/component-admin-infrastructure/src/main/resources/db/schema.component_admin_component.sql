-- 建表语句sql
DROP TABLE IF EXISTS component_admin_component;
CREATE TABLE `component_admin_component` (
  `id` bigint NOT NULL COMMENT 'id主键',
  `code` varchar(100) NOT NULL COMMENT '组件英文名称,如：area、user',
  `name` varchar(100) NOT NULL COMMENT '组件中文名称，一般对应名称的中文',
  `path` varchar(200) NOT NULL COMMENT '组件路径，相对于项目路径如：component/area',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `version` int NOT NULL COMMENT '乐观锁字段',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户id',
  `create_at` datetime NOT NULL COMMENT '创建时间的时间戳',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `update_at` datetime DEFAULT NULL COMMENT '修改时间的时间戳',
  `update_by` bigint DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `code` (`code`),
  KEY `create_at` (`create_at`) USING BTREE,
  KEY `update_at` (`update_at`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='组件表';
