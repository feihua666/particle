-- 建表语句sql
DROP TABLE IF EXISTS component_system_config;
CREATE TABLE `component_system_config` (
  `id` bigint NOT NULL COMMENT '主键ID',
  `code` varchar(200) NOT NULL COMMENT '参数配置编码，唯一',
  `name` varchar(50) NOT NULL COMMENT '参数配置名称',
  `value` varchar(2000) NOT NULL COMMENT '参数配置值',
  `is_built_in` tinyint(1) NOT NULL COMMENT '是否内置，一般内置的不可删除，否则可能引起错误',
  `is_disabled` tinyint(1) NOT NULL COMMENT '是否禁用',
  `disabled_reason` varchar(255) DEFAULT NULL COMMENT '禁用原因',
  `tag` varchar(255) DEFAULT NULL COMMENT '标签，一个标签，可用于标识一类数据',
  `remark` varchar(255) DEFAULT NULL COMMENT '描述',
  `version` int NOT NULL COMMENT '乐观锁字段',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户id',
  `create_at` datetime NOT NULL COMMENT '创建时间的时间戳',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `update_at` datetime DEFAULT NULL COMMENT '修改时间的时间戳',
  `update_by` bigint DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `code` (`code`) USING BTREE,
  KEY `name` (`name`) USING BTREE,
  KEY `is_disabled` (`is_disabled`) USING BTREE,
  KEY `tag` (`tag`) USING BTREE,
  KEY `create_at` (`create_at`) USING BTREE,
  KEY `update_at` (`update_at`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='系统参数配置表';
