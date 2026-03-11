-- 建表语句sql
DROP TABLE IF EXISTS component_admin_component_dependency;
CREATE TABLE `component_admin_component_dependency` (
  `id` bigint NOT NULL COMMENT 'id主键',
  `component_id` bigint NOT NULL COMMENT '源组件id',
  `depend_component_id` bigint NOT NULL COMMENT '依赖组件id',
  `is_required` tinyint(1) NOT NULL COMMENT '是否必需：1=必须，0=可选',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `version` int NOT NULL COMMENT '乐观锁字段',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户id',
  `create_at` datetime NOT NULL COMMENT '创建时间的时间戳',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `update_at` datetime DEFAULT NULL COMMENT '修改时间的时间戳',
  `update_by` bigint DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uni_component_id__depend_component_id` (`component_id`,`depend_component_id`),
  KEY `component_id` (`component_id`) USING BTREE,
  KEY `depend_component_id` (`depend_component_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='组件依赖关系表';
