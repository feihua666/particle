-- import classpath:db/data.dept.dict.sql
-- import classpath:db/data.dept.func.sql
-- import classpath:db/data.dept.datasconstraint.sql

-- 默认初始化部门
INSERT INTO `component_dept` (`id`, `code`, `name`, `type_dict_id`, `master_user_id`, `is_virtual`, `is_comp`, `remark`, `level`, `parent_id`, `parent_id1`, `parent_id2`, `parent_id3`, `parent_id4`, `parent_id5`, `parent_id6`, `parent_id7`, `parent_id8`, `parent_id9`, `parent_id10`, `version`, `tenant_id`, `create_at`, `create_by`, `update_at`, `update_by`) VALUES (1809516493813653506, NULL, 'partilce团队', 1646092523035791361, NULL, 0, 1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 1, '2024-07-06 17:15:26', 1, '2024-07-06 17:15:26', NULL);
INSERT INTO `component_dept` (`id`, `code`, `name`, `type_dict_id`, `master_user_id`, `is_virtual`, `is_comp`, `remark`, `level`, `parent_id`, `parent_id1`, `parent_id2`, `parent_id3`, `parent_id4`, `parent_id5`, `parent_id6`, `parent_id7`, `parent_id8`, `parent_id9`, `parent_id10`, `version`, `tenant_id`, `create_at`, `create_by`, `update_at`, `update_by`) VALUES (1809517604217888770, NULL, '技术开发部', 1646092523035791361, NULL, 0, 0, NULL, 2, 1809516493813653506, 1809516493813653506, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 1, '2024-07-06 17:19:51', 1, '2024-07-06 17:19:51', NULL);

-- 系统默认组织机构树名称，使用该名称意味着直接使用 schema.component_dept.sql 表的结构数据，不需要再建立 schema.component_dept_tree.sql
INSERT INTO `component_dept_tree_name` (`id`, `code`, `name`, `remark`, `version`, `tenant_id`, `create_at`, `create_by`, `update_at`, `update_by`) VALUES (1809517036325904386, 'system_default', '系统默认', '直接按组织机构管理中的结构,请勿使用该名建立组织机构树管理中的数据', 2, 1, '2024-07-06 17:17:35', 1, '2024-07-06 17:18:20', 1);

-- 默认将超级管理员添加到 particle团队公司下
INSERT INTO `component_dept_user_rel` (`id`, `user_id`, `dept_id`, `version`, `tenant_id`, `create_at`, `create_by`, `update_at`, `update_by`) VALUES (1809502277035720706, 1, 1809516493813653506, 3, 1, '2024-07-06 16:18:56', 1, '2024-07-06 16:18:56', 1);