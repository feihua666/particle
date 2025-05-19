-- 建表语句sql
DROP TABLE IF EXISTS component_data_company_ipr_patent_legal_status;
CREATE TABLE `component_data_company_ipr_patent_legal_status` (
  `id` bigint NOT NULL COMMENT '主键ID',
  `company_ipr_patent_id` bigint NOT NULL COMMENT '企业知识产权专利表id',
  `legal_status_dict_id` bigint DEFAULT NULL COMMENT '法律状态，字典id',
  `legal_status_code` varchar(100) DEFAULT NULL COMMENT '法律状态代码',
  `legal_status_name` varchar(200) DEFAULT NULL COMMENT '原始法律状态名称',
  `legal_status_name_en` varchar(200) DEFAULT NULL COMMENT '英文法律状态名称',
  `legal_status_name_cn` varchar(200) DEFAULT NULL COMMENT '中文法律状态名称',
  `legal_status_detail` varchar(500) DEFAULT NULL COMMENT '原始法律状态详情',
  `legal_status_detail_en` varchar(500) DEFAULT NULL COMMENT '英文法律状态详情',
  `legal_status_detail_cn` varchar(500) DEFAULT NULL COMMENT '中文法律状态详情',
  `legal_status_date` date DEFAULT NULL COMMENT '法律状态日期',
  `data_md5` varchar(32) NOT NULL COMMENT '数据md5,legal_status_name + legal_status_detail + legal_status_date',
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='企业知识产权专利法律状态表';
