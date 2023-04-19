-- 建表语句sql
DROP TABLE IF EXISTS component_tenant_create_apply;
CREATE TABLE `component_tenant_create_apply` (
  `id` bigint NOT NULL COMMENT '租户ID',
  `name` varchar(100) NOT NULL COMMENT '租户名称,模糊查询',
  `contact_user_name` varchar(200) DEFAULT NULL COMMENT '联系人姓名',
  `contact_user_email` varchar(300) DEFAULT NULL COMMENT '联系人邮箱',
  `contact_user_phone` varchar(255) DEFAULT NULL COMMENT '联系人电话',
  `tenant_type_dict_id` bigint NOT NULL COMMENT '租户类型字典id',
  `apply_user_id` bigint NOT NULL COMMENT '申请用户',
  `audit_status_dict_id` bigint NOT NULL COMMENT '审核状态，字典id',
  `audit_status_comment` varchar(255) DEFAULT NULL COMMENT '审核意见',
  `audit_user_id` bigint DEFAULT NULL COMMENT '审核用户id',
  `applied_tenant_id` bigint DEFAULT NULL COMMENT '审核通过后创建的租户id',
  `remark` varchar(255) DEFAULT NULL COMMENT '描述',
  `version` int NOT NULL COMMENT '乐观锁字段',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户id',
  `create_at` datetime NOT NULL COMMENT '创建时间的时间戳',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `update_at` datetime DEFAULT NULL COMMENT '修改时间的时间戳',
  `update_by` bigint DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `name` (`name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='租户创建申请表';
