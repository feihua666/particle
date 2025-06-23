-- 建表语句sql
DROP TABLE IF EXISTS component_data_company_ipr_trademark_transfer_person;
CREATE TABLE `component_data_company_ipr_trademark_transfer_person` (
  `id` bigint NOT NULL COMMENT '主键ID',
  `company_ipr_trademark_transfer_id` bigint NOT NULL COMMENT '企业知识产权商标转让表id',
  `transfer_name` varchar(500) DEFAULT NULL COMMENT '原始转让人名称',
  `transfer_name_cn` varchar(500) DEFAULT NULL COMMENT '中文转让人名称',
  `transfer_name_en` varchar(500) DEFAULT NULL COMMENT '英文转让人名称',
  `is_transferred` tinyint(1) DEFAULT NULL COMMENT '是否被转让人，1=被转让人，0=转让人',
  `data_md5` varchar(32) NOT NULL COMMENT '数据md5,transfer_name + is_transferred',
  `latest_handle_at` datetime DEFAULT NULL COMMENT '最后处理时间，不代表数据有变动，用来表示数据处理过，但可能无需处理，不影响版本号变动',
  `version` int NOT NULL COMMENT '乐观锁字段',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户id',
  `create_at` datetime NOT NULL COMMENT '创建时间的时间戳',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `update_at` datetime DEFAULT NULL COMMENT '修改时间的时间戳',
  `update_by` bigint DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uni_company_ipr_trademark_transfer_id__data_md5` (`company_ipr_trademark_transfer_id`,`data_md5`) USING BTREE,
  KEY `company_ipr_trademark_transfer_id` (`company_ipr_trademark_transfer_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='企业知识产权商标转让人表';
