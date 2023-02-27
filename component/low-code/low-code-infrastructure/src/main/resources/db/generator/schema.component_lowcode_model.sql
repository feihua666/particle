DROP TABLE IF EXISTS component_lowcode_model;
CREATE TABLE `component_lowcode_model` (
  `id` bigint NOT NULL COMMENT 'ID',
  `name` varchar(100) NOT NULL COMMENT '名称',
  `name_en` varchar(100) NOT NULL COMMENT '英文名称',
  `name_en_entity` varchar(100) NOT NULL COMMENT '实体名称，首字母大写，符合java类名规范',
  `name_en_entity_var` varchar(100) NOT NULL COMMENT '实体变量名称，name_en_entity的首字母小写',
  `table_name` varchar(255) DEFAULT NULL COMMENT '表名称',
  `table_type_dict_id` bigint NOT NULL COMMENT '模型表类型字典id，rel,tree,normal',
  `request_path` varchar(255) NOT NULL COMMENT '请求路径，不要以斜杠开关如：user',
  `remark` varchar(255) DEFAULT NULL COMMENT '描述,注意事项等',
  `version` int NOT NULL COMMENT '乐观锁字段',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户id',
  `create_at` datetime NOT NULL COMMENT '创建时间的时间戳',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `update_at` datetime DEFAULT NULL COMMENT '修改时间的时间戳',
  `update_by` bigint DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `version` (`version`) USING BTREE,
  KEY `name` (`name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='低代码模型表';
