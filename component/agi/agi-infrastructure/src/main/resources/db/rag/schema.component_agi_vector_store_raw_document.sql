-- 建表语句sql
DROP TABLE IF EXISTS component_agi_vector_store_raw_document;
CREATE TABLE `component_agi_vector_store_raw_document` (
  `id` bigint NOT NULL COMMENT '主键ID',
  `name` varchar(500) NOT NULL COMMENT '名称，可以是文件名，标题等',
  `file_name` varchar(500) NOT NULL COMMENT '文件名称，类型为文件时有值',
  `type_dict_id` bigint NOT NULL COMMENT '类型，字典id,如：word，excel，weblink',
  `url` varchar(500) NOT NULL COMMENT '地址，类型为文件、网址时有值',
  `is_embedded` tinyint(1) NOT NULL COMMENT '是否已嵌入',
  `remark` varchar(255) DEFAULT NULL COMMENT '描述',
  `version` int NOT NULL COMMENT '乐观锁字段',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户id',
  `create_at` datetime NOT NULL COMMENT '创建时间的时间戳',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `update_at` datetime DEFAULT NULL COMMENT '修改时间的时间戳',
  `update_by` bigint DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `name` (`name`) USING BTREE,
  KEY `file_name` (`file_name`) USING BTREE,
  KEY `type_dict_id` (`type_dict_id`) USING BTREE,
  KEY `version` (`version`) USING BTREE,
  KEY `create_at` (`create_at`) USING BTREE,
  KEY `update_at` (`update_at`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='知识存储原始文档表';
