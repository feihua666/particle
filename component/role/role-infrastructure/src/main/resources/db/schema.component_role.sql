DROP TABLE IF EXISTS component_role;
CREATE TABLE `component_role` (
  `id` bigint NOT NULL COMMENT '角色ID',
  `code` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '角色编码,模糊查询',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '角色名称,模糊查询',
  `is_disabled` tinyint(1) NOT NULL COMMENT '是否禁用',
  `disabled_reason` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '禁用原因',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '描述',
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
  UNIQUE KEY `uni_code` (`code`) USING BTREE,
  KEY `idx_name` (`name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='角色表';