-- 建表语句sql
DROP TABLE IF EXISTS component_data_company_md5;
CREATE TABLE `component_data_company_md5` (
  `id` bigint NOT NULL COMMENT '主键ID',
  `company_id` bigint NOT NULL COMMENT '企业id',
  `name_md5` varchar(32) DEFAULT NULL COMMENT '企业名称md5',
  `uscc_md5` varchar(32) DEFAULT NULL COMMENT '统一社会信用代码md5',
  `reg_no_md5` varchar(32) DEFAULT NULL COMMENT '注册号md5',
  `org_code_md5` varchar(32) DEFAULT NULL COMMENT '组织机构代码md5',
  `en_name_md5` varchar(100) DEFAULT NULL COMMENT '英文名称md5',
  `version` int NOT NULL COMMENT '乐观锁字段',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户id',
  `create_at` datetime NOT NULL COMMENT '创建时间的时间戳',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `update_at` datetime DEFAULT NULL COMMENT '修改时间的时间戳',
  `update_by` bigint DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uscc_md5` (`uscc_md5`) USING BTREE,
  UNIQUE KEY `reg_no_md5` (`reg_no_md5`) USING BTREE,
  UNIQUE KEY `org_code_md5` (`org_code_md5`) USING BTREE,
  KEY `en_name_md5` (`en_name_md5`) USING BTREE,
  KEY `name_md5` (`name_md5`) USING BTREE COMMENT '企业名称可能存在重复'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='企业md5表';
