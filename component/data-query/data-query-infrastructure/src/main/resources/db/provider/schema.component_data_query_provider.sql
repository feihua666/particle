-- 建表语句sql
DROP TABLE IF EXISTS component_data_query_provider;
CREATE TABLE `component_data_query_provider` (
  `id` bigint NOT NULL COMMENT '表主键',
  `name` varchar(50) NOT NULL COMMENT '供应商名称',
  `is_disabled` tinyint(1) NOT NULL COMMENT '是否禁用',
  `disabled_reason` varchar(255) DEFAULT NULL COMMENT '禁用原因',
  `contact_user_name` varchar(200) DEFAULT NULL COMMENT '联系人姓名',
  `contact_user_email` varchar(300) DEFAULT NULL COMMENT '联系人邮箱',
  `contact_user_phone` varchar(255) DEFAULT NULL COMMENT '联系人电话',
  `remark` varchar(255) DEFAULT NULL COMMENT '描述',
  `version` int NOT NULL COMMENT '乐观锁字段',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户id',
  `create_at` datetime NOT NULL COMMENT '创建时间的时间戳',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `update_at` datetime DEFAULT NULL COMMENT '修改时间的时间戳',
  `update_by` bigint DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `name` (`name`) USING BTREE,
  KEY `data_object_id` (`is_disabled`) USING BTREE,
  KEY `version` (`version`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='数据查询供应商表';