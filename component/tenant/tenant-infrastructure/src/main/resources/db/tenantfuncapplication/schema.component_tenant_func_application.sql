-- 建表语句sql
DROP TABLE IF EXISTS component_tenant_func_application;
CREATE TABLE `component_tenant_func_application` (
  `id` bigint NOT NULL COMMENT '主键',
  `func_application_id` bigint NOT NULL COMMENT '功能应用id',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `application_theme` varchar(255) DEFAULT NULL COMMENT '应用主题，一个代表客户端主题样式的字符串',
  `application_default_route` varchar(255) DEFAULT NULL COMMENT '应用默认的页面路由',
  `application_logo_url` varchar(350) DEFAULT NULL COMMENT '应用logo地址',
  `application_icon_url` varchar(350) DEFAULT NULL COMMENT '应用图标地址',
  `config_json` varchar(2000) DEFAULT NULL COMMENT '额外配置json',
  `version` int NOT NULL COMMENT '乐观锁字段',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户id',
  `create_at` datetime NOT NULL COMMENT '创建时间的时间戳',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `update_at` datetime DEFAULT NULL COMMENT '修改时间的时间戳',
  `update_by` bigint DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uni_ func_application_id_tenant_id` (`func_application_id`,`tenant_id`),
  KEY `idx_func_id` (`func_application_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='租户功能应用表';
