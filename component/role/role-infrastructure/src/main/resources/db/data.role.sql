-- 添加初始角色 超级管理员
INSERT INTO `component_role` (`id`, `code`, `name`, `is_disabled`, `disabled_reason`, `remark`, `level`, `parent_id`, `parent_id1`, `parent_id2`, `parent_id3`, `parent_id4`, `parent_id5`, `parent_id6`, `parent_id7`, `parent_id8`, `parent_id9`, `parent_id10`, `version`, `tenant_id`, `create_at`, `create_by`, `update_at`, `update_by`) VALUES (1, 'superadmin', '超级管理员', 0, NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 1, '2020-12-07 17:56:40', NULL, NULL, NULL);
-- 给用户id=1的用户默认添加超级管理员角色
INSERT INTO `component_role_user_rel` (`id`, `user_id`, `role_id`, `version`, `tenant_id`, `create_at`, `create_by`, `update_at`, `update_by`) VALUES (1, 1, 1, 1, 1, '2020-12-11 17:59:42', NULL, NULL, NULL);
