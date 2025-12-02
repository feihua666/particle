-- 建表语句sql
DROP TABLE IF EXISTS component_data_dynamic_data_indicator;
CREATE TABLE `component_data_dynamic_data_indicator` (
  `id` bigint NOT NULL COMMENT '主键ID',
  `dynamic_data_category_id` bigint NOT NULL COMMENT '动态数据分类id',
  `dynamic_data_indicator_category_id` bigint NOT NULL COMMENT '动态数据指标分类id',
  `name` varchar(300) NOT NULL COMMENT '指标名称',
  `is_disabled` tinyint(1) NOT NULL COMMENT '是否禁用，1=是，0=否',
  `remark` varchar(1300) NOT NULL COMMENT '备注信息',
  `version` int NOT NULL COMMENT '乐观锁字段',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户id',
  `create_at` datetime NOT NULL COMMENT '创建时间的时间戳',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `update_at` datetime DEFAULT NULL COMMENT '修改时间的时间戳',
  `update_by` bigint DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `dynamic_data_indicator_category_id__name` (`dynamic_data_indicator_category_id`,`name`) USING BTREE,
  KEY `dynamic_data_category_id` (`dynamic_data_category_id`) USING BTREE,
  KEY `create_at` (`create_at`) USING BTREE,
  KEY `update_at` (`update_at`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='动态数据指标表';
