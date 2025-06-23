-- 建表语句sql
DROP TABLE IF EXISTS component_data_company_prime_staff_position;
CREATE TABLE `component_data_company_prime_staff_position` (
  `id` bigint NOT NULL COMMENT '主键ID',
  `company_prime_staff_id` bigint NOT NULL COMMENT '企业主要人员表ID',
  `position_name` varchar(50) DEFAULT NULL COMMENT '职位名称',
  `position_dict_id` bigint DEFAULT NULL COMMENT '职位，字典id',
  `latest_handle_at` datetime DEFAULT NULL COMMENT '最后处理时间，不代表数据有变动，用来表示数据处理过，但可能无需处理，不影响版本号变动',
  `version` int NOT NULL COMMENT '乐观锁字段',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户id',
  `create_at` datetime NOT NULL COMMENT '创建时间的时间戳',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `update_at` datetime DEFAULT NULL COMMENT '修改时间的时间戳',
  `update_by` bigint DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `unique_company_prime_staff_id__position_name` (`company_prime_staff_id`,`position_name`) USING BTREE,
  KEY `company_prime_staff_id` (`company_prime_staff_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='企业主要人员职位表';
