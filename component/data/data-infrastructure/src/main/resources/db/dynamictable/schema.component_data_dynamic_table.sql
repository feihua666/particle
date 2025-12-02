-- 建表语句sql
DROP TABLE IF EXISTS component_data_dynamic_table;
CREATE TABLE `component_data_dynamic_table` (
  `id` bigint NOT NULL COMMENT '主键ID',
  `name` varchar(300) NOT NULL COMMENT '表名称',
  `comment` varchar(300) NOT NULL COMMENT '表注释',
  `is_created_table` tinyint(1) NOT NULL COMMENT '是否已建表，1=是，0=否',
  `dynamic_table_field_num` int NOT NULL COMMENT '字段数量',
  `dynamic_table_data_num` int NOT NULL COMMENT '数据数量',
  `remark` varchar(1300) DEFAULT NULL COMMENT '备注信息',
  `version` int NOT NULL COMMENT '乐观锁字段',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户id',
  `create_at` datetime NOT NULL COMMENT '创建时间的时间戳',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `update_at` datetime DEFAULT NULL COMMENT '修改时间的时间戳',
  `update_by` bigint DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `name` (`name`) USING BTREE,
  KEY `create_at` (`create_at`) USING BTREE,
  KEY `update_at` (`update_at`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='动态数据表格表';
