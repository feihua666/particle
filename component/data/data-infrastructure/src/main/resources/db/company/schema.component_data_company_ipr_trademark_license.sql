-- 建表语句sql
DROP TABLE IF EXISTS component_data_company_ipr_trademark_license;
CREATE TABLE `component_data_company_ipr_trademark_license` (
  `id` bigint NOT NULL COMMENT '主键ID',
  `company_ipr_trademark_id` bigint NOT NULL COMMENT '企业知识产权商标表id',
  `license_issue_no` varchar(50) DEFAULT NULL COMMENT '许可期号',
  `license_page_no` varchar(50) DEFAULT NULL COMMENT '许可页码',
  `license_filing_no` varchar(50) DEFAULT NULL COMMENT '许可备案号',
  `license_filing_public_date` date DEFAULT NULL COMMENT '许可备案公告日期',
  `license_term` varchar(500) DEFAULT NULL COMMENT '许可期限',
  `license_good_service` varchar(1500) DEFAULT NULL COMMENT '许可使用的商品服务',
  `data_md5` varchar(32) NOT NULL COMMENT '数据md5,license_issue_no',
  `latest_handle_at` datetime DEFAULT NULL COMMENT '最后处理时间，不代表数据有变动，用来表示数据处理过，但可能无需处理，不影响版本号变动',
  `version` int NOT NULL COMMENT '乐观锁字段',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户id',
  `create_at` datetime NOT NULL COMMENT '创建时间的时间戳',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `update_at` datetime DEFAULT NULL COMMENT '修改时间的时间戳',
  `update_by` bigint DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uni_company_ipr_trademark_id__data_md5` (`company_ipr_trademark_id`,`data_md5`) USING BTREE,
  KEY `company_ipr_trademark_id` (`company_ipr_trademark_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='企业知识产权商标许可信息表';
