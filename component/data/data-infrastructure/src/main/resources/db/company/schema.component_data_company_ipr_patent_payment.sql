-- 建表语句sql
DROP TABLE IF EXISTS component_data_company_ipr_patent_payment;
CREATE TABLE `component_data_company_ipr_patent_payment` (
  `id` bigint NOT NULL COMMENT '主键ID',
  `company_ipr_patent_id` bigint NOT NULL COMMENT '企业知识产权专利表id',
  `fee_amount` decimal(11,2) DEFAULT NULL COMMENT '费用金额(元)',
  `fee_type` varchar(100) DEFAULT NULL COMMENT '费用种类,如：发明专利第13年年费、发明专利申请费、权利要求附加费、优先权要求费、宽限费、发明专利文印费、发明专利申请审查费、变更费、发明专利年费、发明专利登记印刷费、发明专利维持费',
  `receipt_no` varchar(50) DEFAULT NULL COMMENT '收据号',
  `payer` varchar(50) DEFAULT NULL COMMENT '缴费人信息，如：xxxx公司',
  `handle_status` varchar(100) DEFAULT NULL COMMENT '处理状态,如：处理结束',
  `pay_date` date DEFAULT NULL COMMENT '缴费日期',
  `data_md5` varchar(32) NOT NULL COMMENT '数据md5,fee_type + receipt_no + payer + handle_status + pay_date',
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='企业知识产权专利缴费信息表';
