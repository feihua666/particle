-- 租户管理根菜单
INSERT INTO `component_func` (`id`, `code`, `name`, `func_group_id`, `icon`, `is_disabled`, `disabled_reason`, `url`, `permissions`, `type_dict_id`, `active_name`, `is_show`, `component_of`, `remark`, `seq`, `level`, `parent_id`, `parent_id1`, `parent_id2`, `parent_id3`, `parent_id4`, `parent_id5`, `parent_id6`, `parent_id7`, `parent_id8`, `parent_id9`, `parent_id10`, `version`, `tenant_id`, `create_at`, `create_by`, `update_at`, `update_by`) VALUES (1645787686251315201, 'tenant_manage_menu', '租户管理', 1, 'UserFilled', 0, NULL, NULL, NULL, 21, NULL, 1,'tenant', NULL, 10, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 1, '2023-04-11 21:55:36', 1, NULL, NULL);
-- 租户管理页面
INSERT INTO `component_func` (`id`, `code`, `name`, `func_group_id`, `icon`, `is_disabled`, `disabled_reason`, `url`, `permissions`, `type_dict_id`, `active_name`, `is_show`, `component_of`, `remark`, `seq`, `level`, `parent_id`, `parent_id1`, `parent_id2`, `parent_id3`, `parent_id4`, `parent_id5`, `parent_id6`, `parent_id7`, `parent_id8`, `parent_id9`, `parent_id10`, `version`, `tenant_id`, `create_at`, `create_by`, `update_at`, `update_by`) VALUES (1645788053739454465, 'tenant_manage_page', '租户管理', 1, 'UserFilled', 0, NULL, '/admin/tenantManagePage', NULL, 22, NULL, 1,'tenant', NULL, 10, 2, 1645787686251315201, 1645787686251315201, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 1, '2023-04-11 21:57:04', 1, NULL, NULL);
-- 租户管理权限按钮
INSERT INTO `component_func` (`id`, `code`, `name`, `func_group_id`, `icon`, `is_disabled`, `disabled_reason`, `url`, `permissions`, `type_dict_id`, `active_name`, `is_show`, `component_of`, `remark`, `seq`, `level`, `parent_id`, `parent_id1`, `parent_id2`, `parent_id3`, `parent_id4`, `parent_id5`, `parent_id6`, `parent_id7`, `parent_id8`, `parent_id9`, `parent_id10`, `version`, `tenant_id`, `create_at`, `create_by`, `update_at`, `update_by`) VALUES (1645799575635165186, 'tenant_manage_page_query', '租户管理查询', 1, 'Link', 0, NULL, NULL, 'admin:web:tenant:pageQuery', 1605497232817737729, NULL, 1,'tenant', NULL, 10, 3, 1645788053739454465, 1645787686251315201, 1645788053739454465, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 1, '2023-04-11 22:42:51', 1, NULL, NULL);
INSERT INTO `component_func` (`id`, `code`, `name`, `func_group_id`, `icon`, `is_disabled`, `disabled_reason`, `url`, `permissions`, `type_dict_id`, `active_name`, `is_show`, `component_of`, `remark`, `seq`, `level`, `parent_id`, `parent_id1`, `parent_id2`, `parent_id3`, `parent_id4`, `parent_id5`, `parent_id6`, `parent_id7`, `parent_id8`, `parent_id9`, `parent_id10`, `version`, `tenant_id`, `create_at`, `create_by`, `update_at`, `update_by`) VALUES (1645799576612438018, 'tenant_manage_page_create', '租户管理添加', 1, 'Link', 0, NULL, NULL, 'admin:web:tenant:create', 1605497232817737729, NULL, 1,'tenant', NULL, 10, 3, 1645788053739454465, 1645787686251315201, 1645788053739454465, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 1, '2023-04-11 22:42:51', 1, NULL, NULL);
INSERT INTO `component_func` (`id`, `code`, `name`, `func_group_id`, `icon`, `is_disabled`, `disabled_reason`, `url`, `permissions`, `type_dict_id`, `active_name`, `is_show`, `component_of`, `remark`, `seq`, `level`, `parent_id`, `parent_id1`, `parent_id2`, `parent_id3`, `parent_id4`, `parent_id5`, `parent_id6`, `parent_id7`, `parent_id8`, `parent_id9`, `parent_id10`, `version`, `tenant_id`, `create_at`, `create_by`, `update_at`, `update_by`) VALUES (1645799577883312129, 'tenant_manage_page_delete', '租户管理删除', 1, 'Link', 0, NULL, NULL, 'admin:web:tenant:delete', 1605497232817737729, NULL, 1,'tenant', NULL, 10, 3, 1645788053739454465, 1645787686251315201, 1645788053739454465, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 1, '2023-04-11 22:42:52', 1, NULL, NULL);
INSERT INTO `component_func` (`id`, `code`, `name`, `func_group_id`, `icon`, `is_disabled`, `disabled_reason`, `url`, `permissions`, `type_dict_id`, `active_name`, `is_show`, `component_of`, `remark`, `seq`, `level`, `parent_id`, `parent_id1`, `parent_id2`, `parent_id3`, `parent_id4`, `parent_id5`, `parent_id6`, `parent_id7`, `parent_id8`, `parent_id9`, `parent_id10`, `version`, `tenant_id`, `create_at`, `create_by`, `update_at`, `update_by`) VALUES (1645799578822836225, 'tenant_manage_page_update', '租户管理修改', 1, 'Link', 0, NULL, NULL, 'admin:web:tenant:update', 1605497232817737729, NULL, 1,'tenant', NULL, 10, 3, 1645788053739454465, 1645787686251315201, 1645788053739454465, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 1, '2023-04-11 22:42:52', 1, NULL, NULL);
INSERT INTO `component_func` (`id`, `code`, `name`, `func_group_id`, `icon`, `is_disabled`, `disabled_reason`, `url`, `permissions`, `type_dict_id`, `active_name`, `is_show`, `component_of`, `remark`, `seq`, `level`, `parent_id`, `parent_id1`, `parent_id2`, `parent_id3`, `parent_id4`, `parent_id5`, `parent_id6`, `parent_id7`, `parent_id8`, `parent_id9`, `parent_id10`, `version`, `tenant_id`, `create_at`, `create_by`, `update_at`, `update_by`) VALUES (1648890480508338177, 'tenant_manage_tenantAssignFuncApplication', '租户分配功能应用', 1, 'Link', 0, NULL, NULL, 'admin:web:tenantFuncApplication:tenantAssignFuncApplication,admin:web:tenantFuncApplication:queryFuncApplicationIdsByTenantId,admin:web:funcApplication:queryList', 1605497232817737729, NULL, 1, 'tenant', NULL, 10, 3, 1645788053739454465, 1645787686251315201, 1645788053739454465, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, '2023-04-20 11:25:00', 1, NULL, NULL);

-- 租户用户管理页面
INSERT INTO `component_func` (`id`, `code`, `name`, `func_group_id`, `icon`, `is_disabled`, `disabled_reason`, `url`, `permissions`, `type_dict_id`, `active_name`, `is_show`, `component_of`, `remark`, `seq`, `level`, `parent_id`, `parent_id1`, `parent_id2`, `parent_id3`, `parent_id4`, `parent_id5`, `parent_id6`, `parent_id7`, `parent_id8`, `parent_id9`, `parent_id10`, `version`, `tenant_id`, `create_at`, `create_by`, `update_at`, `update_by`) VALUES (1648166945654059010, 'tenant_user_manage_page', '租户用户管理', 1, 'User', 0, NULL, '/admin/tenantUserManagePage', NULL, 22, NULL, 1, 'tenant', NULL, 10, 2, 1645787686251315201, 1645787686251315201, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, '2023-04-18 11:29:56', 1, NULL, NULL);
-- 租户用户管理按钮
INSERT INTO `component_func` (`id`, `code`, `name`, `func_group_id`, `icon`, `is_disabled`, `disabled_reason`, `url`, `permissions`, `type_dict_id`, `active_name`, `is_show`, `component_of`, `remark`, `seq`, `level`, `parent_id`, `parent_id1`, `parent_id2`, `parent_id3`, `parent_id4`, `parent_id5`, `parent_id6`, `parent_id7`, `parent_id8`, `parent_id9`, `parent_id10`, `version`, `tenant_id`, `create_at`, `create_by`, `update_at`, `update_by`) VALUES (1648167139187634178, 'tenant_user_manage_page_query', '租户用户管理查询', 1, 'Link', 0, NULL, NULL, 'adminTenantUserManagePage,admin:web:tenantUser:pageQuery,adminUserManagePage,admin:web:user:pageQuery', 1605497232817737729, NULL, 1, 'tenant', NULL, 10, 3, 1648166945654059010, 1645787686251315201, 1648166945654059010, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, '2023-04-18 11:30:42', 1, NULL, NULL);
INSERT INTO `component_func` (`id`, `code`, `name`, `func_group_id`, `icon`, `is_disabled`, `disabled_reason`, `url`, `permissions`, `type_dict_id`, `active_name`, `is_show`, `component_of`, `remark`, `seq`, `level`, `parent_id`, `parent_id1`, `parent_id2`, `parent_id3`, `parent_id4`, `parent_id5`, `parent_id6`, `parent_id7`, `parent_id8`, `parent_id9`, `parent_id10`, `version`, `tenant_id`, `create_at`, `create_by`, `update_at`, `update_by`) VALUES (1648167140705972226, 'tenant_user_manage_page_create', '租户用户管理添加', 1, 'Link', 0, NULL, NULL, 'adminTenantUserManageAdd,admin:web:tenantUser:create,adminUserManagePage,admin:web:user:pageQuery', 1605497232817737729, NULL, 1, 'tenant', NULL, 10, 3, 1648166945654059010, 1645787686251315201, 1648166945654059010, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, '2023-04-18 11:30:42', 1, NULL, NULL);
INSERT INTO `component_func` (`id`, `code`, `name`, `func_group_id`, `icon`, `is_disabled`, `disabled_reason`, `url`, `permissions`, `type_dict_id`, `active_name`, `is_show`, `component_of`, `remark`, `seq`, `level`, `parent_id`, `parent_id1`, `parent_id2`, `parent_id3`, `parent_id4`, `parent_id5`, `parent_id6`, `parent_id7`, `parent_id8`, `parent_id9`, `parent_id10`, `version`, `tenant_id`, `create_at`, `create_by`, `update_at`, `update_by`) VALUES (1648167141960069122, 'tenant_user_manage_page_delete', '租户用户管理删除', 1, 'Link', 0, NULL, NULL, 'admin:web:tenantUser:delete', 1605497232817737729, NULL, 1, 'tenant', NULL, 10, 3, 1648166945654059010, 1645787686251315201, 1648166945654059010, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, '2023-04-18 11:30:43', 1, NULL, NULL);
INSERT INTO `component_func` (`id`, `code`, `name`, `func_group_id`, `icon`, `is_disabled`, `disabled_reason`, `url`, `permissions`, `type_dict_id`, `active_name`, `is_show`, `component_of`, `remark`, `seq`, `level`, `parent_id`, `parent_id1`, `parent_id2`, `parent_id3`, `parent_id4`, `parent_id5`, `parent_id6`, `parent_id7`, `parent_id8`, `parent_id9`, `parent_id10`, `version`, `tenant_id`, `create_at`, `create_by`, `update_at`, `update_by`) VALUES (1648167143260303362, 'tenant_user_manage_page_update', '租户用户管理修改', 1, 'Link', 0, NULL, NULL, 'adminTenantUserManageUpdate,admin:web:tenantUser:update,adminUserManagePage,admin:web:user:pageQuery', 1605497232817737729, NULL, 1, 'tenant', NULL, 10, 3, 1648166945654059010, 1645787686251315201, 1648166945654059010, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, '2023-04-18 11:30:43', 1, NULL, NULL);

-- 租户应用管理页面
INSERT INTO `component_func` (`id`, `code`, `name`, `func_group_id`, `icon`, `is_disabled`, `disabled_reason`, `url`, `permissions`, `type_dict_id`, `active_name`, `is_show`, `component_of`, `remark`, `seq`, `level`, `parent_id`, `parent_id1`, `parent_id2`, `parent_id3`, `parent_id4`, `parent_id5`, `parent_id6`, `parent_id7`, `parent_id8`, `parent_id9`, `parent_id10`, `version`, `tenant_id`, `create_at`, `create_by`, `update_at`, `update_by`) VALUES (1648203443929726978, 'tenant_application_manage_page', '租户功能应用管理', 1, 'Grid', 0, NULL, '/admin/tenantFuncApplicationManagePage', NULL, 22, NULL, 1, 'tenant', NULL, 10, 2, 1645787686251315201, 1645787686251315201, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, '2023-04-18 13:54:58', 1, NULL, NULL);
-- 租户应用管理按钮
INSERT INTO `component_func` (`id`, `code`, `name`, `func_group_id`, `icon`, `is_disabled`, `disabled_reason`, `url`, `permissions`, `type_dict_id`, `active_name`, `is_show`, `component_of`, `remark`, `seq`, `level`, `parent_id`, `parent_id1`, `parent_id2`, `parent_id3`, `parent_id4`, `parent_id5`, `parent_id6`, `parent_id7`, `parent_id8`, `parent_id9`, `parent_id10`, `version`, `tenant_id`, `create_at`, `create_by`, `update_at`, `update_by`) VALUES (1648204179426099201, 'tenant_application_manage_page_query', '租户功能应用管理查询', 1, 'Link', 0, NULL, NULL, 'adminTenantFuncApplicationManagePage,admin:web:tenantFuncApplication:pageQuery,admin:web:funcApplication:queryList', 1605497232817737729, NULL, 1, 'tenant', NULL, 10, 3, 1648203443929726978, 1645787686251315201, 1648203443929726978, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, '2023-04-18 13:57:53', 1, NULL, NULL);
INSERT INTO `component_func` (`id`, `code`, `name`, `func_group_id`, `icon`, `is_disabled`, `disabled_reason`, `url`, `permissions`, `type_dict_id`, `active_name`, `is_show`, `component_of`, `remark`, `seq`, `level`, `parent_id`, `parent_id1`, `parent_id2`, `parent_id3`, `parent_id4`, `parent_id5`, `parent_id6`, `parent_id7`, `parent_id8`, `parent_id9`, `parent_id10`, `version`, `tenant_id`, `create_at`, `create_by`, `update_at`, `update_by`) VALUES (1648204180273348609, 'tenant_application_manage_page_create', '租户功能应用管理添加', 1, 'Link', 0, NULL, NULL, 'adminTenantFuncApplicationManageAdd,admin:web:tenantFuncApplication:create,admin:web:funcApplication:queryList', 1605497232817737729, NULL, 1, 'tenant', NULL, 10, 3, 1648203443929726978, 1645787686251315201, 1648203443929726978, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, '2023-04-18 13:57:53', 1, NULL, NULL);
INSERT INTO `component_func` (`id`, `code`, `name`, `func_group_id`, `icon`, `is_disabled`, `disabled_reason`, `url`, `permissions`, `type_dict_id`, `active_name`, `is_show`, `component_of`, `remark`, `seq`, `level`, `parent_id`, `parent_id1`, `parent_id2`, `parent_id3`, `parent_id4`, `parent_id5`, `parent_id6`, `parent_id7`, `parent_id8`, `parent_id9`, `parent_id10`, `version`, `tenant_id`, `create_at`, `create_by`, `update_at`, `update_by`) VALUES (1648204181082849282, 'tenant_application_manage_page_delete', '租户功能应用管理删除', 1, 'Link', 0, NULL, NULL, 'admin:web:tenantFuncApplication:delete', 1605497232817737729, NULL, 1, 'tenant', NULL, 10, 3, 1648203443929726978, 1645787686251315201, 1648203443929726978, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, '2023-04-18 13:57:54', 1, NULL, NULL);
INSERT INTO `component_func` (`id`, `code`, `name`, `func_group_id`, `icon`, `is_disabled`, `disabled_reason`, `url`, `permissions`, `type_dict_id`, `active_name`, `is_show`, `component_of`, `remark`, `seq`, `level`, `parent_id`, `parent_id1`, `parent_id2`, `parent_id3`, `parent_id4`, `parent_id5`, `parent_id6`, `parent_id7`, `parent_id8`, `parent_id9`, `parent_id10`, `version`, `tenant_id`, `create_at`, `create_by`, `update_at`, `update_by`) VALUES (1648204181955264513, 'tenant_application_manage_page_update', '租户功能应用管理修改', 1, 'Link', 0, NULL, NULL, 'adminTenantFuncApplicationManageUpdate,admin:web:tenantFuncApplication:update,admin:web:funcApplication:queryList', 1605497232817737729, NULL, 1, 'tenant', NULL, 10, 3, 1648203443929726978, 1645787686251315201, 1648203443929726978, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, '2023-04-18 13:57:54', 1, NULL, NULL);
INSERT INTO `component_func` (`id`, `code`, `name`, `func_group_id`, `icon`, `is_disabled`, `disabled_reason`, `url`, `permissions`, `type_dict_id`, `active_name`, `is_show`, `component_of`, `remark`, `seq`, `level`, `parent_id`, `parent_id1`, `parent_id2`, `parent_id3`, `parent_id4`, `parent_id5`, `parent_id6`, `parent_id7`, `parent_id8`, `parent_id9`, `parent_id10`, `version`, `tenant_id`, `create_at`, `create_by`, `update_at`, `update_by`) VALUES (1648890983296335874, 'tenant_application_manage_tenantAssignFunc', '租户应用分配功能菜单', 1, 'Link', 0, NULL, NULL, 'admin:web:tenantFunc:tenantAssignFunc,admin:web:tenantFunc:queryFuncIdsByTenantId,admin:web:func:queryList', 1605497232817737729, NULL, 1, 'tenant', NULL, 10, 3, 1648203443929726978, 1645787686251315201, 1648203443929726978, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, '2023-04-20 11:27:00', 1, NULL, NULL);

-- 租户功能菜单管理页面
INSERT INTO `component_func` (`id`, `code`, `name`, `func_group_id`, `icon`, `is_disabled`, `disabled_reason`, `url`, `permissions`, `type_dict_id`, `active_name`, `is_show`, `component_of`, `remark`, `seq`, `level`, `parent_id`, `parent_id1`, `parent_id2`, `parent_id3`, `parent_id4`, `parent_id5`, `parent_id6`, `parent_id7`, `parent_id8`, `parent_id9`, `parent_id10`, `version`, `tenant_id`, `create_at`, `create_by`, `update_at`, `update_by`) VALUES (1648230510922625025, 'tenant_func_manage_page', '租户功能菜单管理', 1, 'Link', 0, NULL, '/admin/tenantFuncManagePage', NULL, 22, NULL, 1, 'tenant', NULL, 10, 2, 1645787686251315201, 1645787686251315201, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, '2023-04-18 15:42:31', 1, NULL, NULL);
-- 租户功能菜单管理按钮
INSERT INTO `component_func` (`id`, `code`, `name`, `func_group_id`, `icon`, `is_disabled`, `disabled_reason`, `url`, `permissions`, `type_dict_id`, `active_name`, `is_show`, `component_of`, `remark`, `seq`, `level`, `parent_id`, `parent_id1`, `parent_id2`, `parent_id3`, `parent_id4`, `parent_id5`, `parent_id6`, `parent_id7`, `parent_id8`, `parent_id9`, `parent_id10`, `version`, `tenant_id`, `create_at`, `create_by`, `update_at`, `update_by`) VALUES (1648230660940296193, 'tenant_func_manage_page_query', '租户功能菜单管理查询', 1, 'Link', 0, NULL, NULL, 'adminTenantFuncManagePage,admin:web:tenantFunc:pageQuery', 1605497232817737729, NULL, 1, 'tenant', NULL, 10, 3, 1648230510922625025, 1645787686251315201, 1648230510922625025, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, '2023-04-18 15:43:07', 1, NULL, NULL);
INSERT INTO `component_func` (`id`, `code`, `name`, `func_group_id`, `icon`, `is_disabled`, `disabled_reason`, `url`, `permissions`, `type_dict_id`, `active_name`, `is_show`, `component_of`, `remark`, `seq`, `level`, `parent_id`, `parent_id1`, `parent_id2`, `parent_id3`, `parent_id4`, `parent_id5`, `parent_id6`, `parent_id7`, `parent_id8`, `parent_id9`, `parent_id10`, `version`, `tenant_id`, `create_at`, `create_by`, `update_at`, `update_by`) VALUES (1648230662118895617, 'tenant_func_manage_page_create', '租户功能菜单管理添加', 1, 'Link', 0, NULL, NULL, 'adminTenantFuncManageAdd,admin:web:tenantFunc:create', 1605497232817737729, NULL, 1, 'tenant', NULL, 10, 3, 1648230510922625025, 1645787686251315201, 1648230510922625025, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, '2023-04-18 15:43:07', 1, NULL, NULL);
INSERT INTO `component_func` (`id`, `code`, `name`, `func_group_id`, `icon`, `is_disabled`, `disabled_reason`, `url`, `permissions`, `type_dict_id`, `active_name`, `is_show`, `component_of`, `remark`, `seq`, `level`, `parent_id`, `parent_id1`, `parent_id2`, `parent_id3`, `parent_id4`, `parent_id5`, `parent_id6`, `parent_id7`, `parent_id8`, `parent_id9`, `parent_id10`, `version`, `tenant_id`, `create_at`, `create_by`, `update_at`, `update_by`) VALUES (1648230663096168449, 'tenant_func_manage_page_delete', '租户功能菜单管理删除', 1, 'Link', 0, NULL, NULL, 'admin:web:tenantFunc:delete', 1605497232817737729, NULL, 1, 'tenant', NULL, 10, 3, 1648230510922625025, 1645787686251315201, 1648230510922625025, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, '2023-04-18 15:43:07', 1, NULL, NULL);
INSERT INTO `component_func` (`id`, `code`, `name`, `func_group_id`, `icon`, `is_disabled`, `disabled_reason`, `url`, `permissions`, `type_dict_id`, `active_name`, `is_show`, `component_of`, `remark`, `seq`, `level`, `parent_id`, `parent_id1`, `parent_id2`, `parent_id3`, `parent_id4`, `parent_id5`, `parent_id6`, `parent_id7`, `parent_id8`, `parent_id9`, `parent_id10`, `version`, `tenant_id`, `create_at`, `create_by`, `update_at`, `update_by`) VALUES (1648230664174104577, 'tenant_func_manage_page_update', '租户功能菜单管理修改', 1, 'Link', 0, NULL, NULL, 'adminTenantFuncManageUpdate,admin:web:tenantFunc:update', 1605497232817737729, NULL, 1, 'tenant', NULL, 10, 3, 1648230510922625025, 1645787686251315201, 1648230510922625025, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, '2023-04-18 15:43:08', 1, NULL, NULL);
-- 租户创建申请管理页面
INSERT INTO `component_func` (`id`, `code`, `name`, `func_group_id`, `icon`, `is_disabled`, `disabled_reason`, `url`, `permissions`, `type_dict_id`, `active_name`, `is_show`, `component_of`, `remark`, `seq`, `level`, `parent_id`, `parent_id1`, `parent_id2`, `parent_id3`, `parent_id4`, `parent_id5`, `parent_id6`, `parent_id7`, `parent_id8`, `parent_id9`, `parent_id10`, `version`, `tenant_id`, `create_at`, `create_by`, `update_at`, `update_by`) VALUES (1648908056047190017, 'tenant_create_apply_manage', '租户创建申请管理', 1, 'Avatar', 0, NULL, '/admin/tenantCreateApplyManagePage', NULL, 22, NULL, 1, 'tenant', NULL, 10, 2, 1645787686251315201, 1645787686251315201, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, '2023-04-20 12:34:50', 1, NULL, NULL);
-- 租户创建申请管理按钮
INSERT INTO `component_func` (`id`, `code`, `name`, `func_group_id`, `icon`, `is_disabled`, `disabled_reason`, `url`, `permissions`, `type_dict_id`, `active_name`, `is_show`, `component_of`, `remark`, `seq`, `level`, `parent_id`, `parent_id1`, `parent_id2`, `parent_id3`, `parent_id4`, `parent_id5`, `parent_id6`, `parent_id7`, `parent_id8`, `parent_id9`, `parent_id10`, `version`, `tenant_id`, `create_at`, `create_by`, `update_at`, `update_by`) VALUES (1648908293071503361, 'tenant_create_apply_manage_query', '租户创建申请管理查询', 1, 'Link', 0, NULL, NULL, 'adminTenantCreateApplyManagePage,admin:web:tenantCreateApply:pageQuery', 1605497232817737729, NULL, 1, 'tenant', NULL, 10, 3, 1648908056047190017, 1645787686251315201, 1648908056047190017, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, '2023-04-20 12:35:47', 1, NULL, NULL);
INSERT INTO `component_func` (`id`, `code`, `name`, `func_group_id`, `icon`, `is_disabled`, `disabled_reason`, `url`, `permissions`, `type_dict_id`, `active_name`, `is_show`, `component_of`, `remark`, `seq`, `level`, `parent_id`, `parent_id1`, `parent_id2`, `parent_id3`, `parent_id4`, `parent_id5`, `parent_id6`, `parent_id7`, `parent_id8`, `parent_id9`, `parent_id10`, `version`, `tenant_id`, `create_at`, `create_by`, `update_at`, `update_by`) VALUES (1648908294191382530, 'tenant_create_apply_manage_create', '租户创建申请管理添加', 1, 'Link', 0, NULL, NULL, 'adminTenantCreateApplyManageAdd,admin:web:tenantCreateApply:create', 1605497232817737729, NULL, 1, 'tenant', NULL, 10, 3, 1648908056047190017, 1645787686251315201, 1648908056047190017, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, '2023-04-20 12:35:47', 1, NULL, NULL);
INSERT INTO `component_func` (`id`, `code`, `name`, `func_group_id`, `icon`, `is_disabled`, `disabled_reason`, `url`, `permissions`, `type_dict_id`, `active_name`, `is_show`, `component_of`, `remark`, `seq`, `level`, `parent_id`, `parent_id1`, `parent_id2`, `parent_id3`, `parent_id4`, `parent_id5`, `parent_id6`, `parent_id7`, `parent_id8`, `parent_id9`, `parent_id10`, `version`, `tenant_id`, `create_at`, `create_by`, `update_at`, `update_by`) VALUES (1648908296359837697, 'tenant_create_apply_manage_delete', '租户创建申请管理删除', 1, 'Link', 0, NULL, NULL, 'admin:web:tenantCreateApply:delete', 1605497232817737729, NULL, 1, 'tenant', NULL, 10, 3, 1648908056047190017, 1645787686251315201, 1648908056047190017, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, '2023-04-20 12:35:48', 1, NULL, NULL);
INSERT INTO `component_func` (`id`, `code`, `name`, `func_group_id`, `icon`, `is_disabled`, `disabled_reason`, `url`, `permissions`, `type_dict_id`, `active_name`, `is_show`, `component_of`, `remark`, `seq`, `level`, `parent_id`, `parent_id1`, `parent_id2`, `parent_id3`, `parent_id4`, `parent_id5`, `parent_id6`, `parent_id7`, `parent_id8`, `parent_id9`, `parent_id10`, `version`, `tenant_id`, `create_at`, `create_by`, `update_at`, `update_by`) VALUES (1648908297727180801, 'tenant_create_apply_manage_update', '租户创建申请管理修改', 1, 'Link', 0, NULL, NULL, 'adminTenantCreateApplyManageUpdate,admin:web:tenantCreateApply:update', 1605497232817737729, NULL, 1, 'tenant', NULL, 10, 3, 1648908056047190017, 1645787686251315201, 1648908056047190017, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, '2023-04-20 12:35:48', 1, NULL, NULL);