-- 建表语句sql
DROP TABLE IF EXISTS component_data_company_honor_qualification;
CREATE TABLE `component_data_company_honor_qualification` (
  `id` bigint NOT NULL COMMENT '主键ID',
  `company_id` bigint NOT NULL COMMENT '企业表ID',
  `name` varchar(100) DEFAULT NULL COMMENT '名称,如：高新技术企业',
  `level` varchar(100) DEFAULT NULL COMMENT '等级，如：国家级',
  `publish_date` date DEFAULT NULL COMMENT '发布日期',
  `start_date` date DEFAULT NULL COMMENT '发布日期',
  `end_date` date DEFAULT NULL COMMENT '截止日期',
  `certificate_no` varchar(100) DEFAULT NULL COMMENT '证书编号',
  `publish_office` varchar(100) DEFAULT NULL COMMENT '发布单位，如：福州市工业和信息化局',
  `publish_title` varchar(200) DEFAULT NULL COMMENT '发布标题，如：福州市2022年工业龙头企业名单',
  `data_md5` varchar(32) NOT NULL COMMENT '数据md5,name + level + certificate_no + publish_office + publish_title',
  `latest_handle_at` datetime DEFAULT NULL COMMENT '最后处理时间，不代表数据有变动，用来表示数据处理过，但可能无需处理，不影响版本号变动',
  `version` int NOT NULL COMMENT '乐观锁字段',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户id',
  `create_at` datetime NOT NULL COMMENT '创建时间的时间戳',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `update_at` datetime DEFAULT NULL COMMENT '修改时间的时间戳',
  `update_by` bigint DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `company_id` (`company_id`) USING BTREE,
  KEY `certificate_no` (`certificate_no`) USING BTREE,
  UNIQUE KEY `uni_company_id__data_md5` (`company_id`,`data_md5`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='企业荣誉资质表';
