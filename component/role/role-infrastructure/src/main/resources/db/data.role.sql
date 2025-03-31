-- import classpath:db/data.role.dict.sql
-- import classpath:db/data.role.func.sql

-- 添加初始角色 超级管理员
INSERT INTO `component_role` (`id`, `code`, `name`, `is_disabled`, `disabled_reason`,`is_superadmin`, `seq`, `remark`, `level`, `parent_id`, `parent_id1`, `parent_id2`, `parent_id3`, `parent_id4`, `parent_id5`, `parent_id6`, `parent_id7`, `parent_id8`, `parent_id9`, `parent_id10`, `version`, `tenant_id`, `create_at`, `create_by`, `update_at`, `update_by`) VALUES (1, 'superadmin', '超级管理员', 0, NULL, 10,1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 1, '2020-12-07 17:56:40', NULL, NULL, NULL);
-- 给用户id=1的用户默认添加超级管理员角色
INSERT INTO `component_role_user_rel` (`id`, `user_id`, `role_id`, `version`, `tenant_id`, `create_at`, `create_by`, `update_at`, `update_by`) VALUES (1, 1, 1, 1, 1, '2020-12-11 17:59:42', NULL, NULL, NULL);

-- 虚拟超级管理员
INSERT INTO component_role (id, code, name, is_disabled, disabled_reason, is_superadmin, seq, remark, level, parent_id, parent_id1, parent_id2, parent_id3, parent_id4, parent_id5, parent_id6, parent_id7, parent_id8, parent_id9, parent_id10, version, tenant_id, create_at, create_by, update_at, update_by) VALUES (1818107182885949441, 'virtual_superadmin', '虚拟超级管理员', 0, null, 0, 10, null, 1, null, null, null, null, null, null, null, null, null, null, null, 1, 1, '2024-07-30 10:11:46', 1, '2024-07-30 10:11:46', null);
INSERT INTO component_role_user_rel (id, user_id, role_id, version, tenant_id, create_at, create_by, update_at, update_by) VALUES (1818107855388069890, 1818106787283390466, 1818107182885949441, 1, 1, '2024-07-30 10:14:26', 1, '2024-07-30 10:14:26', null);
