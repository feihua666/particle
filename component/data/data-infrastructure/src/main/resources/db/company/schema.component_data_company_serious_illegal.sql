-- 建表语句sql
DROP TABLE IF EXISTS component_data_company_serious_illegal;
CREATE TABLE `component_data_company_serious_illegal` (
  `id` bigint NOT NULL COMMENT '主键ID',
  `company_id` bigint NOT NULL COMMENT '企业表ID',
  `type` varchar(100) DEFAULT NULL COMMENT '类别，如：严重违法失信企业名单',
  `put_reason` text NOT NULL COMMENT '列入原因',
  `put_date` date NOT NULL COMMENT '列入日期',
  `put_institute_company_id` bigint DEFAULT NULL COMMENT '作出列入决定机关公司id',
  `put_institute_name` varchar(150) DEFAULT NULL COMMENT '作出列入决定机关名称，冗余公司名称',
  `remove_reason` text COMMENT '移除原因',
  `remove_date` date DEFAULT NULL COMMENT '移除日期',
  `remove_institute_company_id` bigint DEFAULT NULL COMMENT '作出移除决定机关公司id',
  `remove_institute_name` varchar(150) DEFAULT NULL COMMENT '作出移除决定机关名称，冗余公司名称',
  `data_md5` varchar(32) NOT NULL COMMENT '数据md5,type + put_reason + put_date',
  `latest_handle_at` datetime DEFAULT NULL COMMENT '最后处理时间，不代表数据有变动，用来表示数据处理过，但可能无需处理，不影响版本号变动',
  `version` int NOT NULL COMMENT '乐观锁字段',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户id',
  `create_at` datetime NOT NULL COMMENT '创建时间的时间戳',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `update_at` datetime DEFAULT NULL COMMENT '修改时间的时间戳',
  `update_by` bigint DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `company_id` (`company_id`) USING BTREE,
  UNIQUE KEY `uni_company_id__data_md5` (`company_id`,`data_md5`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='企业严重违法表';
