-- 建表语句sql
DROP TABLE IF EXISTS component_openplatform_openapi_record_app_month_bill;
CREATE TABLE `component_openplatform_openapi_record_app_month_bill` (
  `id` bigint NOT NULL COMMENT '表主键',
  `openplatform_app_id` bigint DEFAULT NULL COMMENT '开放平台应用id',
  `app_id` varchar(255) NOT NULL COMMENT 'appId',
  `customer_id` bigint DEFAULT NULL COMMENT '客户id',
  `year` int NOT NULL COMMENT '年',
  `month` int NOT NULL COMMENT '月，取值范围1-12',
  `total_call` int NOT NULL COMMENT '调用总量',
  `total_fee_call` int NOT NULL COMMENT '调用计费总量',
  `total_fee_amount` int NOT NULL COMMENT '总消费金额，单位分',
  `status_dict_id` bigint NOT NULL COMMENT '账单状态，字典id，如：初始生成、已结清',
  `remark` varchar(2000) DEFAULT NULL COMMENT '描述',
  `version` int NOT NULL COMMENT '乐观锁字段',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户id',
  `create_at` datetime NOT NULL COMMENT '创建时间的时间戳',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `update_at` datetime DEFAULT NULL COMMENT '修改时间的时间戳',
  `update_by` bigint DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uni_customer_id_year_month` (`openplatform_app_id`,`year`,`month`) USING BTREE,
  KEY `customer_id` (`openplatform_app_id`) USING BTREE,
  KEY `year` (`year`) USING BTREE,
  KEY `month` (`month`) USING BTREE,
  KEY `status_dict_id` (`status_dict_id`) USING BTREE,
  KEY `create_at` (`create_at`) USING BTREE,
  KEY `update_at` (`update_at`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='开放平台应用月账单表';
