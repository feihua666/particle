-- 建表语句sql
DROP TABLE IF EXISTS component_data_scope;
CREATE TABLE `component_data_scope` (
  `id` bigint NOT NULL COMMENT '主键ID',
  `code` varchar(50) NOT NULL COMMENT '数据范围编码',
  `name` varchar(50) NOT NULL COMMENT '数据范围名称',
  `data_object_id` bigint NOT NULL COMMENT '数据对象id',
  `constraint_content_type_dict_id` bigint DEFAULT NULL COMMENT '约束条件内容类型，字典id',
  `constraint_content` varchar(2000) DEFAULT NULL COMMENT '约束条件内容，暂时想到的用sql模板',
  `is_custom` tinyint(1) NOT NULL COMMENT '是否自定义，如果自定义=1，否则为0',
  `is_for_delete` tinyint(1) NOT NULL COMMENT '是否用于删除',
  `is_for_update` tinyint(1) NOT NULL COMMENT '是否用于修改',
  `is_for_query` tinyint(1) NOT NULL COMMENT '是否用于查询',
  `is_for_other` tinyint(1) NOT NULL COMMENT '是否用于其它，如：执行等除增、删、改、查的其它数据影响',
  `remark` varchar(255) DEFAULT NULL COMMENT '描述',
  `version` int NOT NULL COMMENT '乐观锁字段',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户id',
  `create_at` datetime NOT NULL COMMENT '创建时间的时间戳',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `update_at` datetime DEFAULT NULL COMMENT '修改时间的时间戳',
  `update_by` bigint DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `code` (`code`),
  KEY `name` (`name`) USING BTREE,
  KEY `version` (`version`) USING BTREE,
  KEY `create_at` (`create_at`) USING BTREE,
  KEY `update_at` (`update_at`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='数据范围表';
