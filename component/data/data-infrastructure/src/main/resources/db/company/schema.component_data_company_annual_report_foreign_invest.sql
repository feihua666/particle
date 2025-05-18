-- 建表语句sql
DROP TABLE IF EXISTS component_data_company_annual_report_foreign_invest;
CREATE TABLE `component_data_company_annual_report_foreign_invest` (
  `id` bigint NOT NULL COMMENT '主键ID',
  `company_id` bigint NOT NULL COMMENT '企业表ID',
  `company_annual_report_id` bigint NOT NULL COMMENT '企业年报表ID',
  `year` int NOT NULL COMMENT '年报年度',
  `serial_number` int DEFAULT NULL COMMENT '序号',
  `invest_company_id` bigint DEFAULT NULL COMMENT '对外投资企业，企业表ID',
  `invest_company_name` varchar(100) DEFAULT NULL COMMENT '对外投资企业名称',
  `invest_company_uscc` varchar(100) DEFAULT NULL COMMENT '对外投资企业统一社会信用代码',
  `invest_percent` decimal(11,2) DEFAULT NULL COMMENT '对外投资比例',
  `invest_amount` decimal(16,5) DEFAULT NULL COMMENT '对外投资金额（万元）',
  `invest_amount_currency_dict_id` bigint DEFAULT NULL COMMENT '对外投资金额币种，字典id，如：人民币',
  `data_md5` varchar(32) DEFAULT NULL COMMENT '数据md5,invest_company_name + invest_company_uscc',
  `latest_handle_at` datetime DEFAULT NULL COMMENT '最后处理时间，不代表数据有变动，用来表示数据处理过，但可能无需处理，不影响版本号变动',
  `version` int NOT NULL COMMENT '乐观锁字段',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户id',
  `create_at` datetime NOT NULL COMMENT '创建时间的时间戳',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `update_at` datetime DEFAULT NULL COMMENT '修改时间的时间戳',
  `update_by` bigint DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `company_id` (`company_id`) USING BTREE,
  KEY `company_annual_report_id` (`company_annual_report_id`) USING BTREE,
  KEY `year` (`year`) USING BTREE,
  UNIQUE KEY `uni_company_annual_report_id__data_md5` (`company_annual_report_id`,`data_md5`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='企业年报对外投资表';
