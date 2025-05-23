-- 建表语句sql
DROP TABLE IF EXISTS component_data_company_annual_report;
CREATE TABLE `component_data_company_annual_report` (
  `id` bigint NOT NULL COMMENT '主键ID',
  `company_id` bigint NOT NULL COMMENT '企业表ID',
  `year` int NOT NULL COMMENT '年报年度',
  `company_name` varchar(100) DEFAULT NULL COMMENT '企业名称',
  `uscc` varchar(100) DEFAULT NULL COMMENT '统一社会信用代码',
  `reg_no` varchar(100) DEFAULT NULL COMMENT '注册号',
  `capital` decimal(16,5) DEFAULT NULL COMMENT '资金数额（万元）',
  `capital_currency_dict_id` bigint DEFAULT NULL COMMENT '资金数额币种，字典id，如：人民币',
  `operator_company_person_id` bigint DEFAULT NULL COMMENT '经营者id，对应的人id',
  `operator_name` varchar(255) DEFAULT NULL COMMENT '经营者名称，人名，一般为法人，冗余 operator_company_person_id',
  `postal_address` varchar(255) DEFAULT NULL COMMENT '企业通信地址',
  `post_code` varchar(20) DEFAULT NULL COMMENT '邮政编码',
  `contact_phone` varchar(50) DEFAULT NULL COMMENT '企业联系电话',
  `email` varchar(100) DEFAULT NULL COMMENT '企业电子邮箱',
  `employee_num` int DEFAULT NULL COMMENT '从业人数',
  `female_employee_num` int DEFAULT NULL COMMENT '其中女性从业人数',
  `status_dict_id` bigint DEFAULT NULL COMMENT '企业经营状态,字典id，如：开业',
  `holding_control_info` varchar(2000) DEFAULT NULL COMMENT '企业控股情况',
  `is_has_invest_or_bug_equity` tinyint(1) DEFAULT NULL COMMENT '是否有投资信息或购买其他公司股权',
  `is_has_website` tinyint(1) DEFAULT NULL COMMENT '是否有网站或网店',
  `is_has_foreign_guarantee` tinyint(1) DEFAULT NULL COMMENT '是否有对外提供担保信息',
  `is_has_equity_transfer` tinyint(1) DEFAULT NULL COMMENT '有限责任公司本年度是否发生股东股权转让',
  `normal_business_scope` text COMMENT '经营范围（一般项目）',
  `approved_business_scope` text COMMENT '经营范围（许可项目）',
  `is_is_has_foreign_guarantee_public` tinyint(1) DEFAULT NULL COMMENT '是否对外提供担保信息公示',
  `is_female_employee_num_public` tinyint(1) DEFAULT NULL COMMENT '是否其中女性从业人数公示',
  `is_holding_control_info_public` tinyint(1) DEFAULT NULL COMMENT '是否企业控股情况公示',
  `latest_handle_at` datetime DEFAULT NULL COMMENT '最后处理时间，不代表数据有变动，用来表示数据处理过，但可能无需处理，不影响版本号变动',
  `version` int NOT NULL COMMENT '乐观锁字段',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户id',
  `create_at` datetime NOT NULL COMMENT '创建时间的时间戳',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `update_at` datetime DEFAULT NULL COMMENT '修改时间的时间戳',
  `update_by` bigint DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uni_company_id__year` (`company_id`,`year`) USING BTREE,
  KEY `company_id` (`company_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='企业年报表';
