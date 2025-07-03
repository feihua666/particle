-- 建表语句sql
DROP TABLE IF EXISTS component_cms_content_multimedia;
CREATE TABLE `component_cms_content_multimedia` (
  `id` bigint NOT NULL COMMENT '主键ID',
  `cms_site_id` bigint NOT NULL COMMENT '站点id',
  `cms_content_id` bigint DEFAULT NULL COMMENT '内容id',
  `content` longtext NOT NULL COMMENT '文本内容',
  `image_url` varchar(300) DEFAULT NULL COMMENT '图片地址，主要用于列表展示',
  `image_description` varchar(300) DEFAULT NULL COMMENT '图片描述，可能主要用于详情展示，如：图片来源于网络',
  `image_url1` varchar(300) DEFAULT NULL COMMENT '图片地址1，主要用于列表展示',
  `image_description1` varchar(300) DEFAULT NULL COMMENT '图片描述1，可能主要用于详情展示，如：图片来源于网络',
  `image_url2` varchar(300) DEFAULT NULL COMMENT '图片地址2，主要用于列表展示',
  `image_description2` varchar(300) DEFAULT NULL COMMENT '图片描述2，可能主要用于详情展示，如：图片来源于网络',
  `file_name` varchar(100) DEFAULT NULL COMMENT '文件名称',
  `file_url` varchar(300) DEFAULT NULL COMMENT '文件地址',
  `file_size` int DEFAULT NULL COMMENT '文件大小，单位字节',
  `file_size_str` varchar(100) DEFAULT NULL COMMENT '文件大小字符串，有高可读性',
  `media_type` varchar(100) DEFAULT NULL COMMENT '媒体类型',
  `seq` int NOT NULL COMMENT '排序,默认按该字段升序排序',
  `version` int NOT NULL COMMENT '乐观锁字段',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户id',
  `create_at` datetime NOT NULL COMMENT '创建时间的时间戳',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `update_at` datetime DEFAULT NULL COMMENT '修改时间的时间戳',
  `update_by` bigint DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `cms_site_id` (`cms_site_id`) USING BTREE,
  KEY `cms_content_id` (`cms_content_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='内容多媒体表';
