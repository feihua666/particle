-- 建表语句sql
DROP TABLE IF EXISTS component_data_company_ipr_software_copyright;
CREATE TABLE `component_data_company_ipr_software_copyright` (
  `id` bigint NOT NULL COMMENT '主键ID',
  `reg_no` varchar(50) DEFAULT NULL COMMENT '注册号',
  `category_no` varchar(100) DEFAULT NULL COMMENT '分类号',
  `name` varchar(100) DEFAULT NULL COMMENT '软件全称',
  `name_simple` varchar(100) DEFAULT NULL COMMENT '软件简称',
  `version_no` varchar(100) DEFAULT NULL COMMENT '版本号',
  `copyright_owner` varchar(100) DEFAULT NULL COMMENT '著作权人',
  `is_copyright_owner_natural_person` tinyint(1) DEFAULT NULL COMMENT '是否著作权人为自然人，1=自然人，0=非自然人',
  `copyright_owner_company_id` bigint DEFAULT NULL COMMENT '著作权人公司id，is_copyright_owner_natural_person = 0 时有值',
  `copyright_owner_company_person_id` bigint DEFAULT NULL COMMENT '著作权人个人id，is_copyright_owner_natural_person = 1 时有值',
  `first_public_date` date DEFAULT NULL COMMENT '首次发表日期',
  `reg_date` date DEFAULT NULL COMMENT '登记日期',
  `country` varchar(50) DEFAULT NULL COMMENT '国家，如：中国',
  `latest_handle_at` datetime DEFAULT NULL COMMENT '最后处理时间，不代表数据有变动，用来表示数据处理过，但可能无需处理，不影响版本号变动',
  `version` int NOT NULL COMMENT '乐观锁字段',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户id',
  `create_at` datetime NOT NULL COMMENT '创建时间的时间戳',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `update_at` datetime DEFAULT NULL COMMENT '修改时间的时间戳',
  `update_by` bigint DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `reg_no` (`reg_no`) USING BTREE,
  KEY `copyright_owner_company_id` (`copyright_owner_company_id`) USING BTREE,
  KEY `copyright_owner_company_person_id` (`copyright_owner_company_person_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='企业知识产权软件著作表';
