-- 建表语句sql
DROP TABLE IF EXISTS component_openplatform_openapi_record_app_openapi_day_summary;
CREATE TABLE `component_openplatform_openapi_record_app_openapi_day_summary` (
  `id` bigint NOT NULL COMMENT '表主键',
  `openplatform_app_id` bigint DEFAULT NULL COMMENT '开放平台应用id',
  `app_id` varchar(255) DEFAULT NULL COMMENT '应用id',
  `openplatform_openapi_id` bigint DEFAULT NULL COMMENT '开放平台接口id',
  `day_at` date NOT NULL COMMENT '日期',
  `customer_id` bigint DEFAULT NULL COMMENT '客户id',
  `total_call` int NOT NULL COMMENT '调用总量',
  `total_fee_call` int NOT NULL COMMENT '调用计费总量',
  `average_unit_price_amount` int NOT NULL COMMENT '平均单价金额，单位分',
  `total_fee_amount` int NOT NULL COMMENT '总消费金额，单位分',
  `remark` varchar(2000) DEFAULT NULL COMMENT '描述',
  `version` int NOT NULL COMMENT '乐观锁字段',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户id',
  `create_at` datetime NOT NULL COMMENT '创建时间的时间戳',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `update_at` datetime DEFAULT NULL COMMENT '修改时间的时间戳',
  `update_by` bigint DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uni_openplatform_app_id_app_id_openplatform_openapi_id_day_at` (`openplatform_app_id`,`app_id`,`openplatform_openapi_id`,`day_at`) USING BTREE,
  KEY `openplatform_app_id` (`openplatform_app_id`) USING BTREE,
  KEY `app_id` (`app_id`) USING BTREE,
  KEY `openplatform_openapi_id` (`openplatform_openapi_id`) USING BTREE,
  KEY `day_at` (`day_at`) USING BTREE,
  KEY `create_at` (`create_at`) USING BTREE,
  KEY `update_at` (`update_at`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='开放平台应用开放接口日汇总表';