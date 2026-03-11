-- 建表语句sql
DROP TABLE IF EXISTS component_audit_record_snapshot_data;
CREATE TABLE `component_audit_record_snapshot_data` (
  `id` bigint NOT NULL COMMENT '主键ID',
  `audit_record_id` bigint NOT NULL COMMENT '审核记录id',
  `data_id` bigint NOT NULL COMMENT '数据id，标识是哪个数据快照',
  `data_snapshot_data_id` bigint NOT NULL COMMENT '快照数据id，标识是哪个快照数据的id',
  `snapshot_json` longtext NOT NULL COMMENT '快照内容json',
  `version` int NOT NULL COMMENT '乐观锁字段',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户id',
  `create_at` datetime NOT NULL COMMENT '创建时间的时间戳',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `update_at` datetime DEFAULT NULL COMMENT '修改时间的时间戳',
  `update_by` bigint DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `audit_record_id` (`audit_record_id`) USING BTREE,
  KEY `data_id` (`data_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='审核记录数据快照表';
