-- 建表语句sql
DROP TABLE IF EXISTS component_data_object;
CREATE TABLE `component_data_object` (
  `id` bigint NOT NULL COMMENT '主键ID',
  `code` varchar(50) NOT NULL COMMENT '数据对象编码',
  `name` varchar(100) NOT NULL COMMENT '数据对象名称',
  `custom_data_url` varchar(300) DEFAULT NULL COMMENT '数据范围自定义时用来绑定自定义数据的url',
  `is_custom_data_lazy` tinyint(1) NOT NULL COMMENT '自定义数据是否懒加载,否则为一次性加载全部数据',
  `custom_data_type_dict_id` bigint DEFAULT NULL COMMENT '自定义数据交互方式，字典id，如：表格、树形',
  `custom_data_config_json` varchar(2000) DEFAULT NULL COMMENT '数据交互方式内容',
  `is_disabled` tinyint(1) NOT NULL COMMENT '是否禁用',
  `disabled_reason` varchar(255) DEFAULT NULL COMMENT '禁用原因',
  `remark` varchar(255) DEFAULT NULL COMMENT '描述',
  `version` int NOT NULL COMMENT '乐观锁字段',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户id',
  `create_at` datetime NOT NULL COMMENT '创建时间的时间戳',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `update_at` datetime DEFAULT NULL COMMENT '修改时间的时间戳',
  `update_by` bigint DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `code` (`code`),
  KEY `version` (`version`) USING BTREE,
  KEY `name` (`name`) USING BTREE,
  KEY `create_at` (`create_at`) USING BTREE,
  KEY `update_at` (`update_at`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='数据对象表';
