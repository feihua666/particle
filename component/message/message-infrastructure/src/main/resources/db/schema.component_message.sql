-- 建表语句sql
DROP TABLE IF EXISTS component_message;
CREATE TABLE `component_message` (
  `id` bigint NOT NULL COMMENT '表主键',
  `title` varchar(255) NOT NULL COMMENT '消息标题',
  `short_content` varchar(255) DEFAULT NULL COMMENT '消息简短内容',
  `content` text NOT NULL COMMENT '消息内容',
  `is_content_html` tinyint(1) NOT NULL COMMENT '内容是否为html，1=是，0=否',
  `business_data_json` varchar(2000) DEFAULT NULL COMMENT '业务内容json',
  `business_id` bigint DEFAULT NULL COMMENT '业务数据id',
  `type_dict_id` bigint DEFAULT NULL COMMENT '消息分类，字典',
  `send_status_dict_id` bigint NOT NULL COMMENT '发送状态，字典id，未发送，发送中，已发送',
  `send_user_id` bigint DEFAULT NULL COMMENT '发送人id',
  `send_at` datetime DEFAULT NULL COMMENT '发送时间',
  `is_system` tinyint(1) NOT NULL COMMENT '是否为系统消息，1=是，0=否',
  `message_template_id` bigint DEFAULT NULL COMMENT '消息模板id，用来追踪是哪个模板',
  `version` int NOT NULL COMMENT '乐观锁字段',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户id',
  `create_at` datetime NOT NULL COMMENT '创建时间的时间戳',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `update_at` datetime DEFAULT NULL COMMENT '修改时间的时间戳',
  `update_by` bigint DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `type_dict_id` (`type_dict_id`) USING BTREE,
  KEY `send_status_dict_id` (`send_status_dict_id`) USING BTREE,
  KEY `business_id` (`business_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='消息表';
