-- 建表语句sql
DROP TABLE IF EXISTS component_data_company_ipr_trademark_license_person;
CREATE TABLE `component_data_company_ipr_trademark_license_person` (
  `id` bigint NOT NULL COMMENT '主键ID',
  `company_ipr_trademark_license_id` bigint NOT NULL COMMENT '企业知识产权商标许可表id',
  `license_name` varchar(500) DEFAULT NULL COMMENT '原始许可人名称',
  `license_name_cn` varchar(500) DEFAULT NULL COMMENT '中文许可人名称',
  `license_name_en` varchar(500) DEFAULT NULL COMMENT '英文许可人名称',
  `is_licensed` tinyint(1) DEFAULT NULL COMMENT '是否为被许可人，1=被许可人，0=许可人',
  `data_md5` varchar(32) NOT NULL COMMENT '数据md5,license_name + is_licensed',
  `latest_handle_at` datetime DEFAULT NULL COMMENT '最后处理时间，不代表数据有变动，用来表示数据处理过，但可能无需处理，不影响版本号变动',
  `version` int NOT NULL COMMENT '乐观锁字段',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户id',
  `create_at` datetime NOT NULL COMMENT '创建时间的时间戳',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `update_at` datetime DEFAULT NULL COMMENT '修改时间的时间戳',
  `update_by` bigint DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uni_company_ipr_trademark_license_id__data_md5` (`company_ipr_trademark_license_id`,`data_md5`) USING BTREE,
  KEY `company_ipr_trademark_license_id` (`company_ipr_trademark_license_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='企业知识产权商标许可人表';
