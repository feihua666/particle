-- 建表语句sql
DROP TABLE IF EXISTS component_message_user_state;
CREATE TABLE `component_message_user_state` (
  `id` bigint NOT NULL COMMENT '表主键',
  `message_id` bigint NOT NULL COMMENT '消息表主键',
  `user_id` bigint NOT NULL COMMENT '用户id',
  `is_read` tinyint(1) NOT NULL COMMENT '是否已读，1=已读，0=未读',
  `read_at` datetime DEFAULT NULL COMMENT '读取时间',
  `version` int NOT NULL COMMENT '乐观锁字段',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户id',
  `create_at` datetime NOT NULL COMMENT '创建时间的时间戳',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `update_at` datetime DEFAULT NULL COMMENT '修改时间的时间戳',
  `update_by` bigint DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `message_id` (`message_id`),
  KEY `user_id` (`user_id`),
  KEY `user_id_is_read` (`user_id`,`is_read`),
  KEY `create_at` (`create_at`) USING BTREE,
  KEY `update_at` (`update_at`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='用户消息读取状态表';
