-- 建表语句sql
DROP TABLE IF EXISTS component_feedback_attachment;
CREATE TABLE `component_feedback_attachment` (
  `id` bigint NOT NULL COMMENT '主键ID',
  `feedback_id` bigint NOT NULL COMMENT '反馈id',
  `attachment_name` varchar(300) NOT NULL COMMENT '附件名称',
  `attachment_url` varchar(300) NOT NULL COMMENT '附件地址',
  `version` int NOT NULL COMMENT '乐观锁字段',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户id',
  `create_at` datetime NOT NULL COMMENT '创建时间的时间戳',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `update_at` datetime DEFAULT NULL COMMENT '修改时间的时间戳',
  `update_by` bigint DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `feedback_id` (`feedback_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='意见反馈附件表';
