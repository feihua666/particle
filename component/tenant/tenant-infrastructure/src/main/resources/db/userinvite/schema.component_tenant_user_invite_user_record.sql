-- 建表语句sql
DROP TABLE IF EXISTS component_tenant_user_invite_user_record;
CREATE TABLE `component_tenant_user_invite_user_record` (
  `id` bigint NOT NULL COMMENT '主键',
  `tenant_user_invite_id` int NOT NULL COMMENT '租户用户邀请id',
  `invited_user_id` bigint NOT NULL COMMENT '被邀请人用户id',
  `join_at` datetime DEFAULT NULL COMMENT '用户加入时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '描述',
  `version` int NOT NULL COMMENT '乐观锁字段',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户id',
  `create_at` datetime NOT NULL COMMENT '创建时间的时间戳',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `update_at` datetime DEFAULT NULL COMMENT '修改时间的时间戳',
  `update_by` bigint DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `tenant_user_invite_id` (`tenant_user_invite_id`,`invited_user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='租户用户邀请记录表';
