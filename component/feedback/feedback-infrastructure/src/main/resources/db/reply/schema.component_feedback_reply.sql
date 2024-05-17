-- 建表语句sql
DROP TABLE IF EXISTS component_feedback_reply;
CREATE TABLE `component_feedback_reply` (
  `id` bigint NOT NULL COMMENT '主键ID',
  `feedback_id` bigint NOT NULL COMMENT '意见反馈id',
  `reply_content` text NOT NULL COMMENT '回复内容',
  `reply_at` datetime NOT NULL COMMENT '回复时间',
  `reply_user_id` bigint NOT NULL COMMENT '回复用户id',
  `is_feedback_user_read` tinyint(1) NOT NULL COMMENT '是否提问题建议用户已读',
  `feedback_user_read_at` datetime default NULL COMMENT '用户已读时间',
  `feedback_user_rate_dict_id` bigint DEFAULT NULL COMMENT '提问题建议用户评价，字典id',
  `feedback_user_rate_memo` varchar(300) DEFAULT NULL COMMENT '提问题建议用户评价内容',
  `feedback_user_rate_at` datetime default NULL COMMENT '用户评价时间',
  `first_feedback_id` bigint DEFAULT NULL COMMENT '第一条意见反馈id，主要串联起一个话题',
  `remark` varchar(255) DEFAULT NULL COMMENT '描述',
  `version` int NOT NULL COMMENT '乐观锁字段',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户id',
  `create_at` datetime NOT NULL COMMENT '创建时间的时间戳',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `update_at` datetime DEFAULT NULL COMMENT '修改时间的时间戳',
  `update_by` bigint DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `feedback_id` (`feedback_id`) USING BTREE,
  KEY `reply_user_id` (`reply_user_id`) USING BTREE,
  KEY `is_feedback_user_read` (`is_feedback_user_read`) USING BTREE,
  KEY `feedback_user_rate_dict_id` (`feedback_user_rate_dict_id`) USING BTREE,
  KEY `create_at` (`create_at`) USING BTREE,
  KEY `update_at` (`update_at`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='意见反馈回复表';
