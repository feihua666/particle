-- 建表语句sql
DROP TABLE IF EXISTS component_navigation_site_category_rel;
CREATE TABLE `component_navigation_site_category_rel` (
  `id` bigint NOT NULL COMMENT '主键',
  `navigation_site_id` bigint NOT NULL COMMENT '导航网站id',
  `navigation_category_id` bigint NOT NULL COMMENT '导航分类id',
  `seq` int NOT NULL COMMENT '排序,默认按该字段升序排序',
  `version` int NOT NULL COMMENT '乐观锁字段',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户id',
  `create_at` datetime NOT NULL COMMENT '创建时间的时间戳',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `update_at` datetime DEFAULT NULL COMMENT '修改时间的时间戳',
  `update_by` bigint DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uni_role_id_func_id` (`navigation_site_id`,`navigation_category_id`) USING BTREE,
  KEY `idx_navigation_site_id` (`navigation_site_id`) USING BTREE,
  KEY `idx_navigation_category_id` (`navigation_category_id`) USING BTREE,
  KEY `create_at` (`create_at`) USING BTREE,
  KEY `update_at` (`update_at`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='导航网站分类关系表';
