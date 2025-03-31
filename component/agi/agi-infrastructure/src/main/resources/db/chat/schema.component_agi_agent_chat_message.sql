-- 建表语句sql
DROP TABLE IF EXISTS component_agi_agent_chat_message;
CREATE TABLE `component_agi_agent_chat_message` (
  `id` bigint NOT NULL COMMENT '主键ID',
  `agi_agent_chat_id` bigint NOT NULL COMMENT '智能体对话id',
  `agi_agent_id` bigint DEFAULT NULL COMMENT '智能体id，不强制依赖智能体',
  `chat_id` varchar(100) NOT NULL COMMENT '对话id，唯一',
  `user_id` bigint DEFAULT NULL COMMENT '用户id，是哪个用户的对话',
  `message_type` varchar(150) NOT NULL COMMENT '消息类型，用来标识是谁的消息如：user、assistant、system',
  `content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin COMMENT '消息内容',
  `metadata_json` varchar(2000) DEFAULT NULL COMMENT '元数据信息json',
  `remark` varchar(255) DEFAULT NULL COMMENT '描述',
  `version` int NOT NULL COMMENT '乐观锁字段',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户id',
  `create_at` datetime NOT NULL COMMENT '创建时间的时间戳',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `update_at` datetime DEFAULT NULL COMMENT '修改时间的时间戳',
  `update_by` bigint DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `chat_id` (`chat_id`) USING BTREE,
  KEY `agi_agent_id` (`agi_agent_id`) USING BTREE,
  KEY `user_id` (`user_id`) USING BTREE,
  KEY `version` (`version`) USING BTREE,
  KEY `create_at` (`create_at`) USING BTREE,
  KEY `update_at` (`update_at`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='智能体对话消息表';
