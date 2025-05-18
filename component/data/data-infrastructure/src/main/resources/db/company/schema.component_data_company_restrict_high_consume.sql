-- 建表语句sql
DROP TABLE IF EXISTS component_data_company_restrict_high_consume;
CREATE TABLE `component_data_company_restrict_high_consume` (
  `id` bigint NOT NULL COMMENT '主键ID',
  `case_no` varchar(100) DEFAULT NULL COMMENT '案号',
  `attach_url` varchar(300) DEFAULT NULL COMMENT '文件链接，外部链接',
  `attach_snapshot_url` varchar(300) DEFAULT NULL COMMENT '文件快照链接，内部链接',
  `file_case_date` date DEFAULT NULL COMMENT '立案日期',
  `publish_date` date DEFAULT NULL COMMENT '发布日期',
  `execute_court_company_id` bigint DEFAULT NULL COMMENT '执行法院公司id',
  `execute_court_name` varchar(150) DEFAULT NULL COMMENT '执行法院名称，冗余公司名称',
  `data_md5` varchar(32) DEFAULT NULL COMMENT '数据md5,case_no + file_case_date + publish_date + execute_court_name',
  `latest_handle_at` datetime DEFAULT NULL COMMENT '最后处理时间，不代表数据有变动，用来表示数据处理过，但可能无需处理，不影响版本号变动',
  `version` int NOT NULL COMMENT '乐观锁字段',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户id',
  `create_at` datetime NOT NULL COMMENT '创建时间的时间戳',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `update_at` datetime DEFAULT NULL COMMENT '修改时间的时间戳',
  `update_by` bigint DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `case_no` (`case_no`) USING BTREE,
  UNIQUE KEY `uni_data_md5` (`data_md5`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='企业限制高消费表';
