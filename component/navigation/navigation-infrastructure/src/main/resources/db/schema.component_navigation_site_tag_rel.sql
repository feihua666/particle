-- 建表语句sql
DROP TABLE IF EXISTS component_navigation_site_tag_rel;
CREATE TABLE `component_navigation_site_tag_rel` (
  `id` bigint NOT NULL COMMENT 'id主键',
  `navigation_site_id` bigint NOT NULL COMMENT '网站id',
  `navigation_site_tag_id` bigint NOT NULL COMMENT '网站标签id',
  `version` int NOT NULL COMMENT '乐观锁字段',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户id',
  `create_at` datetime NOT NULL COMMENT '创建时间的时间戳',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `update_at` datetime DEFAULT NULL COMMENT '修改时间的时间戳',
  `update_by` bigint DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uni_navigation_site_id_navigation_site_tag_id` (`navigation_site_id`,`navigation_site_tag_id`) USING BTREE,
  KEY `navigation_site_id` (`navigation_site_id`),
  KEY `navigation_site_tag_id` (`navigation_site_tag_id`),
  KEY `create_at` (`create_at`) USING BTREE,
  KEY `update_at` (`update_at`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='导航网站标签关系表';
