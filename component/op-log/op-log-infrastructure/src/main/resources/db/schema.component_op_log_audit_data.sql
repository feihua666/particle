-- 建表语句sql
DROP TABLE IF EXISTS component_op_log_audit_data;
CREATE TABLE `component_op_log_audit_data` (
  `id` bigint NOT NULL COMMENT '主键ID',
  `name` varchar(100) NOT NULL COMMENT '数据字段名称,模糊查询',
  `property_name` varchar(100) NOT NULL COMMENT '数据字段英文名称',
  `old_value` longtext COMMENT '旧值',
  `new_value` longtext COMMENT '新值',
  `change_type_dict_id` bigint NOT NULL COMMENT '值改变类型字典id',
  `change_type` varchar(255) NOT NULL COMMENT '值改变类型，添加、删除、修改',
  `type_dict_id` bigint DEFAULT NULL COMMENT '类型对应的字典id',
  `type` varchar(255) NOT NULL COMMENT '类型，一个操作类型英文字符串标识，如：create、delete',
  `user_id` bigint DEFAULT NULL COMMENT '操作用户id',
  `data_id` bigint DEFAULT NULL COMMENT '数据id',
  `data_table` varchar(255) DEFAULT NULL COMMENT '数据表名',
  `data_entity` varchar(255) DEFAULT NULL COMMENT '数据载体，一般为实体DO类全路径',
  `op_log_id` bigint DEFAULT NULL COMMENT '操作日志id',
  `remark` varchar(255) DEFAULT NULL COMMENT '描述',
  `version` int NOT NULL COMMENT '乐观锁字段',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户id',
  `create_at` datetime NOT NULL COMMENT '创建时间的时间戳',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `update_at` datetime DEFAULT NULL COMMENT '修改时间的时间戳',
  `update_by` bigint DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_name` (`name`) USING BTREE,
  KEY `property_name` (`property_name`),
  KEY `type_dict_id` (`type_dict_id`),
  KEY `data_id` (`data_id`),
  KEY `op_log_id` (`op_log_id`),
  KEY `create_at` (`create_at`) USING BTREE,
  KEY `update_at` (`update_at`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='操作日志审计数据表';
