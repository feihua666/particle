-- 建表语句sql
DROP TABLE IF EXISTS component_data_company_discredited_judgment_debtor;
CREATE TABLE `component_data_company_discredited_judgment_debtor` (
  `id` bigint NOT NULL COMMENT '主键ID',
  `case_no` varchar(100) DEFAULT NULL COMMENT '案号',
  `dishonest_executed_person_name` varchar(100) DEFAULT NULL COMMENT '被执行人名称，冗余公司名称',
  `is_dishonest_executed_person_natural_person` tinyint(1) DEFAULT NULL COMMENT '是否被执行人为自然人，1=自然人，0=非自然人',
  `dishonest_executed_person_company_id` bigint DEFAULT NULL COMMENT '被执行人公司id，is_dishonest_executed_person_natural_person = 0 时有值',
  `dishonest_executed_person_company_person_id` bigint DEFAULT NULL COMMENT '被执行人个人id，is_dishonest_executed_person_natural_person = 1 时有值',
  `legal_person_name` varchar(100) DEFAULT NULL COMMENT '法人名称，is_legal_person_natural_person 等于0时为人名，等于1时为公司名',
  `is_legal_person_natural_person` tinyint(1) DEFAULT NULL COMMENT '是否法人为自然人，1=自然人，0=非自然人',
  `legal_person_company_id` bigint DEFAULT NULL COMMENT '法人公司id，is_legal_person_natural_person = 0 时有值',
  `legal_person_company_person_id` bigint DEFAULT NULL COMMENT '法人个人id，is_legal_person_natural_person = 1 时有值',
  `dishonest_executed_person_license_no` varchar(50) DEFAULT NULL COMMENT '被执行人证照/证件号码',
  `execute_court_company_id` bigint DEFAULT NULL COMMENT '执行法院公司id',
  `execute_court_name` varchar(150) DEFAULT NULL COMMENT '执行法院名称，冗余公司名称',
  `file_case_date` date DEFAULT NULL COMMENT '立案日期',
  `finished_case_date` date DEFAULT NULL COMMENT '结案日期',
  `is_finished` tinyint(1) DEFAULT NULL COMMENT '是否已结案，1=已结案，0=未结案，执行中',
  `publish_date` date DEFAULT NULL COMMENT '发布日期',
  `document_no` varchar(200) DEFAULT NULL COMMENT '执行依据文号',
  `decision_basis_dept_company_id` bigint DEFAULT NULL COMMENT '做出执行的依据单位公司id',
  `decision_basis_dept_name` varchar(100) DEFAULT NULL COMMENT '做出执行的依据单位，冗余公司名称',
  `obligation` varchar(300) DEFAULT NULL COMMENT '生效法律文书确定的义务',
  `performance` varchar(100) DEFAULT NULL COMMENT '履行情况，如：全部未履行',
  `perform_part` varchar(100) DEFAULT NULL COMMENT '已履行',
  `un_perform_part` varchar(100) DEFAULT NULL COMMENT '未履行',
  `dishonest_executed_person_behavior` varchar(500) DEFAULT NULL COMMENT '失信被执行人行为具体情形，如：违反财产报告制度',
  `execute_amount` decimal(16,5) DEFAULT NULL COMMENT '执行标的金额(万元)',
  `execute_amount_currency_dict_id` bigint DEFAULT NULL COMMENT '执行标的金额币种，字典id，如：人民币',
  `data_md5` varchar(32) NOT NULL COMMENT '数据md5,case_no + dishonest_executed_person_name + obligation + performance',
  `latest_handle_at` datetime DEFAULT NULL COMMENT '最后处理时间，不代表数据有变动，用来表示数据处理过，但可能无需处理，不影响版本号变动',
  `version` int NOT NULL COMMENT '乐观锁字段',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户id',
  `create_at` datetime NOT NULL COMMENT '创建时间的时间戳',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `update_at` datetime DEFAULT NULL COMMENT '修改时间的时间戳',
  `update_by` bigint DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `case_no` (`case_no`) USING BTREE,
  UNIQUE KEY `uni_dishonest_executed_person_company_id__data_md5` (`dishonest_executed_person_company_id`,`data_md5`) USING BTREE,
  UNIQUE KEY `uni_dishonest_executed_person_company_person_id__data_md5` (`dishonest_executed_person_company_person_id`,`data_md5`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='企业失信被执行人表';
