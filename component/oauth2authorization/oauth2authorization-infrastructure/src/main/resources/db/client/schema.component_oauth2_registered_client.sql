-- 建表语句sql
DROP TABLE IF EXISTS component_oauth2_registered_client;
CREATE TABLE `component_oauth2_registered_client` (
  `id` bigint NOT NULL COMMENT '主键',
  `client_id` varchar(100) NOT NULL COMMENT '客户端ID，唯一标识客户端',
  `client_id_issued_at` datetime NOT NULL COMMENT '客户端ID的发布时间',
  `client_secret` varchar(200) DEFAULT NULL COMMENT '客户端秘钥，用于进行客户端认证',
  `client_secret_expires_at` datetime DEFAULT NULL COMMENT '客户端秘钥的过期时间，为空表示不过期',
  `client_name` varchar(200) NOT NULL COMMENT '客户端名称，用于展示',
  `client_authentication_methods` varchar(1000) NOT NULL COMMENT '客户端的身份验证方法，用于验证客户端的身份',
  `authorization_grant_types` varchar(1000) NOT NULL COMMENT '客户端支持的授权类型，用于指定客户端可以使用的授权方式',
  `redirect_uris` varchar(1000) DEFAULT NULL COMMENT '客户端重定向URI，指定通过OAuth 2.0授权流程后重定向到的URI',
  `scopes` varchar(1000) NOT NULL COMMENT '客户端的访问范围，表示客户端可以请求的资源范围',
  `client_settings` varchar(2000) NOT NULL COMMENT '客户端的设置，用于存储其他客户端特定的配置信息',
  `token_settings` varchar(2000) NOT NULL COMMENT '令牌的设置，用于存储与令牌相关的配置信息',
  `version` int NOT NULL COMMENT '乐观锁字段',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户id',
  `create_at` datetime NOT NULL COMMENT '创建时间的时间戳',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `update_at` datetime DEFAULT NULL COMMENT '修改时间的时间戳',
  `update_by` bigint DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `client_id` (`client_id`) USING BTREE,
  KEY `client_name` (`client_name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC COMMENT='开放平台客户端表';
