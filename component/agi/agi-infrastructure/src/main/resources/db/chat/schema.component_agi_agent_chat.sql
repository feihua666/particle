-- 建表语句sql
DROP TABLE IF EXISTS component_agi_agent_chat;
CREATE TABLE `component_agi_agent_chat` (
  `id` bigint NOT NULL COMMENT '主键ID',
  `agi_agent_id` bigint DEFAULT NULL COMMENT '智能体id，不强制依赖智能体',
  `chat_id` varchar(100) NOT NULL COMMENT '对话id，唯一',
  `user_id` bigint DEFAULT NULL COMMENT '用户id，是哪个用户的对话',
  `title` varchar(150) NOT NULL COMMENT '对话标题',
  `title_memo` varchar(500) DEFAULT NULL COMMENT '对话标题说明，一般说明来源如：是如何设置的标题',
  `remark` varchar(255) DEFAULT NULL COMMENT '描述',
  `version` int NOT NULL COMMENT '乐观锁字段',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户id',
  `create_at` datetime NOT NULL COMMENT '创建时间的时间戳',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `update_at` datetime DEFAULT NULL COMMENT '修改时间的时间戳',
  `update_by` bigint DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `chat_id` (`chat_id`) USING BTREE,
  KEY `agi_agent_id` (`agi_agent_id`) USING BTREE,
  KEY `user_id` (`user_id`) USING BTREE,
  KEY `version` (`version`) USING BTREE,
  KEY `create_at` (`create_at`) USING BTREE,
  KEY `update_at` (`update_at`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='智能体对话表';
