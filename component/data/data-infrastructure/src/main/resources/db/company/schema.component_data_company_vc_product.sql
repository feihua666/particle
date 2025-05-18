-- 建表语句sql
DROP TABLE IF EXISTS component_data_company_vc_product;
CREATE TABLE `component_data_company_vc_product` (
  `id` bigint NOT NULL COMMENT '主键ID',
  `company_id` bigint NOT NULL COMMENT '企业表ID',
  `product_name` varchar(100) DEFAULT NULL COMMENT '产品名称',
  `product_logo_url` varchar(300) DEFAULT NULL COMMENT '产品logo地址',
  `product_description` varchar(2000) DEFAULT NULL COMMENT '产品介绍',
  `is_primary` tinyint(1) DEFAULT NULL COMMENT '是否是该公司代表性的产品',
  `round_num` int DEFAULT NULL COMMENT '融资次数',
  `competitive_product_num` int DEFAULT NULL COMMENT '竞品数量',
  `current_round_dict_id` bigint DEFAULT NULL COMMENT '当前融资轮次',
  `amount` decimal(16,5) DEFAULT NULL COMMENT '融资金额（万元）',
  `data_md5` varchar(32) DEFAULT NULL COMMENT '数据md5,product_name',
  `latest_handle_at` datetime DEFAULT NULL COMMENT '最后处理时间，不代表数据有变动，用来表示数据处理过，但可能无需处理，不影响版本号变动',
  `version` int NOT NULL COMMENT '乐观锁字段',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户id',
  `create_at` datetime NOT NULL COMMENT '创建时间的时间戳',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `update_at` datetime DEFAULT NULL COMMENT '修改时间的时间戳',
  `update_by` bigint DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `company_id` (`company_id`) USING BTREE,
  KEY `product_name` (`product_name`) USING BTREE,
  UNIQUE KEY `uni_company_id__data_md5` (`company_id`,`data_md5`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='企业融资产品表';
