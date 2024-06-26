-- 建表语句sql
DROP TABLE IF EXISTS component_op_log;
CREATE TABLE `component_op_log` (
  `id` bigint NOT NULL COMMENT '主键ID',
  `name` varchar(100) NOT NULL COMMENT '操作名称,模糊查询',
  `module_dict_id` bigint DEFAULT NULL COMMENT '模块对应的字典id',
  `module` varchar(255) NOT NULL COMMENT '模块，一个模块英语字符串标识',
  `type_dict_id` bigint DEFAULT NULL COMMENT '类型对应的字典id',
  `type` varchar(255) NOT NULL COMMENT '类型，一个操作类型英文字符串标识，如：create、delete',
  `user_id` bigint DEFAULT NULL COMMENT '操作用户id',
  `user_name` varchar(255) DEFAULT NULL COMMENT '操作用户姓名',
  `user_nickname` varchar(255) DEFAULT NULL COMMENT '操作用户昵称',
  `user_avatar` varchar(500) DEFAULT NULL COMMENT '操作用户头像',
  `url` varchar(2000) DEFAULT NULL COMMENT '请求地址，要求带http',
  `ip` varchar(500) DEFAULT NULL COMMENT '请求ip',
  `main_data_id` bigint DEFAULT NULL COMMENT '主数据id',
  `main_data_table` varchar(255) DEFAULT NULL COMMENT '主数据表名',
  `main_data_entity` varchar(255) DEFAULT NULL COMMENT '主数据载体，一般为实体DO类全路径',
  `operate_at` datetime NOT NULL COMMENT '操作时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '描述',
  `level` int NOT NULL COMMENT '层级、深度',
  `parent_id` bigint DEFAULT NULL COMMENT '父级',
  `parent_id1` bigint DEFAULT NULL COMMENT 'LEVEL为1的父id',
  `parent_id2` bigint DEFAULT NULL COMMENT 'LEVEL为2的父id',
  `parent_id3` bigint DEFAULT NULL COMMENT 'LEVEL为3的父id',
  `parent_id4` bigint DEFAULT NULL COMMENT 'LEVEL为4的父id',
  `parent_id5` bigint DEFAULT NULL COMMENT 'LEVEL为5的父id',
  `parent_id6` bigint DEFAULT NULL COMMENT 'LEVEL为6的父id',
  `parent_id7` bigint DEFAULT NULL COMMENT 'LEVEL为7的父id',
  `parent_id8` bigint DEFAULT NULL COMMENT 'LEVEL为8的父id',
  `parent_id9` bigint DEFAULT NULL COMMENT 'LEVEL为9的父id',
  `parent_id10` bigint DEFAULT NULL COMMENT 'LEVEL为10的父id',
  `version` int NOT NULL COMMENT '乐观锁字段',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户id',
  `create_at` datetime NOT NULL COMMENT '创建时间的时间戳',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `update_at` datetime DEFAULT NULL COMMENT '修改时间的时间戳',
  `update_by` bigint DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_name` (`name`) USING BTREE,
  KEY `user_id` (`user_id`),
  KEY `module_dict_id` (`module_dict_id`),
  KEY `type_dict_id` (`type_dict_id`),
  KEY `main_data_id` (`main_data_id`),
  KEY `create_at` (`create_at`) USING BTREE,
  KEY `update_at` (`update_at`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='操作日志表';
