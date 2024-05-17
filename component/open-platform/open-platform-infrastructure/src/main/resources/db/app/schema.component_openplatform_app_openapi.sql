-- 建表语句sql
DROP TABLE IF EXISTS component_openplatform_app_openapi;
CREATE TABLE `component_openplatform_app_openapi` (
  `id` bigint NOT NULL COMMENT '表主键',
  `openplatform_app_id` bigint NOT NULL COMMENT '开放平台应用id',
  `openplatform_openapi_id` bigint NOT NULL COMMENT '开放接口id，这里只存储接口，不存储分组',
  `openplatform_openapi_fee_id` bigint DEFAULT NULL COMMENT '计费id，不配置不计费',
  `openplatform_provider_id` bigint DEFAULT NULL COMMENT '指定供应商，如果不指定将按默认编码调用',
  `remark` varchar(255) DEFAULT NULL COMMENT '描述',
  `version` int NOT NULL COMMENT '乐观锁字段',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户id',
  `create_at` datetime NOT NULL COMMENT '创建时间的时间戳',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `update_at` datetime DEFAULT NULL COMMENT '修改时间的时间戳',
  `update_by` bigint DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uni_openplatform_app_id__openplatform_openapi_id` (`openplatform_app_id`,`openplatform_openapi_id`) USING BTREE,
  KEY `openplatform_app_id` (`openplatform_app_id`) USING BTREE,
  KEY `openplatform_openapi_id` (`openplatform_openapi_id`) USING BTREE,
  KEY `create_at` (`create_at`) USING BTREE,
  KEY `update_at` (`update_at`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='开放平台应用与开放接口配置表';
