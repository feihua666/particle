-- 建表语句sql
DROP TABLE IF EXISTS component_data_company_prime_staff;
CREATE TABLE `component_data_company_prime_staff` (
  `id` bigint NOT NULL COMMENT '主键ID',
  `company_id` bigint NOT NULL COMMENT '企业表ID',
  `staff_name` varchar(50) DEFAULT NULL COMMENT '姓名',
  `staff_company_person_id` bigint DEFAULT NULL COMMENT '个人表ID',
  `position_names` varchar(50) DEFAULT NULL COMMENT '职位名称，多个以逗号分隔',
  `latest_handle_at` datetime DEFAULT NULL COMMENT '最后处理时间，不代表数据有变动，用来表示数据处理过，但可能无需处理，不影响版本号变动',
  `version` int NOT NULL COMMENT '乐观锁字段',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户id',
  `create_at` datetime NOT NULL COMMENT '创建时间的时间戳',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `update_at` datetime DEFAULT NULL COMMENT '修改时间的时间戳',
  `update_by` bigint DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `unique_company_id__staff_name` (`company_id`,`staff_name`) USING BTREE,
  KEY `company_id` (`company_id`) USING BTREE,
  KEY `staff_company_person_id` (`staff_company_person_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='企业主要人员表';
