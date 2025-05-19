-- 建表语句sql
DROP TABLE IF EXISTS component_data_company_court_announcement;
CREATE TABLE `component_data_company_court_announcement` (
  `id` bigint NOT NULL COMMENT '主键ID',
  `announcement_no` varchar(100) DEFAULT NULL COMMENT '公告号',
  `announcement_type` varchar(100) DEFAULT NULL COMMENT '公告类型',
  `case_no` varchar(255) DEFAULT NULL COMMENT '案号',
  `case_reason` varchar(255) DEFAULT NULL COMMENT '案由',
  `court_company_id` bigint DEFAULT NULL COMMENT '法院公司id',
  `court_name` varchar(150) DEFAULT NULL COMMENT '法院名称，冗余公司名称',
  `publish_page` varchar(50) DEFAULT NULL COMMENT '刊登板面页码',
  `publish_page_date` date DEFAULT NULL COMMENT '刊登板面日期',
  `publish_date` date DEFAULT NULL COMMENT '发布日期',
  `data_md5` varchar(32) NOT NULL COMMENT '数据md5,announcement_no + announcement_type + case_no + case_reason + publish_date',
  `latest_handle_at` datetime DEFAULT NULL COMMENT '最后处理时间，不代表数据有变动，用来表示数据处理过，但可能无需处理，不影响版本号变动',
  `version` int NOT NULL COMMENT '乐观锁字段',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户id',
  `create_at` datetime NOT NULL COMMENT '创建时间的时间戳',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `update_at` datetime DEFAULT NULL COMMENT '修改时间的时间戳',
  `update_by` bigint DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `announcement_no` (`announcement_no`) USING BTREE,
  KEY `case_no` (`case_no`) USING BTREE,
  UNIQUE KEY `uni_data_md5` (`data_md5`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='企业法院公告表';
