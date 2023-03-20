--
DROP TABLE IF EXISTS global_message_event;
CREATE TABLE global_message_event  (
  `id` varchar(64)  NOT NULL COMMENT '消息id',
  `mq` varchar(100)  NULL DEFAULT NULL COMMENT '消息通道',
  `status` varchar(20) NOT NULL COMMENT '消息通道',
  `content` text  NOT NULL COMMENT '消息内容，json数据',
  `tenant_id` bigint NULL DEFAULT NULL COMMENT '租户id，预留',
  `create_at` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_bin  COMMENT = '消息事件表';