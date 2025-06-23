-- 建表语句sql
DROP TABLE IF EXISTS component_data_company_ipr_geogra_approve_announcement;
CREATE TABLE `component_data_company_ipr_geogra_approve_announcement` (
  `id` bigint NOT NULL COMMENT '主键ID',
  `company_ipr_geogra_id` bigint DEFAULT NULL COMMENT '企业知识产权地理标识id',
  `approve_public_no` varchar(50) DEFAULT NULL COMMENT '核准公告编号',
  `company_name` varchar(50) DEFAULT NULL COMMENT '企业名称',
  `approve_address` varchar(50) DEFAULT NULL COMMENT '核准地址',
  `approve_legal_person_name` varchar(100) DEFAULT NULL COMMENT '核准法人代表',
  `product_name` varchar(100) DEFAULT NULL COMMENT '产品名称',
  `approve_trademark` varchar(100) DEFAULT NULL COMMENT '核准商标',
  `approve_date` date DEFAULT NULL COMMENT '核准日期',
  `approve_remark` varchar(200) DEFAULT NULL COMMENT '核准备注',
  `latest_handle_at` datetime DEFAULT NULL COMMENT '最后处理时间，不代表数据有变动，用来表示数据处理过，但可能无需处理，不影响版本号变动',
  `version` int NOT NULL COMMENT '乐观锁字段',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户id',
  `create_at` datetime NOT NULL COMMENT '创建时间的时间戳',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `update_at` datetime DEFAULT NULL COMMENT '修改时间的时间戳',
  `update_by` bigint DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uni_company_ipr_geogra_id__approve_public_no` (`company_ipr_geogra_id`,`approve_public_no`) USING BTREE,
  KEY `company_ipr_geogra_id` (`company_ipr_geogra_id`) USING BTREE,
  KEY `approve_public_no` (`approve_public_no`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='企业知识产权地理标识核准公告表';
