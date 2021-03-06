DROP TABLE IF EXISTS component_user;
CREATE TABLE `component_user` (
  `id` bigint NOT NULL COMMENT '用户ID',
  `nickname` varchar(50) NOT NULL COMMENT '昵称，姓名，模糊查询',
  `gender_dict_id` bigint DEFAULT NULL COMMENT '性别，字典id',
  `avatar` varchar(500) DEFAULT NULL COMMENT '头像，图片绝对路径',
  `serial_no` varchar(50) DEFAULT NULL COMMENT '用户编号，可以做为员工编号',
  `comp_id` bigint DEFAULT NULL COMMENT '公司id，冗余字段，由dept_id对应公司派生',
  `dept_id` bigint DEFAULT NULL COMMENT '部门id',
  `is_virtual` tinyint(1) NOT NULL COMMENT '是否虚拟用户，虚拟用户代表不是一个真正存在的用户',
  `is_lock` tinyint(1) NOT NULL COMMENT '锁定状态，0=未锁定；1=锁定',
  `lock_reason` varchar(255) DEFAULT NULL COMMENT '锁定原因',
  `category_dict_id` bigint DEFAULT NULL COMMENT '用户分类字典，标识是哪一类用户，比如后台用户等',
  `group_flag` varchar(255) DEFAULT NULL COMMENT '分组标识',
  `source_from_dict_id` bigint DEFAULT NULL COMMENT '用户来源，字典id',
  `password` varchar(255) DEFAULT NULL,
  `version` int NOT NULL COMMENT '乐观锁字段',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户id',
  `create_at` datetime NOT NULL COMMENT '创建时间的时间戳',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `update_at` datetime DEFAULT NULL COMMENT '修改时间的时间戳',
  `update_by` bigint DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `serial_no` (`serial_no`) USING BTREE COMMENT '一个用户只能有一个编码',
  KEY `gender` (`gender_dict_id`) USING BTREE,
  KEY `is_lock` (`is_lock`) USING BTREE,
  KEY `dept_id` (`dept_id`) USING BTREE,
  KEY `nickname` (`nickname`) USING BTREE,
  KEY `comp_id` (`comp_id`) USING BTREE,
  KEY `group_flag` (`group_flag`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=COMPACT COMMENT='后台管理用户表';
