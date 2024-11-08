-- 建表语句sql
DROP TABLE IF EXISTS component_navigation_site;
CREATE TABLE `component_navigation_site` (
  `id` bigint NOT NULL COMMENT '主键ID',
  `name` varchar(100) NOT NULL COMMENT '网站名称',
  `title` varchar(300) NOT NULL COMMENT '网站标题',
  `logo_url` varchar(400) DEFAULT NULL COMMENT '网站logo图标地址',
  `url` varchar(400) NOT NULL COMMENT '网站地址',
  `screenshot_url` varchar(400) DEFAULT NULL COMMENT '网站截屏地址',
  `screenshot_thumbnail_url` varchar(400) DEFAULT NULL COMMENT '网站截屏缩略图地址',
  `content` varchar(400) DEFAULT NULL COMMENT '简短描述',
  `content_detail` text COMMENT '详细描述',
  `collection_at` datetime NOT NULL COMMENT '收录时间',
  `is_published` tinyint(1) NOT NULL COMMENT '是否已发布，已发布不能修改和删除',
  `unpublished_reason` varchar(255) DEFAULT NULL COMMENT '下架原因，未发布原因',
  `remark` varchar(255) DEFAULT NULL COMMENT '描述',
  `version` int NOT NULL COMMENT '乐观锁字段',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户id',
  `create_at` datetime DEFAULT NULL COMMENT '创建时间的时间戳',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `update_at` datetime DEFAULT NULL COMMENT '修改时间的时间戳',
  `update_by` bigint DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `name` (`name`) USING BTREE,
  UNIQUE KEY `url` (`url`) USING BTREE,
  KEY `create_at` (`create_at`) USING BTREE,
  KEY `update_at` (`update_at`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='导航网站表';
