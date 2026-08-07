-- 建表语句sql
DROP TABLE IF EXISTS component_agi_model_provider;
CREATE TABLE `component_agi_model_provider` (
  `id` bigint NOT NULL COMMENT '主键ID',
  `code` varchar(50) NOT NULL COMMENT '提供商编码，如：（openai, dashscope, ollama, deepseek）',
  `name` varchar(100) NOT NULL COMMENT '提供商显示名称，如：（OpenAI, 阿里灵积, Ollama本地）',
  `type_dict_id` bigint NOT NULL COMMENT '提供商类型,如：（cloud-云服务, local-本地服务, self-hosted-自建）',
  `base_url` varchar(500) DEFAULT NULL COMMENT 'API 基础地址',
  `api_key_encrypted` text COMMENT '加密的 API Key',
  `is_disabled` tinyint(1) NOT NULL COMMENT '是否禁用',
  `extra_config_json` mediumtext COMMENT '扩展配置，如：（JSON格式，如超时时间、重试次数等）',
  `seq` int NOT NULL COMMENT '排序,默认按该字段升序排序',
  `remark` varchar(255) DEFAULT NULL COMMENT '描述',
  `version` int NOT NULL COMMENT '乐观锁字段',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户id',
  `create_at` datetime NOT NULL COMMENT '创建时间的时间戳',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `update_at` datetime DEFAULT NULL COMMENT '修改时间的时间戳',
  `update_by` bigint DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_code` (`code`) USING BTREE,
  KEY `name` (`name`) USING BTREE,
  KEY `type_dict_id` (`type_dict_id`) USING BTREE,
  KEY `is_disabled` (`is_disabled`) USING BTREE,
  KEY `seq` (`seq`) USING BTREE,
  KEY `version` (`version`) USING BTREE,
  KEY `create_at` (`create_at`) USING BTREE,
  KEY `update_at` (`update_at`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='AI模型提供商表';
