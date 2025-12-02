-- 建表语句sql
DROP TABLE IF EXISTS component_data_dynamic_data_indicator_category_upload_record;
CREATE TABLE `component_data_dynamic_data_indicator_category_upload_record` (
  `id` bigint NOT NULL COMMENT '主键ID',
  `dynamic_data_indicator_category_id` bigint NOT NULL COMMENT '动态数据指标分类id',
  `upload_file_name` varchar(300) NOT NULL COMMENT '上传文件名称',
  `upload_file_url` varchar(300) NOT NULL COMMENT '上传文件地址',
  `upload_indicator_num` int NOT NULL COMMENT '上传指标数量',
  `upload_indicator_data_num` int NOT NULL COMMENT '上传数据数量',
  `is_public` tinyint(1) NOT NULL COMMENT '是否发布，1=是，0=否',
  `version` int NOT NULL COMMENT '乐观锁字段',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户id',
  `create_at` datetime NOT NULL COMMENT '创建时间的时间戳',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `update_at` datetime DEFAULT NULL COMMENT '修改时间的时间戳',
  `update_by` bigint DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `dynamic_data_indicator_category_id` (`dynamic_data_indicator_category_id`) USING BTREE,
  KEY `create_at` (`create_at`) USING BTREE,
  KEY `update_at` (`update_at`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='动态数据指标分类上传记录表';
