-- 建表语句sql
DROP TABLE IF EXISTS component_data_company_vc_product_competitive_product_rel;
CREATE TABLE `component_data_company_vc_product_competitive_product_rel` (
  `id` bigint NOT NULL COMMENT '主键ID',
  `company_vc_product_id` bigint NOT NULL COMMENT '企业融资产品表ID',
  `company_vc_competitive_product_id` bigint NOT NULL COMMENT '企业竞品id，企业融资产品表ID',
  `latest_handle_at` datetime DEFAULT NULL COMMENT '最后处理时间，不代表数据有变动，用来表示数据处理过，但可能无需处理，不影响版本号变动',
  `version` int NOT NULL COMMENT '乐观锁字段',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户id',
  `create_at` datetime NOT NULL COMMENT '创建时间的时间戳',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `update_at` datetime DEFAULT NULL COMMENT '修改时间的时间戳',
  `update_by` bigint DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `company_vc_competitive_product_id` (`company_vc_competitive_product_id`) USING BTREE,
  UNIQUE KEY `uni_company_vc_product_id__company_vc_competitive_product_id` (`company_vc_product_id`,`company_vc_competitive_product_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='企业融资产品竞品关系表';
