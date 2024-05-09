-- 建表语句sql
DROP TABLE IF EXISTS component_crm_customer_relation;
CREATE TABLE `component_crm_customer_relation` (
  `id` bigint NOT NULL COMMENT 'id主键',
  `crm_customer_id` bigint NOT NULL COMMENT '客户id',
  `another_crm_customer_id` bigint NOT NULL COMMENT '另一个客户id',
  `crm_customer_relation_define_id` bigint NOT NULL COMMENT '关系id，描述为客户是另一个客户的关系，如：张三是李四的父亲',
  `relation_detail` varchar(255) DEFAULT NULL COMMENT '关系详情描述',
  `version` int NOT NULL COMMENT '乐观锁字段',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户id',
  `create_at` datetime NOT NULL COMMENT '创建时间的时间戳',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `update_at` datetime DEFAULT NULL COMMENT '修改时间的时间戳',
  `update_by` bigint DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `crm_customer_id` (`crm_customer_id`),
  KEY `another_crm_customer_id` (`another_crm_customer_id`),
  KEY `crm_customer_relation_define_id` (`crm_customer_relation_define_id`),
  KEY `create_at` (`create_at`) USING BTREE,
  KEY `update_at` (`update_at`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='客户与客户关系表';
