-- 建表语句sql
DROP TABLE IF EXISTS component_openplatform_doc_dir;
CREATE TABLE `component_openplatform_doc_dir` (
  `id` bigint NOT NULL COMMENT 'id主键',
  `name` varchar(100) NOT NULL COMMENT '名称',
  `name_simple` varchar(100) DEFAULT NULL COMMENT '简称',
  `openplatform_doc_dir_name_id` bigint NOT NULL COMMENT '开放接口文档目录名称id',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `seq` int NOT NULL COMMENT '排序,默认按该字段升序排序',
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
  KEY `name` (`name`) USING BTREE,
  KEY `name_simple` (`name_simple`) USING BTREE,
  KEY `parent_id1` (`parent_id1`) USING BTREE,
  KEY `parent_id2` (`parent_id2`) USING BTREE,
  KEY `parent_id3` (`parent_id3`) USING BTREE,
  KEY `parent_id4` (`parent_id4`) USING BTREE,
  KEY `parent_id5` (`parent_id5`) USING BTREE,
  KEY `parent_id6` (`parent_id6`) USING BTREE,
  KEY `parent_id7` (`parent_id7`) USING BTREE,
  KEY `parent_id8` (`parent_id8`) USING BTREE,
  KEY `parent_id9` (`parent_id9`) USING BTREE,
  KEY `parent_id10` (`parent_id10`) USING BTREE,
  KEY `openplatform_doc_dir_name_id` (`openplatform_doc_dir_name_id`) USING BTREE,
  KEY `level` (`level`) USING BTREE,
  KEY `parent_id` (`parent_id`) USING BTREE,
  KEY `seq` (`seq`) USING BTREE,
  KEY `create_at` (`create_at`) USING BTREE,
  KEY `update_at` (`update_at`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='开放接口文档目录表';