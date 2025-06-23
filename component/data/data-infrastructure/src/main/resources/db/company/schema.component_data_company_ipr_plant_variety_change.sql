-- 建表语句sql
DROP TABLE IF EXISTS component_data_company_ipr_plant_variety_change;
CREATE TABLE `component_data_company_ipr_plant_variety_change` (
  `id` bigint NOT NULL COMMENT '主键ID',
  `company_ipr_plant_variety_id` bigint NOT NULL COMMENT '企业知识产权植物新品种表id',
  `change_date` date DEFAULT NULL COMMENT '变更日期',
  `change_before` varchar(100) DEFAULT NULL COMMENT '变更前',
  `change_after` varchar(100) DEFAULT NULL COMMENT '变更后',
  `change_reason` varchar(200) DEFAULT NULL COMMENT '原因',
  `is_name` tinyint(1) DEFAULT NULL COMMENT '是否名称变更',
  `is_transfer` tinyint(1) DEFAULT NULL COMMENT '是否转让变更',
  `is_cultivate` tinyint(1) DEFAULT NULL COMMENT '是否培育人变更',
  `is_applicant` tinyint(1) DEFAULT NULL COMMENT '是否申请人变更',
  `is_agent` tinyint(1) DEFAULT NULL COMMENT '是否代理人变更',
  `is_agency` tinyint(1) DEFAULT NULL COMMENT '是否代理机构变更',
  `data_md5` varchar(32) DEFAULT NULL COMMENT '数据md5,change_date + change_before + change_after + change_reason + is_name + is_transfer + is_cultivate + is_applicant + is_agent + is_agency',
  `latest_handle_at` datetime DEFAULT NULL COMMENT '最后处理时间，不代表数据有变动，用来表示数据处理过，但可能无需处理，不影响版本号变动',
  `version` int NOT NULL COMMENT '乐观锁字段',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户id',
  `create_at` datetime NOT NULL COMMENT '创建时间的时间戳',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `update_at` datetime DEFAULT NULL COMMENT '修改时间的时间戳',
  `update_by` bigint DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uni_company_ipr_plant_variety_id__data_md5` (`company_ipr_plant_variety_id`,`data_md5`) USING BTREE,
  KEY `company_ipr_plant_variety_id` (`company_ipr_plant_variety_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='企业知识产权植物新品种变更信息表';
