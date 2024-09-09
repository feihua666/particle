-- 建表语句sql
DROP TABLE IF EXISTS component_data_company;
CREATE TABLE `component_data_company` (
  `id` bigint NOT NULL COMMENT '主键ID',
  `name` varchar(100) DEFAULT NULL COMMENT '企业名称',
  `uscc` varchar(100) DEFAULT NULL COMMENT '统一社会信用代码，unified_social_credit_code',
  `reg_no` varchar(100) DEFAULT NULL COMMENT '注册号，registration_no',
  `org_code` varchar(100) DEFAULT NULL COMMENT '组织机构代码，organization_code',
  `en_name` varchar(100) DEFAULT NULL COMMENT '英文名称，english_name',
  `parent_id` bigint DEFAULT NULL COMMENT '父级id，如果存在父级id表示该企业为分支机构',
  `category` tinyint(2) NOT null COMMENT '分类，1=企业，2=个体，3=组代',
  `latest_update_at` datetime DEFAULT NULL COMMENT '最后更新时间，相关联的只要有更新，就需要更新该值',
  `version` int NOT NULL COMMENT '乐观锁字段',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户id',
  `create_at` datetime NOT NULL COMMENT '创建时间的时间戳',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `update_at` datetime DEFAULT NULL COMMENT '修改时间的时间戳',
  `update_by` bigint DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uscc` (`uscc`) USING BTREE,
  UNIQUE KEY `reg_no` (`reg_no`) USING BTREE,
  UNIQUE KEY `org_code` (`org_code`) USING BTREE,
  KEY `en_name` (`en_name`) USING BTREE,
  KEY `name` (`name`) USING BTREE COMMENT '企业名称可能存在重复'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='企业表';
