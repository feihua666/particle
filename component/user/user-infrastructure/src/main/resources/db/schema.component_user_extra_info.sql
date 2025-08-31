-- 建表语句sql
DROP TABLE IF EXISTS component_user_extra_info;
CREATE TABLE `component_user_extra_info` (
  `id` bigint NOT NULL COMMENT '主键ID',
  `user_id` bigint NOT NULL COMMENT '用户id',
  `org_name` varchar(50) DEFAULT NULL COMMENT '单位名称',
  `job_title` varchar(150) DEFAULT NULL COMMENT '职称',
  `profile` varchar(500) DEFAULT NULL COMMENT '个人简介',
  `extra_info_json` varchar(2000) DEFAULT NULL COMMENT '额外自定义非查询信息',
  `version` int NOT NULL COMMENT '乐观锁字段',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户id',
  `create_at` datetime NOT NULL COMMENT '创建时间的时间戳',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `update_at` datetime DEFAULT NULL COMMENT '修改时间的时间戳',
  `update_by` bigint DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `user_id` (`user_id`) USING BTREE,
  KEY `create_at` (`create_at`) USING BTREE,
  KEY `update_at` (`update_at`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='用户扩展信息表';
