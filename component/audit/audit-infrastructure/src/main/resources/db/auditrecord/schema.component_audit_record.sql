-- 建表语句sql
DROP TABLE IF EXISTS component_audit_record;
CREATE TABLE `component_audit_record` (
  `id` bigint NOT NULL COMMENT '主键ID',
  `data_id` bigint NOT NULL COMMENT '数据id，标识是哪个数据的审批记录',
  `audit_result_dict_id` bigint NOT NULL COMMENT '审核结果类型，如：同意且无修改意见、同意且有修改意见、不同意且有修改意见',
  `audit_comment` varchar(500) DEFAULT NULL COMMENT '审核意见',
  `audit_at` datetime NOT NULL COMMENT '审核时间',
  `audit_by` bigint NOT NULL COMMENT '审核人',
  `data_pre_status_dict_id` bigint DEFAULT NULL COMMENT '数据审核之前状态，字典id，如：草稿、退回（可修改）',
  `data_post_status_dict_id` bigint NOT NULL COMMENT '数据审核之后状态，字典id，如：草稿、退回（可修改）',
  `group_flag` varchar(255) NOT NULL COMMENT '分组标识，如：cms_content',
  `group_flag_memo` varchar(255) NOT NULL COMMENT '分组标识备忘，如：cms模板内容审核',
  `remark` varchar(255) DEFAULT NULL COMMENT '描述',
  `version` int NOT NULL COMMENT '乐观锁字段',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户id',
  `create_at` datetime NOT NULL COMMENT '创建时间的时间戳',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `update_at` datetime DEFAULT NULL COMMENT '修改时间的时间戳',
  `update_by` bigint DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `data_id` (`data_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='审核记录表';
