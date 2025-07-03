-- 建表语句sql
DROP TABLE IF EXISTS component_cms_channel_view_record;
CREATE TABLE `component_cms_channel_view_record` (
  `id` bigint NOT NULL COMMENT '主键ID',
  `cms_site_id` bigint NOT NULL COMMENT '站点id',
  `cms_channel_id` bigint NOT NULL COMMENT '栏目id',
  `device_id` varchar(256) DEFAULT NULL COMMENT '访问终端设备id',
  `ip` varchar(20) DEFAULT NULL COMMENT '访问终端设备ip',
  `view_at` datetime NOT NULL COMMENT '访问时间',
  `version` int NOT NULL COMMENT '乐观锁字段',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户id',
  `create_at` datetime NOT NULL COMMENT '创建时间的时间戳',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `update_at` datetime DEFAULT NULL COMMENT '修改时间的时间戳',
  `update_by` bigint DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `cms_site_id` (`cms_site_id`) USING BTREE,
  KEY `cms_channel_id` (`cms_channel_id`) USING BTREE,
  KEY `device_id` (`device_id`) USING BTREE,
  KEY `ip` (`ip`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='栏目访问记录表';
