-- 建表语句sql
DROP TABLE IF EXISTS component_crm_customer_relation_define;
CREATE TABLE `component_crm_customer_relation_define` (
  `id` bigint NOT NULL COMMENT 'id主键',
  `code` varchar(255) DEFAULT NULL COMMENT '关系定义编码',
  `name` varchar(255) NOT NULL COMMENT '关系定义名称',
  `is_bidirectional` tinyint(1) NOT NULL COMMENT '是否为双向关系,不是双向就是单身',
  `bidirectional_id` bigint DEFAULT NULL COMMENT '双向关系id，如果为单向关系，则必填，存储对应的双向关系id',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `version` int NOT NULL COMMENT '乐观锁字段',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户id',
  `create_at` datetime NOT NULL COMMENT '创建时间的时间戳',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `update_at` datetime DEFAULT NULL COMMENT '修改时间的时间戳',
  `update_by` bigint DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `name` (`name`),
  UNIQUE KEY `code` (`code`),
  KEY `create_at` (`create_at`) USING BTREE,
  KEY `update_at` (`update_at`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='客户关系定义表';
