--
DROP TABLE IF EXISTS global_captcha;
CREATE TABLE global_captcha  (
  `id` varchar(64)  NOT NULL COMMENT '唯一标识id',
  `content_json` longtext  NULL DEFAULT NULL COMMENT '内容',
  `is_dynamic` tinyint(1) DEFAULT NULL COMMENT '是否为动态验证码，1=是，0=接口',
  `tenant_id` bigint NULL DEFAULT NULL COMMENT '租户id，预留',
  `create_at` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_bin  COMMENT = '验证码表';