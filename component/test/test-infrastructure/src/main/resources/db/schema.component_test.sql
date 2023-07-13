DROP TABLE IF EXISTS component_test;
CREATE TABLE `component_test` (
  `id` bigint NOT NULL COMMENT '用户ID',
  `nickname` varchar(50) NOT NULL COMMENT '昵称，姓名,模糊查询',
  `gender_dict_id` bigint DEFAULT NULL COMMENT '性别，字典id',
  `avatar` varchar(500) DEFAULT NULL COMMENT '头像，建议图片相对路径',
  `is_lock` tinyint(1) NOT NULL COMMENT '锁定状态，0=未锁定；1=锁定',
  `lock_reason` varchar(255) DEFAULT NULL COMMENT '锁定原因',
  `group_flag` varchar(255) DEFAULT NULL COMMENT '分组标识',
  `source_from_dict_id` bigint DEFAULT NULL COMMENT '用户来源，字典id',
  `version` int NOT NULL COMMENT '乐观锁字段',
  `tenant_id` bigint NULL DEFAULT NULL COMMENT '租户id',
  `create_at` datetime NOT NULL COMMENT '创建时间的时间戳',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `update_at` datetime DEFAULT NULL COMMENT '修改时间的时间戳',
  `update_by` bigint DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `gender` (`gender_dict_id`) USING BTREE,
  KEY `is_lock` (`is_lock`) USING BTREE,
  KEY `nickname` (`nickname`) USING BTREE,
  KEY `group_flag` (`group_flag`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='测试表';
