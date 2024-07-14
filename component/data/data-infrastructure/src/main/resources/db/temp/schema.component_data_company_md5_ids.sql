-- 建表语句sql
DROP TABLE IF EXISTS component_data_company_md5_ids;
CREATE TABLE `component_data_company_md5_ids` (
  `id` bigint NOT NULL COMMENT '主键ID',
  `company_id` bigint NOT NULL COMMENT '企业id',
  `parent_id_uuid0` varchar(32) NOT NULL COMMENT 'uuid0',
  `parent_id_uuid1` varchar(32) DEFAULT NULL COMMENT 'uuid1',
  `parent_id_uuid2` varchar(32) DEFAULT NULL COMMENT 'uuid2',
  `parent_id_uuid3` varchar(32) DEFAULT NULL COMMENT 'uuid3',
  `parent_id_uuid4` varchar(32) DEFAULT NULL COMMENT 'uuid4',
  `parent_id_uuid5` varchar(32) DEFAULT NULL COMMENT 'uuid5',
  `parent_id_uuid6` varchar(32) DEFAULT NULL COMMENT 'uuid6',
  `parent_id_uuid7` varchar(32) DEFAULT NULL COMMENT 'uuid7',
  `parent_id_uuid8` varchar(32) DEFAULT NULL COMMENT 'uuid8',
  `parent_id_uuid9` varchar(32) DEFAULT NULL COMMENT 'uuid9',
  `parent_id_uuid10` varchar(32) DEFAULT NULL COMMENT 'uuid10',
  `parent_id_uuid11` varchar(32) DEFAULT NULL COMMENT 'uuid11',
  `version` int NOT NULL COMMENT '乐观锁字段',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户id',
  `create_at` datetime NOT NULL COMMENT '创建时间的时间戳',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `update_at` datetime DEFAULT NULL COMMENT '修改时间的时间戳',
  `update_by` bigint DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `company_id` (`company_id`) USING BTREE,
  KEY `parent_id_uuid0` (`parent_id_uuid0`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='企业md5ids表';
