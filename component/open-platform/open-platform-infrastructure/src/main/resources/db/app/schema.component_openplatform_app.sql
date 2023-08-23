-- 建表语句sql
DROP TABLE IF EXISTS component_openplatform_app;
CREATE TABLE `component_openplatform_app` (
  `id` bigint NOT NULL COMMENT '表主键',
  `name` varchar(255) NOT NULL COMMENT '名称',
  `app_id` varchar(255) NOT NULL COMMENT 'appId，冗余ouath2的clientId',
  `owner_user_id` bigint DEFAULT NULL COMMENT '归属用户id，预留',
  `owner_customer_id` bigint DEFAULT NULL COMMENT '归属客户id，预留',
  `request_algorithm_secret_json` text NOT NULL COMMENT '请求算法与密钥等相关配置，最终对应 com.particle.global.openapi.data.OpenapiAlgorithmSecret',
  `response_algorithm_secret_json` text DEFAULT NULL COMMENT '响应算法与密钥等相关配置，最终对应 com.particle.global.openapi.data.OpenapiAlgorithmSecret',
  `scopes` varchar(2000) DEFAULT NULL COMMENT '访问范围配置，方便单独指定',
  `openplatform_openapi_fee_id` bigint DEFAULT NULL COMMENT '计费id，不配置不计费',
  `is_disabled` tinyint(1) NOT NULL COMMENT '是否禁用',
  `disabled_reason` varchar(255) DEFAULT NULL COMMENT '禁用原因',
  `is_check_signature` tinyint(1) NOT NULL COMMENT '是否检查签名,主要用于在oauth2 token时可以不检查',
  `version` int NOT NULL COMMENT '乐观锁字段',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户id',
  `create_at` datetime NOT NULL COMMENT '创建时间的时间戳',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `update_at` datetime DEFAULT NULL COMMENT '修改时间的时间戳',
  `update_by` bigint DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `app_id` (`app_id`),
  KEY `name` (`name`) USING BTREE,
  KEY `owner_user_id` (`owner_user_id`) USING BTREE,
  KEY `owner_customer_id` (`owner_customer_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='开放平台应用表';
