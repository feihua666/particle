-- 建表语句sql
DROP TABLE IF EXISTS component_data_company_annual_report_equity_change;
CREATE TABLE `component_data_company_annual_report_equity_change` (
  `id` bigint NOT NULL COMMENT '主键ID',
  `company_id` bigint NOT NULL COMMENT '企业表ID',
  `company_annual_report_id` bigint NOT NULL COMMENT '企业年报表ID',
  `year` int NOT NULL COMMENT '年报年度',
  `serial_number` int DEFAULT NULL COMMENT '序号',
  `shareholder_name` varchar(100) DEFAULT NULL COMMENT '股东名称，可能是企业名称也可能是个人名称',
  `is_shareholder_natural_person` tinyint(1) DEFAULT NULL COMMENT '是否股东为自然人，1=自然人，0=非自然人',
  `shareholder_company_id` bigint DEFAULT NULL COMMENT '股东公司id，is_shareholder_natural_person = 0 时有值',
  `shareholder_company_person_id` bigint DEFAULT NULL COMMENT '股东个人id，is_shareholder_natural_person = 1 时有值',
  `percent_before` decimal(11,2) DEFAULT NULL COMMENT '变更前比例',
  `percent_after` decimal(11,2) DEFAULT NULL COMMENT '变更后比例',
  `change_date` date DEFAULT NULL COMMENT '变更日期',
  `data_md5` varchar(32) DEFAULT NULL COMMENT '数据md5,shareholder_name + percent_before + percent_after + change_date',
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='企业年报股权变更表';
