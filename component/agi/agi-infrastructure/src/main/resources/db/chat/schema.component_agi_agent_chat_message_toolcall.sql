-- 建表语句sql
DROP TABLE IF EXISTS component_agi_agent_chat_message_toolcall;
CREATE TABLE `component_agi_agent_chat_message_toolcall` (
  `id` bigint NOT NULL COMMENT '主键ID',
  `agi_agent_chat_message_id` bigint NOT NULL COMMENT '智能体对话消息id',
  `unique_id` varchar(100) NOT NULL COMMENT '唯一id',
  `name` varchar(150) NOT NULL COMMENT '名称',
  `type` varchar(150) NOT NULL COMMENT '类型',
  `arguments` varchar(2000) DEFAULT NULL COMMENT '参数',
  `remark` varchar(255) DEFAULT NULL COMMENT '描述',
  `version` int NOT NULL COMMENT '乐观锁字段',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户id',
  `create_at` datetime NOT NULL COMMENT '创建时间的时间戳',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `update_at` datetime DEFAULT NULL COMMENT '修改时间的时间戳',
  `update_by` bigint DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `unique_id` (`unique_id`) USING BTREE,
  KEY `version` (`version`) USING BTREE,
  KEY `create_at` (`create_at`) USING BTREE,
  KEY `update_at` (`update_at`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='智能体对话消息工具调用表';
