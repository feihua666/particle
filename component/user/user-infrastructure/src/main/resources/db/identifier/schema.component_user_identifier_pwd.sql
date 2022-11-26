DROP TABLE IF EXISTS component_user_identifier_pwd;
CREATE TABLE `component_user_identifier_pwd` (
  `id` bigint NOT NULL COMMENT '表主键',
  `user_id` bigint NOT NULL COMMENT '用户id',
  `identifier_id` bigint NOT NULL COMMENT '用户标识id',
  `pwd` varchar(500) NOT NULL COMMENT '密码',
  `pwd_encrypt_flag` varchar(255) NOT NULL COMMENT '密码加密方式标识',
  `is_expired` tinyint(1) NOT NULL COMMENT '是否过期，过期后该密码不能登录',
  `expired_reason` varchar(255) DEFAULT NULL COMMENT '过期原因',
  `expire_at` datetime DEFAULT NULL COMMENT '到期时间，为空永不到期',
  `is_need_update` tinyint(1) NOT NULL COMMENT '是否需要提示修改密码',
  `pwd_modified_at` datetime NOT NULL COMMENT '密码的修改时间',
  `complexity` int NOT NULL COMMENT '复杂度，数字越高越复杂，取值 1-100',
  `group_flag` varchar(255) DEFAULT NULL COMMENT '分组标识',
  `version` int NOT NULL COMMENT '乐观锁字段',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户id',
  `create_at` datetime NOT NULL COMMENT '创建时间的时间戳',
  `create_by` varchar(20) DEFAULT NULL COMMENT '创建人',
  `update_at` datetime DEFAULT NULL COMMENT '修改时间的时间戳',
  `update_by` varchar(20) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `identifier_id` (`identifier_id`) USING BTREE,
  KEY `group_flag` (`group_flag`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户密码表';
