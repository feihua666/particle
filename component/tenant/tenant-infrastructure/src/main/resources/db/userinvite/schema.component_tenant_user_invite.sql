-- 建表语句sql
DROP TABLE IF EXISTS component_tenant_user_invite;
CREATE TABLE `component_tenant_user_invite` (
  `id` bigint NOT NULL COMMENT '主键',
  `invite_code` varchar(200) NOT NULL COMMENT '邀请码',
  `max_invite_count` int NOT NULL COMMENT '最大邀请人数',
  `invited_count` int DEFAULT NULL COMMENT '已邀请人数',
  `is_expired` tinyint(1) NOT NULL COMMENT '是否过期，过期后该密码不能登录',
  `expired_reason` varchar(255) DEFAULT NULL COMMENT '过期原因',
  `expire_at` datetime DEFAULT NULL COMMENT '到期时间，为空永不到期',
  `inviter_user_id` bigint NOT NULL COMMENT '邀请人用户id',
  `version` int NOT NULL COMMENT '乐观锁字段',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户id',
  `create_at` datetime NOT NULL COMMENT '创建时间的时间戳',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `update_at` datetime DEFAULT NULL COMMENT '修改时间的时间戳',
  `update_by` bigint DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_func_id` (`invite_code`) USING BTREE,
  KEY `create_at` (`create_at`) USING BTREE,
  KEY `update_at` (`update_at`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='租户用户邀请表';
