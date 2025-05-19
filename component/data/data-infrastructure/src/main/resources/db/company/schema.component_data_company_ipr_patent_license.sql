-- 建表语句sql
DROP TABLE IF EXISTS component_data_company_ipr_patent_license;
CREATE TABLE `component_data_company_ipr_patent_license` (
  `id` bigint NOT NULL COMMENT '主键ID',
  `company_ipr_patent_id` bigint NOT NULL COMMENT '企业知识产权专利表id',
  `license_type` varchar(10) DEFAULT NULL COMMENT '专利权许可类型,如：1、2',
  `filing_contract_no` varchar(50) DEFAULT NULL COMMENT '专利备案合同号编码',
  `filing_contract_date` date DEFAULT NULL COMMENT '合同备案日期',
  `assignor` varchar(50) DEFAULT NULL COMMENT '让与人，转让方，如：xxxx公司',
  `contract_change_date` date DEFAULT NULL COMMENT '合同变更日期',
  `assignee` varchar(50) DEFAULT NULL COMMENT '受让人，接收方，如：xxxx公司',
  `contract_rescission_date` date DEFAULT NULL COMMENT '合同解除日期',
  `data_md5` varchar(32) NOT NULL COMMENT '数据md5,license_type + filing_contract_no + filing_contract_date + assignor + assignee',
  `latest_handle_at` datetime DEFAULT NULL COMMENT '最后处理时间，不代表数据有变动，用来表示数据处理过，但可能无需处理，不影响版本号变动',
  `version` int NOT NULL COMMENT '乐观锁字段',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户id',
  `create_at` datetime NOT NULL COMMENT '创建时间的时间戳',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `update_at` datetime DEFAULT NULL COMMENT '修改时间的时间戳',
  `update_by` bigint DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `company_ipr_patent_id` (`company_ipr_patent_id`) USING BTREE,
  UNIQUE KEY `uni_company_ipr_patent_id__data_md5` (`company_ipr_patent_id`,`data_md5`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='企业知识产权专利许可信息表';
