
# 数据库

- 标准表：普通的表，业务表
- 树表：树形结构，如部门表、菜单表
- 关系表：关系，如用户和角色关系表

不同的表根据下面示例模板对号创建
```sql
DROP TABLE IF EXISTS {table_name};
CREATE TABLE `{table_name}` (
  -- 1. 主键
  `id` bigint NOT NULL COMMENT 'id主键',
  -- 2. 树表必须包含的公共字段
  `seq` int NOT NULL COMMENT '排序,默认按该字段升序排序',
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
  -- 3. 业务字段区域
  -- {business_columns}
  -- 4. 必须包含的公共字段
  `version` int NOT NULL COMMENT '乐观锁字段',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户id',
  `create_at` datetime NOT NULL COMMENT '创建时间的时间戳',
  `create_by` bigint DEFAULT NULL COMMENT '创建人',
  `update_at` datetime DEFAULT NULL COMMENT '修改时间的时间戳',
  `update_by` bigint DEFAULT NULL COMMENT '修改人',
  -- 5. 索引区域
  PRIMARY KEY (`id`) USING BTREE,
  -- UNIQUE KEY `uk_{unique_column}` (`{unique_column}`) USING BTREE,
  KEY `idx_parent_id` (`parent_id`) USING BTREE,
  KEY `idx_level` (`level`) USING BTREE
  -- KEY `idx_{column}` (`{column}`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='{table_comment}';
```
