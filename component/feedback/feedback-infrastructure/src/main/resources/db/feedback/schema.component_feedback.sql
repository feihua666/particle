-- 建表语句sql
DROP TABLE IF EXISTS component_feedback;
CREATE TABLE `component_feedback` (
  `id` bigint NOT NULL COMMENT '主键ID',
  `feedback_content` text NOT NULL COMMENT '问题建议内容',
  `feedback_at` datetime NOT NULL COMMENT '问题建议时间',
  `feedback_user_id` bigint DEFAULT NULL COMMENT '问题建议用户id，如果存在登录用户，记录提建议的用户id',
  `contact_username` varchar(255) DEFAULT NULL COMMENT '联系姓名',
  `contact_telephone` varchar(100) DEFAULT NULL COMMENT '联系电话',
  `contact_email` varchar(255) DEFAULT NULL COMMENT '联系邮箱',
  `is_contact_telephone_mobile` tinyint(1) NOT NULL COMMENT '是否联系电话为手机号',
  `is_handle` tinyint(1) NOT NULL COMMENT '是否已处理',
  `handle_result` varchar(255) DEFAULT NULL COMMENT '处理结果，描述文本',
  `handle_at` datetime default NULL COMMENT '处理时间',
  `first_feedback_id` bigint DEFAULT NULL COMMENT '第一条意见反馈id，主要串联起一个话题',
  `remark` varchar(255) DEFAULT NULL COMMENT '描述',
  `version` int NOT NULL COMMENT '乐观锁字段',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户id',
  `create_at` datetime NOT NULL COMMENT '创建时间的时间戳',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `update_at` datetime DEFAULT NULL COMMENT '修改时间的时间戳',
  `update_by` bigint DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `contact_username` (`contact_username`) USING BTREE,
  KEY `contact_telephone` (`contact_telephone`) USING BTREE,
  KEY `create_at` (`create_at`) USING BTREE,
  KEY `update_at` (`update_at`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='意见反馈表';
