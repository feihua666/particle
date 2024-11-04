-- 建表语句sql
DROP TABLE IF EXISTS component_navigation_submit;
CREATE TABLE `component_navigation_submit` (
  `id` bigint NOT NULL COMMENT '主键ID',
  `name` varchar(100) NOT NULL COMMENT '网站名称',
  `title` varchar(300) NOT NULL COMMENT '网站标题',
  `url` varchar(400) NOT NULL COMMENT '网站地址',
  `submit_at` datetime NOT NULL COMMENT '提交时间',
  `status_dict_id` bigint NOT NULL COMMENT '状态，字典id，新提交，数据补充中，数据补充完成，已提交',
  `site_data_json` text DEFAULT NULL COMMENT '网站数据json，数据补充时先补充到这里，最后提交到网站表中',
  `remark` varchar(255) DEFAULT NULL COMMENT '描述',
  `version` int NOT NULL COMMENT '乐观锁字段',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户id',
  `create_at` datetime DEFAULT NULL COMMENT '创建时间的时间戳',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `update_at` datetime DEFAULT NULL COMMENT '修改时间的时间戳',
  `update_by` bigint DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `url` (`url`) USING BTREE,
  KEY `name` (`name`) USING BTREE,
  KEY `create_at` (`create_at`) USING BTREE,
  KEY `update_at` (`update_at`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='导航提交表';
