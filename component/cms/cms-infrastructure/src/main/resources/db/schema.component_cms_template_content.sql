-- 建表语句sql
DROP TABLE IF EXISTS component_cms_template_content;
CREATE TABLE `component_cms_template_content` (
  `id` bigint NOT NULL COMMENT '主键ID',
  `data_id` bigint NOT NULL COMMENT '数据id，不同模板类型，分类对象不同id',
  `type_name` varchar(100) NOT NULL COMMENT '模板类型，site=端点首页模板、channel=栏目模板、content=内容模板',
  `content` longtext NOT NULL COMMENT '模板内容',
  `version` int NOT NULL COMMENT '乐观锁字段',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户id',
  `create_at` datetime NOT NULL COMMENT '创建时间的时间戳',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `update_at` datetime DEFAULT NULL COMMENT '修改时间的时间戳',
  `update_by` bigint DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uni_data_id__type_name` (`data_id`,`type_name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='模板内容表';
