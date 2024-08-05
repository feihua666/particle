-- import classpath:db/data.tenant.func.sql
-- import classpath:db/data.tenant.dict.sql
-- import classpath:db/data.tenant.message.sql

-- 平台根租户 id 为1
INSERT INTO `component_tenant` (`id`, `code`, `name`, `is_disabled`, `disabled_reason`, `user_name`, `email`, `mobile`, `tenant_domain`, `tenant_theme_json`, `tenant_default_route_json`, `tenant_logo_json`, `is_formal`, `user_limit_count`, `effective_at`, `expire_at`, `master_user_id`, `config_json`, `remark`, `level`, `parent_id`, `parent_id1`, `parent_id2`, `parent_id3`, `parent_id4`, `parent_id5`, `parent_id6`, `parent_id7`, `parent_id8`, `parent_id9`, `parent_id10`, `version`, `tenant_id`, `create_at`, `create_by`, `update_at`, `update_by`) VALUES (1, 'root_tenant', '租户平台运营', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, NULL, NULL, 1, NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 1, '2023-04-11 22:37:06', NULL, NULL, NULL);
-- 超级管理员为根租户的用户
INSERT INTO `component_tenant_user` (`id`, `user_id`, `name`, `is_expired`, `expired_reason`, `expire_at`, `is_leave`, `leave_reason`, `leave_at`, `join_at`, `is_formal`, `version`, `tenant_id`, `create_at`, `create_by`, `update_at`, `update_by`) VALUES (1, 1, '超级管理员', 0, NULL, NULL, 0, NULL, NULL, '2023-04-14 18:02:51', 1, 1, 1, '2023-04-14 18:02:51', NULL, NULL, NULL);

-- 虚拟超级管理员
INSERT INTO component_tenant_user (id, user_id, name, is_expired, expired_reason, expire_at, effective_at, effective_at_trigger_dict_id, effective_days, is_leave, leave_reason, leave_at, join_at, is_formal, remark, version, tenant_id, create_at, create_by, update_at, update_by) VALUES (1818122966571962370, 1818106787283390466, '虚拟超级管理员', 0, null, null, null, null, null, 0, null, null, '2024-07-30 11:14:29', 1, null, 2, 1, '2024-07-30 11:14:29', 1, '2024-07-30 11:14:39', 1);
