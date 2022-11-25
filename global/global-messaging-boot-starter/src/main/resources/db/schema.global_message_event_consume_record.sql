--
DROP TABLE IF EXISTS global_message_event_consume_record;
CREATE TABLE global_message_event_consume_record  (
  `id` varchar(64)  NOT NULL COMMENT '消息id',
  `create_at` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COMMENT = '消息事件消费记录表';