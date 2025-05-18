--
DROP TABLE IF EXISTS global_cache;
CREATE TABLE global_cache  (
  `id` varchar(64)  NOT NULL COMMENT '主键',
  `cache_name` varchar(500) NOT NULL COMMENT '缓存名称',
  `cache_key` varchar(500) NOT NULL COMMENT '缓存键值',
  `cache_value_json` text DEFAULT NULL COMMENT '缓存值，json形式',
  `cache_value_blob` blob DEFAULT NULL COMMENT '缓存值，blob形式',
  `cache_value_type` varchar(100) NOT NULL COMMENT '缓存值类型，如：java.lang.String',
  `cache_value_is_basic_type` tinyint(1) NOT NULL COMMENT '缓存值类型是否为基本数据类型，1=是，0=否',
  `cache_create_at` datetime NOT NULL COMMENT '缓存创建时间',
  `cache_ttl` int(11) NOT NULL COMMENT '缓存存活时间，单位为秒',
  `tenant_id` bigint NULL DEFAULT NULL COMMENT '租户id，预留',
  `create_at` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uni_cache_name__cache_key` (`cache_name`,`cache_key`) USING BTREE,
  KEY `cache_create_at` (`cache_create_at`) USING BTREE
) ENGINE = InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_bin  COMMENT = '缓存表';
