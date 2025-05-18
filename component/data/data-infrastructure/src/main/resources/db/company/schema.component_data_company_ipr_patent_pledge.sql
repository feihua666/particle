-- 建表语句sql
DROP TABLE IF EXISTS component_data_company_ipr_patent_pledge;
CREATE TABLE `component_data_company_ipr_patent_pledge` (
  `id` bigint NOT NULL COMMENT '主键ID',
  `company_ipr_patent_id` bigint NOT NULL COMMENT '企业知识产权专利表id',
  `pledge_no` varchar(50) DEFAULT NULL COMMENT '质押登记号',
  `pledge_preserve_type` varchar(50) DEFAULT NULL COMMENT '质押保全类型，如：1;1;1、1',
  `pledge_preserve_right_type` varchar(50) DEFAULT NULL COMMENT '质押保全权力类型，如：2;2;2;2;2;2、2',
  `effective_date` date DEFAULT NULL COMMENT '生效日期',
  `change_date` date DEFAULT NULL COMMENT '变更日期',
  `pledgor` varchar(50) DEFAULT NULL COMMENT '出质人，如：xxxx公司',
  `pledgee` varchar(50) DEFAULT NULL COMMENT '质权人，如：xxxx公司',
  `rescission_date` date DEFAULT NULL COMMENT '解除日期',
  `latest_handle_at` datetime DEFAULT NULL COMMENT '最后处理时间，不代表数据有变动，用来表示数据处理过，但可能无需处理，不影响版本号变动',
  `version` int NOT NULL COMMENT '乐观锁字段',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户id',
  `create_at` datetime NOT NULL COMMENT '创建时间的时间戳',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `update_at` datetime DEFAULT NULL COMMENT '修改时间的时间戳',
  `update_by` bigint DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `company_ipr_patent_id` (`company_ipr_patent_id`) USING BTREE,
  KEY `pledge_no` (`pledge_no`) USING BTREE,
  UNIQUE KEY `uni_company_ipr_patent_id__pledge_no` (`company_ipr_patent_id`,`pledge_no`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='企业知识产权专利质押信息表';
