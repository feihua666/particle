-- 建表语句sql
DROP TABLE IF EXISTS component_openplatform_provider_record_prd_api_month_summary;
CREATE TABLE `component_openplatform_provider_record_prd_api_month_summary` (
  `id` bigint NOT NULL COMMENT '表主键',
  `openplatform_provider_id` bigint DEFAULT NULL COMMENT '供应商id',
  `openplatform_provider_api_id` bigint DEFAULT NULL COMMENT '供应商接口id',
  `year` int NOT NULL COMMENT '年',
  `month` int NOT NULL COMMENT '月，取值范围1-12',
  `total_call` int NOT NULL COMMENT '调用总量',
  `total_fee_call` int NOT NULL COMMENT '调用计费总量',
  `average_unit_price_amount` decimal(11,2) NOT NULL COMMENT '平均单价金额，单位分',
  `total_fee_amount` int NOT NULL COMMENT '总消费金额，单位分',
  `remark` varchar(2000) DEFAULT NULL COMMENT '描述',
  `version` int NOT NULL COMMENT '乐观锁字段',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户id',
  `create_at` datetime NOT NULL COMMENT '创建时间的时间戳',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `update_at` datetime DEFAULT NULL COMMENT '修改时间的时间戳',
  `update_by` bigint DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uni_openplatform_provider_id_openplatform_provider_api_id_y_m` (`openplatform_provider_id`,`openplatform_provider_api_id`,`year`,`month`) USING BTREE,
  KEY `openplatform_provider_id` (`openplatform_provider_id`) USING BTREE,
  KEY `openplatform_provider_api_id` (`openplatform_provider_api_id`) USING BTREE,
  KEY `year` (`year`) USING BTREE,
  KEY `month` (`month`) USING BTREE,
  KEY `create_at` (`create_at`) USING BTREE,
  KEY `update_at` (`update_at`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='开放平台供应商接口月汇总表';
