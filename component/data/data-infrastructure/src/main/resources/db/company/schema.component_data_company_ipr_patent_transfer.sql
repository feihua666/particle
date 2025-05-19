-- 建表语句sql
DROP TABLE IF EXISTS component_data_company_ipr_patent_transfer;
CREATE TABLE `component_data_company_ipr_patent_transfer` (
  `id` bigint NOT NULL COMMENT '主键ID',
  `company_ipr_patent_id` bigint NOT NULL COMMENT '企业知识产权专利表id',
  `transfer_type` varchar(50) DEFAULT NULL COMMENT '转移类型,如：1、2',
  `dept` varchar(50) DEFAULT NULL COMMENT '部门',
  `change_before_right_holder` varchar(100) DEFAULT NULL COMMENT '变更前权利人，如：xxxx公司',
  `change_before_address` varchar(100) DEFAULT NULL COMMENT '变更前地址',
  `change_after_right_holder` varchar(100) DEFAULT NULL COMMENT '变更后权利人，如：xxxx公司',
  `change_after_address` varchar(100) DEFAULT NULL COMMENT '变更后地址',
  `current_right_holder` varchar(100) DEFAULT NULL COMMENT '当前权利人，如：xxxx公司',
  `current_address` varchar(100) DEFAULT NULL COMMENT '当前地址',
  `change_effective_date` date DEFAULT NULL COMMENT '变更生效日期',
  `data_md5` varchar(32) NOT NULL COMMENT '数据md5,transfer_type + dept + change_before_right_holder + change_after_right_holder + current_right_holder',
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='企业知识产权专利转让信息表';
