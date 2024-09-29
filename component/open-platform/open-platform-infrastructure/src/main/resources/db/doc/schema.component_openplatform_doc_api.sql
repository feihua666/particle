-- 建表语句sql
DROP TABLE IF EXISTS component_openplatform_doc_api;
CREATE TABLE `component_openplatform_doc_api` (
  `id` bigint NOT NULL COMMENT 'id主键',
  `code` varchar(255) NOT NULL COMMENT '编码，唯一',
  `name` varchar(100) NOT NULL COMMENT '名称',
  `name_simple` varchar(100) DEFAULT NULL COMMENT '简称',
  `logo_url` varchar(300) DEFAULT NULL COMMENT '图标地址',
  `price_per_time` float(10,2) DEFAULT NULL COMMENT '每次价格，单位元',
  `price_per_time_desc` varchar(255) DEFAULT NULL COMMENT '价格文本，price_per_time不支持时可以作为备用',
  `openplatform_openapi_id` bigint NOT NULL COMMENT '开放接口id，这里只存储接口，不存储分组',
  `description` varchar(2000) DEFAULT NULL COMMENT '描述',
  `seq` int NOT NULL COMMENT '排序,默认按该字段升序排序',
  `version` int NOT NULL COMMENT '乐观锁字段',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户id',
  `create_at` datetime NOT NULL COMMENT '创建时间的时间戳',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `update_at` datetime DEFAULT NULL COMMENT '修改时间的时间戳',
  `update_by` bigint DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `code` (`code`),
  UNIQUE KEY `openplatform_openapi_id` (`openplatform_openapi_id`),
  KEY `seq` (`seq`) USING BTREE,
  KEY `name` (`name`) USING BTREE,
  KEY `name_simple` (`name_simple`) USING BTREE,
  KEY `create_at` (`create_at`) USING BTREE,
  KEY `update_at` (`update_at`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='开放接口文档接口表';
