-- 权限管理分组菜单
INSERT INTO `component_func` (`id`, `code`, `name`, `func_group_id`, `icon`, `is_disabled`, `disabled_reason`, `url`, `permissions`, `type_dict_id`, `active_name`, `is_show`, `remark`, `seq`, `level`, `parent_id`, `parent_id1`, `parent_id2`, `parent_id3`, `parent_id4`, `parent_id5`, `parent_id6`, `parent_id7`, `parent_id8`, `parent_id9`, `parent_id10`, `version`, `tenant_id`, `create_at`, `create_by`, `update_at`, `update_by`) VALUES (1607565198887501825, 'permission_manage', '权限管理', 1, 'Lock', 0, NULL, NULL, NULL, 21, NULL, 1, NULL, 10, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 1, '2022-12-27 10:33:05', 1, NULL, NULL);
-- 角色管理
INSERT INTO `component_func` (`id`, `code`, `name`, `func_group_id`, `icon`, `is_disabled`, `disabled_reason`, `url`, `permissions`, `type_dict_id`, `active_name`, `is_show`, `remark`, `seq`, `level`, `parent_id`, `parent_id1`, `parent_id2`, `parent_id3`, `parent_id4`, `parent_id5`, `parent_id6`, `parent_id7`, `parent_id8`, `parent_id9`, `parent_id10`, `version`, `tenant_id`, `create_at`, `create_by`, `update_at`, `update_by`) VALUES (1607569903432777729, 'role_manage', '角色管理', 1, 'Unlock', 0, NULL, '/admin/roleManagePage', NULL, 22, NULL, 1, NULL, 10, 2, 1607565198887501825, 1607565198887501825, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 1, '2022-12-27 10:51:47', 1, NULL, NULL);
-- 角色管理按钮
INSERT INTO `component_func` (`id`, `code`, `name`, `func_group_id`, `icon`, `is_disabled`, `disabled_reason`, `url`, `permissions`, `type_dict_id`, `active_name`, `is_show`, `remark`, `seq`, `level`, `parent_id`, `parent_id1`, `parent_id2`, `parent_id3`, `parent_id4`, `parent_id5`, `parent_id6`, `parent_id7`, `parent_id8`, `parent_id9`, `parent_id10`, `version`, `tenant_id`, `create_at`, `create_by`, `update_at`, `update_by`) VALUES (1607573453437157378, 'role_manage_query', '角色查询', 1, 'Link', 0, NULL, NULL, 'adminRoleManagePage,admin:web:role:pageQuery,admin:web:role:queryList', 1605497232817737729, NULL, 1, NULL, 10, 3, 1607569903432777729, 1607565198887501825, 1607569903432777729, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 1, '2022-12-27 11:05:53', 1, NULL, NULL);
INSERT INTO `component_func` (`id`, `code`, `name`, `func_group_id`, `icon`, `is_disabled`, `disabled_reason`, `url`, `permissions`, `type_dict_id`, `active_name`, `is_show`, `remark`, `seq`, `level`, `parent_id`, `parent_id1`, `parent_id2`, `parent_id3`, `parent_id4`, `parent_id5`, `parent_id6`, `parent_id7`, `parent_id8`, `parent_id9`, `parent_id10`, `version`, `tenant_id`, `create_at`, `create_by`, `update_at`, `update_by`) VALUES (1607573453764313090, 'role_manage_create', '角色添加', 1, 'Link', 0, NULL, NULL, 'adminRoleManageAdd,admin:web:role:create,admin:web:role:queryList', 1605497232817737729, NULL, 1, NULL, 10, 3, 1607569903432777729, 1607565198887501825, 1607569903432777729, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 1, '2022-12-27 11:05:53', 1, NULL, NULL);
INSERT INTO `component_func` (`id`, `code`, `name`, `func_group_id`, `icon`, `is_disabled`, `disabled_reason`, `url`, `permissions`, `type_dict_id`, `active_name`, `is_show`, `remark`, `seq`, `level`, `parent_id`, `parent_id1`, `parent_id2`, `parent_id3`, `parent_id4`, `parent_id5`, `parent_id6`, `parent_id7`, `parent_id8`, `parent_id9`, `parent_id10`, `version`, `tenant_id`, `create_at`, `create_by`, `update_at`, `update_by`) VALUES (1607573454083080193, 'role_manage_delete', '角色删除', 1, 'Link', 0, NULL, NULL, 'admin:web:role:delete', 1605497232817737729, NULL, 1, NULL, 10, 3, 1607569903432777729, 1607565198887501825, 1607569903432777729, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 1, '2022-12-27 11:05:53', 1, NULL, NULL);
INSERT INTO `component_func` (`id`, `code`, `name`, `func_group_id`, `icon`, `is_disabled`, `disabled_reason`, `url`, `permissions`, `type_dict_id`, `active_name`, `is_show`, `remark`, `seq`, `level`, `parent_id`, `parent_id1`, `parent_id2`, `parent_id3`, `parent_id4`, `parent_id5`, `parent_id6`, `parent_id7`, `parent_id8`, `parent_id9`, `parent_id10`, `version`, `tenant_id`, `create_at`, `create_by`, `update_at`, `update_by`) VALUES (1607573454380875777, 'role_manage_update', '角色修改', 1, 'Link', 0, NULL, NULL, 'adminRoleManageUpdate,admin:web:role:update,admin:web:role:queryList', 1605497232817737729, NULL, 1, NULL, 10, 3, 1607569903432777729, 1607565198887501825, 1607569903432777729, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 1, '2022-12-27 11:05:53', 1, NULL, NULL);