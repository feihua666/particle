-- 建表语句sql
DROP TABLE IF EXISTS component_cms_site;
CREATE TABLE `component_cms_site` (
  `id` bigint NOT NULL COMMENT '主键ID',
  `code` varchar(100) DEFAULT NULL COMMENT '站点编码',
  `name` varchar(100) NOT NULL COMMENT '站点名称',
  `domain` varchar(200) DEFAULT NULL COMMENT '站点域名',
  `deploy_path` varchar(200) DEFAULT NULL COMMENT '部署路径，主要用于动态页前端静态页面路径访问',
  `path` varchar(200) DEFAULT NULL COMMENT '站点访问上下文路径，主要应用于动态页访问，可以实现在一个域名下区分不同的站点',
  `template_path` varchar(200) NOT NULL COMMENT '站点模板路径，站点模板存放路径，pc默认default,移动端默认mobile,站点首页模板默认index.html',
  `template_index` varchar(200) NOT NULL COMMENT '站点首页模板,默认index.html',
  `template404_path` varchar(200) NOT NULL COMMENT '404模板路径',
  `template404_index` varchar(200) NOT NULL COMMENT '404内容模板,默认404.html',
  `static_path` varchar(200) DEFAULT NULL COMMENT '站点静态页存放路径',
  `is_prime_site` tinyint(1) NOT NULL COMMENT '是否主站点，同一个域名即domain字段下，只能有一个主站',
  `pv` int NOT NULL COMMENT '页面访问量,页面展示次数',
  `iv` int NOT NULL COMMENT '页面访问ip数,一天之内相同IP地址只被计算一次',
  `uv` int NOT NULL COMMENT '页面访问用户数,页面访问电脑客户端数,一天之内相同cookie的访问只被计算1次',
  `version` int NOT NULL COMMENT '乐观锁字段',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户id',
  `create_at` datetime NOT NULL COMMENT '创建时间的时间戳',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `update_at` datetime DEFAULT NULL COMMENT '修改时间的时间戳',
  `update_by` bigint DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `name` (`name`) USING BTREE,
  UNIQUE KEY `code` (`code`) USING BTREE,
  KEY `domain` (`domain`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='站点表';
