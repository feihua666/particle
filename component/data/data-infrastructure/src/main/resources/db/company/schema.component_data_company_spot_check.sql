-- 建表语句sql
DROP TABLE IF EXISTS component_data_company_spot_check;
CREATE TABLE `component_data_company_spot_check` (
  `id` bigint NOT NULL COMMENT '主键ID',
  `company_id` bigint NOT NULL COMMENT '企业表ID',
  `check_institute` varchar(100) DEFAULT NULL COMMENT '检查实施机关',
  `check_institute_company_id` bigint DEFAULT NULL COMMENT '检查实施机关公司id',
  `check_type_name` varchar(100) DEFAULT NULL COMMENT '检查类型，如：检查',
  `check_date` date DEFAULT NULL COMMENT '检查日期',
  `check_result` varchar(200) DEFAULT NULL COMMENT '检查结果',
  `data_md5` varchar(32) NOT NULL COMMENT '数据md5,check_institute + check_type_name + check_date + check_result',
  `latest_handle_at` datetime DEFAULT NULL COMMENT '最后处理时间，不代表数据有变动，用来表示数据处理过，但可能无需处理，不影响版本号变动',
  `version` int NOT NULL COMMENT '乐观锁字段',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户id',
  `create_at` datetime NOT NULL COMMENT '创建时间的时间戳',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `update_at` datetime DEFAULT NULL COMMENT '修改时间的时间戳',
  `update_by` bigint DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uni_company_id` (`company_id`,`data_md5`) USING BTREE,
  KEY `company_id` (`company_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='企业抽查检查表';
