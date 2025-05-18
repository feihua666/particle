-- 建表语句sql
DROP TABLE IF EXISTS component_data_company_ipr_patent_notice;
CREATE TABLE `component_data_company_ipr_patent_notice` (
  `id` bigint NOT NULL COMMENT '主键ID',
  `company_ipr_patent_id` bigint NOT NULL COMMENT '企业知识产权专利表id',
  `public_date` date DEFAULT NULL COMMENT '通知发文日期',
  `mail_no` varchar(50) DEFAULT NULL COMMENT '通知挂号',
  `receiver_name` varchar(50) DEFAULT NULL COMMENT '通知收件人姓名，如：中国国际贸易促进委员会专利商标事务所',
  `notice_type` varchar(50) DEFAULT NULL COMMENT '通知书类型,如：20112、29503',
  `notice_type_description` varchar(200) DEFAULT NULL COMMENT '通知书类型说明,如：发明专利申请公布及进入实质审查阶段通知书、国际申请进入国家阶段初步审查合格通知书、复审请求补正通知书',
  `data_md5` varchar(32) DEFAULT NULL COMMENT '数据md5,public_date + mail_no + receiver_name + notice_type + notice_type_description',
  `latest_handle_at` datetime DEFAULT NULL COMMENT '最后处理时间，不代表数据有变动，用来表示数据处理过，但可能无需处理，不影响版本号变动',
  `version` int NOT NULL COMMENT '乐观锁字段',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户id',
  `create_at` datetime NOT NULL COMMENT '创建时间的时间戳',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `update_at` datetime DEFAULT NULL COMMENT '修改时间的时间戳',
  `update_by` bigint DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `company_ipr_patent_id` (`company_ipr_patent_id`) USING BTREE,
  UNIQUE KEY `uni_company_ipr_patent_id__data_md5` (`company_ipr_patent_id`,`data_md5`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='企业知识产权专利通知书信息表';
