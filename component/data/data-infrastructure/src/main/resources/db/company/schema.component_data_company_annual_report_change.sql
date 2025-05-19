-- 建表语句sql
DROP TABLE IF EXISTS component_data_company_annual_report_change;
CREATE TABLE `component_data_company_annual_report_change` (
  `id` bigint NOT NULL COMMENT '主键ID',
  `company_id` bigint NOT NULL COMMENT '企业表ID',
  `company_annual_report_id` bigint NOT NULL COMMENT '企业年报表ID',
  `year` int NOT NULL COMMENT '年报年度',
  `serial_number` int DEFAULT NULL COMMENT '序号',
  `change_item_dict_id` bigint DEFAULT NULL COMMENT '变更事项,字典id',
  `change_item_name` varchar(100) NOT NULL COMMENT '变更事项',
  `content_before` varchar(2000) DEFAULT NULL COMMENT '变更前内容',
  `content_after` varchar(2000) DEFAULT NULL COMMENT '变更后内容',
  `change_date` date DEFAULT NULL COMMENT '变更日期',
  `data_md5` varchar(32) NOT NULL COMMENT '数据md5,change_item_name + content_before + content_after + change_date',
  `latest_handle_at` datetime DEFAULT NULL COMMENT '最后处理时间，不代表数据有变动，用来表示数据处理过，但可能无需处理，不影响版本号变动',
  `version` int NOT NULL COMMENT '乐观锁字段',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户id',
  `create_at` datetime NOT NULL COMMENT '创建时间的时间戳',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `update_at` datetime DEFAULT NULL COMMENT '修改时间的时间戳',
  `update_by` bigint DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `company_id` (`company_id`) USING BTREE,
  KEY `company_annual_report_id` (`company_annual_report_id`) USING BTREE,
  KEY `year` (`year`) USING BTREE,
  UNIQUE KEY `uni_company_annual_report_id__data_md5` (`company_annual_report_id`,`data_md5`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='企业年报变更表';
