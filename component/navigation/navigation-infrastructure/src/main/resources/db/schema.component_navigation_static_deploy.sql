-- 建表语句sql
DROP TABLE IF EXISTS component_navigation_static_deploy;
CREATE TABLE `component_navigation_static_deploy` (
  `id` bigint NOT NULL COMMENT '主键ID',
  `code` varchar(255) DEFAULT NULL COMMENT '部署编码',
  `name` varchar(100) NOT NULL COMMENT '部署名称',
  `front_domain` varchar(300) DEFAULT NULL COMMENT '前端域名地址，如：http://www.example.com',
  `front_context_path` varchar(100) DEFAULT NULL COMMENT '前端上下文路径，如：/nav',
  `front_sub_context_path` varchar(100) DEFAULT NULL COMMENT '前端上下文路径的下一级路径，如：/nav',
  `is_pure_static_deploy` tinyint(1) NOT NULL COMMENT '是否纯静态部署，1=纯静态部署，0=半静态部署即只部署前端页面图片或接口不可用',
  `deploy_path` varchar(100) NOT NULL COMMENT '部署路径，如：/data/nav',
  `deploy_post_groovy_script` text DEFAULT NULL COMMENT '部署成功后执行的groovy脚本，方便进行额外处理',
  `last_deploy_at` datetime DEFAULT NULL COMMENT '上一次部署时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '描述',
  `version` int NOT NULL COMMENT '乐观锁字段',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户id',
  `create_at` datetime DEFAULT NULL COMMENT '创建时间的时间戳',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `update_at` datetime DEFAULT NULL COMMENT '修改时间的时间戳',
  `update_by` bigint DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `code` (`code`) USING BTREE,
  KEY `name` (`name`) USING BTREE,
  KEY `create_at` (`create_at`) USING BTREE,
  KEY `update_at` (`update_at`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='导航网站静态部署表';
