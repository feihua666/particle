-- 建表语句sql
DROP TABLE IF EXISTS component_func_application;
CREATE TABLE `component_func_application` (
  `id` bigint NOT NULL COMMENT '应用ID',
  `code` varchar(100) NOT NULL COMMENT '应用编码,模糊查询',
  `name` varchar(100) NOT NULL COMMENT '功能应用名称,模糊查询',
  `is_disabled` tinyint(1) NOT NULL COMMENT '是否禁用',
  `disabled_reason` varchar(255) DEFAULT NULL COMMENT '禁用原因',
  `application_theme` varchar(255) DEFAULT NULL COMMENT '应用主题，一个代表客户端主题样式的字符串',
  `application_default_route` varchar(255) DEFAULT NULL COMMENT '应用默认的页面路由',
  `application_logo_url` varchar(350) DEFAULT NULL COMMENT '应用logo地址',
  `application_icon_url` varchar(350) DEFAULT NULL COMMENT '应用图标地址',
  `config_json` varchar(2000) DEFAULT NULL COMMENT '额外配置json',
  `is_group` tinyint(1) NOT NULL COMMENT '是否为分组，不是分组就是应用，没有其它的',
  `seq` int NOT NULL COMMENT '排序,默认按该字段升序排序',
  `remark` varchar(255) DEFAULT NULL COMMENT '描述',
  `level` int NOT NULL COMMENT '层级、深度',
  `parent_id` bigint DEFAULT NULL COMMENT '父级',
  `parent_id1` bigint DEFAULT NULL COMMENT 'LEVEL为1的父id',
  `parent_id2` bigint DEFAULT NULL COMMENT 'LEVEL为2的父id',
  `parent_id3` bigint DEFAULT NULL COMMENT 'LEVEL为3的父id',
  `parent_id4` bigint DEFAULT NULL COMMENT 'LEVEL为4的父id',
  `parent_id5` bigint DEFAULT NULL COMMENT 'LEVEL为5的父id',
  `parent_id6` bigint DEFAULT NULL COMMENT 'LEVEL为6的父id',
  `parent_id7` bigint DEFAULT NULL COMMENT 'LEVEL为7的父id',
  `parent_id8` bigint DEFAULT NULL COMMENT 'LEVEL为8的父id',
  `parent_id9` bigint DEFAULT NULL COMMENT 'LEVEL为9的父id',
  `parent_id10` bigint DEFAULT NULL COMMENT 'LEVEL为10的父id',
  `version` int NOT NULL COMMENT '乐观锁字段',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户id',
  `create_at` datetime NOT NULL COMMENT '创建时间的时间戳',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `update_at` datetime DEFAULT NULL COMMENT '修改时间的时间戳',
  `update_by` bigint DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uni_code` (`code`) USING BTREE,
  KEY `idx_name` (`name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='功能应用表';