DROP TABLE IF EXISTS component_user_login_device;
CREATE TABLE `component_user_login_device` (
  `id` bigint NOT NULL COMMENT '用户ID',
  `user_id` bigint NOT NULL COMMENT '用户id',
  `login_first_at` datetime NOT NULL COMMENT '首次登录时间',
  `login_last_at` datetime NOT NULL COMMENT '最后一次登录时间',
  `device_id` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '设备id，可以用来唯一标识一个设备',
  `device_name` varchar(255) NOT NULL COMMENT '设备名称，如：xxx的Iphone',
  `validate_at` datetime NOT NULL COMMENT '验证通过时间',
  `operating_system` varchar(255) NOT NULL COMMENT '操作系统及版本',
  `version` int NOT NULL COMMENT '乐观锁字段',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户id',
  `create_at` datetime NOT NULL COMMENT '创建时间的时间戳',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `update_at` datetime DEFAULT NULL COMMENT '修改时间的时间戳',
  `update_by` bigint DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `user_id` (`user_id`),
  KEY `user_id__user_identifier_id` (`user_id`,`validate_at`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='用户登录设备表';
