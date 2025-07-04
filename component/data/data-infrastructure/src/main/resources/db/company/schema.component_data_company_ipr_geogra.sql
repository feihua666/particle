-- 建表语句sql
DROP TABLE IF EXISTS component_data_company_ipr_geogra;
CREATE TABLE `component_data_company_ipr_geogra` (
  `id` bigint NOT NULL COMMENT '主键ID',
  `public_no` varchar(50) DEFAULT NULL COMMENT '公告号',
  `public_date` date DEFAULT NULL COMMENT '公告日期',
  `name` varchar(500) DEFAULT NULL COMMENT '产品名称',
  `national_economic_classification` varchar(200) DEFAULT NULL COMMENT '国民经济行业分类',
  `public_type_name` varchar(100) DEFAULT NULL COMMENT '公告类型，如：批准公告',
  `public_dept_name` varchar(100) DEFAULT NULL COMMENT '公告单位，如：国家质量监督检验检疫总局',
  `area_address` varchar(100) DEFAULT NULL COMMENT '所在地域 ，如：北京市昌平区',
  `protect_scope` text COMMENT '保护范围',
  `protect_file` text COMMENT '保护范围界定文件',
  `quality_technical_requirement` text COMMENT '质量技术要求',
  `special_sign` text COMMENT '专用标志',
  `applicant_name` varchar(200) DEFAULT NULL COMMENT '申请人名称',
  `is_applicant_name_natural_person` tinyint(1) DEFAULT NULL COMMENT '是否申请人为自然人，1=自然人，0=非自然人',
  `applicant_name_company_id` bigint DEFAULT NULL COMMENT '申请人公司id，is_applicant_name_natural_person = 0 时有值',
  `applicant_name_company_person_id` bigint DEFAULT NULL COMMENT '申请人个人id，is_applicant_name_natural_person = 1 时有值',
  `applicant_address` varchar(200) DEFAULT NULL COMMENT '申请人地址',
  `primary_review_institute` varchar(200) DEFAULT NULL COMMENT '初审机构',
  `primary_review_date` date DEFAULT NULL COMMENT '初审日期',
  `agency_name` varchar(200) DEFAULT NULL COMMENT '代理机构',
  `use_product` varchar(200) DEFAULT NULL COMMENT '使用商品',
  `product_group` varchar(200) DEFAULT NULL COMMENT '商品组别',
  `file_path` varchar(300) DEFAULT NULL COMMENT '文件存放路径',
  `latest_handle_at` datetime DEFAULT NULL COMMENT '最后处理时间，不代表数据有变动，用来表示数据处理过，但可能无需处理，不影响版本号变动',
  `version` int NOT NULL COMMENT '乐观锁字段',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户id',
  `create_at` datetime NOT NULL COMMENT '创建时间的时间戳',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `update_at` datetime DEFAULT NULL COMMENT '修改时间的时间戳',
  `update_by` bigint DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `public_no` (`public_no`) USING BTREE,
  KEY `applicant_name_company_id` (`applicant_name_company_id`) USING BTREE,
  KEY `applicant_name_company_person_id` (`applicant_name_company_person_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='企业知识产权地理标识表';
