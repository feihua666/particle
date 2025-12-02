-- 建表语句sql
DROP TABLE IF EXISTS component_data_dynamic_table_field;
CREATE TABLE `component_data_dynamic_table_field` (
  `id` bigint NOT NULL COMMENT '主键ID',
  `dynamic_table_id` bigint DEFAULT NULL COMMENT '动态数据表格id',
  `name` varchar(300) NOT NULL COMMENT '字段名称',
  `comment` varchar(300) NOT NULL COMMENT '字段注释',
  `type` varchar(300) NOT NULL COMMENT '字段类型,如：varchar(100)',
  `is_required` tinyint(1) NOT NULL COMMENT '是否必填,1=是，0=否',
  `default_value` varchar(300) DEFAULT NULL COMMENT '字段默认值',
  `version` int NOT NULL COMMENT '乐观锁字段',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户id',
  `create_at` datetime NOT NULL COMMENT '创建时间的时间戳',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `update_at` datetime DEFAULT NULL COMMENT '修改时间的时间戳',
  `update_by` bigint DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `dynamic_table_id__name` (`dynamic_table_id`,`name`) USING BTREE,
  UNIQUE KEY `dynamic_table_id__comment` (`dynamic_table_id`,`comment`) USING BTREE,
  KEY `dynamic_table_id` (`dynamic_table_id`) USING BTREE,
  KEY `create_at` (`create_at`) USING BTREE,
  KEY `update_at` (`update_at`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='动态数据表格字段表';
