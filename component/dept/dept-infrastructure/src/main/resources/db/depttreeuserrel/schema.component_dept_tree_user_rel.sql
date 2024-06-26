-- 建表语句sql
DROP TABLE IF EXISTS component_dept_tree_user_rel;
CREATE TABLE `component_dept_tree_user_rel` (
  `id` bigint NOT NULL COMMENT '主键',
  `user_id` bigint NOT NULL COMMENT '用户id',
  `dept_tree_id` bigint NOT NULL COMMENT '部门树id',
  `version` int NOT NULL COMMENT '乐观锁字段',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户id',
  `create_at` datetime NOT NULL COMMENT '创建时间的时间戳',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `update_at` datetime DEFAULT NULL COMMENT '修改时间的时间戳',
  `update_by` bigint DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uni_dept_id_user_tree_id` (`user_id`,`dept_tree_id`) USING BTREE,
  KEY `idx_dept_tree_id` (`dept_tree_id`) USING BTREE,
  KEY `idx_user_id` (`user_id`) USING BTREE,
  KEY `create_at` (`create_at`) USING BTREE,
  KEY `update_at` (`update_at`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='部门树用户关系表';
