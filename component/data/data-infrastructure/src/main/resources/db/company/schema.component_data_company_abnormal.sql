-- 建表语句sql
DROP TABLE IF EXISTS component_data_company_abnormal;
CREATE TABLE `component_data_company_abnormal` (
  `id` bigint NOT NULL COMMENT '主键ID',
  `company_id` bigint NOT NULL COMMENT '企业表ID',
  `company_name` varchar(100) DEFAULT NULL COMMENT '企业名称',
  `put_no` varchar(100) DEFAULT NULL COMMENT '列入决定书文号',
  `put_reason` text NOT NULL COMMENT '列入异常名录原因',
  `put_date` date NOT NULL COMMENT '列入异常名录日期',
  `put_institute_company_id` bigint DEFAULT NULL COMMENT '作出列入决定机关公司id',
  `put_institute_name` varchar(150) DEFAULT NULL COMMENT '作出列入决定机关名称，冗余公司名称',
  `remove_no` varchar(100) DEFAULT NULL COMMENT '移出决定书文号',
  `remove_reason` text COMMENT '移出异常名录原因',
  `remove_date` date DEFAULT NULL COMMENT '移出异常名录日期',
  `remove_institute_company_id` bigint DEFAULT NULL COMMENT '作出移出决定机关公司id',
  `remove_institute_name` varchar(150) DEFAULT NULL COMMENT '作出移出决定机关名称，冗余公司名称',
  `data_md5` varchar(32) NOT NULL COMMENT '数据md5,put_no + put_reason + put_date + put_institute_name',
  `latest_handle_at` datetime DEFAULT NULL COMMENT '最后处理时间，不代表数据有变动，用来表示数据处理过，但可能无需处理，不影响版本号变动',
  `version` int NOT NULL COMMENT '乐观锁字段',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户id',
  `create_at` datetime NOT NULL COMMENT '创建时间的时间戳',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `update_at` datetime DEFAULT NULL COMMENT '修改时间的时间戳',
  `update_by` bigint DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uni_company_id__data_md5` (`company_id`,`data_md5`) USING BTREE,
  KEY `company_id` (`company_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='企业经营异常表';
