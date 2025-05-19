-- 建表语句sql
DROP TABLE IF EXISTS component_data_company_open_court_announcement_party;
CREATE TABLE `component_data_company_open_court_announcement_party` (
  `id` bigint NOT NULL COMMENT '主键ID',
  `company_open_court_announcement_id` bigint DEFAULT NULL COMMENT '开庭公告表id',
  `party_name` varchar(100) DEFAULT NULL COMMENT '当事人名称',
  `is_party_natural_person` tinyint(1) DEFAULT NULL COMMENT '是否当事人为自然人，1=自然人，0=非自然人',
  `party_company_id` bigint DEFAULT NULL COMMENT '当事人公司id，is_party_natural_person = 0 时有值',
  `party_company_person_id` bigint DEFAULT NULL COMMENT '当事人个人id，is_party_natural_person = 1 时有值',
  `party_role_dict_id` bigint DEFAULT NULL COMMENT '当事人角色,字典id，如：原告、被告',
  `party_description` varchar(2000) DEFAULT NULL COMMENT '当事人描述信息',
  `data_md5` varchar(32) NOT NULL COMMENT '数据md5,party_name',
  `latest_handle_at` datetime DEFAULT NULL COMMENT '最后处理时间，不代表数据有变动，用来表示数据处理过，但可能无需处理，不影响版本号变动',
  `version` int NOT NULL COMMENT '乐观锁字段',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户id',
  `create_at` datetime NOT NULL COMMENT '创建时间的时间戳',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `update_at` datetime DEFAULT NULL COMMENT '修改时间的时间戳',
  `update_by` bigint DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `party_company_id` (`party_company_id`) USING BTREE,
  KEY `party_company_person_id` (`party_company_person_id`) USING BTREE,
  UNIQUE KEY `uni_company_open_court_announcement_id__data_md5` (`company_open_court_announcement_id`,`data_md5`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='企业开庭公告当事人表';
