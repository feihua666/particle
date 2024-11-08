-- 建表语句sql
DROP TABLE IF EXISTS component_navigation_site_tag;
CREATE TABLE `component_navigation_site_tag` (
  `id` bigint NOT NULL COMMENT 'id主键',
  `code` varchar(255) DEFAULT NULL COMMENT '标签编码',
  `name` varchar(255) NOT NULL COMMENT '标签名称',
  `group_dict_id` bigint NOT NULL COMMENT '分组，字典id',
  `seq` int NOT NULL COMMENT '排序,默认按该字段升序排序',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `version` int NOT NULL COMMENT '乐观锁字段',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户id',
  `create_at` datetime NOT NULL COMMENT '创建时间的时间戳',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `update_at` datetime DEFAULT NULL COMMENT '修改时间的时间戳',
  `update_by` bigint DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `name` (`name`),
  UNIQUE KEY `code` (`code`),
  KEY `create_at` (`create_at`) USING BTREE,
  KEY `update_at` (`update_at`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='导航网站标签表';
