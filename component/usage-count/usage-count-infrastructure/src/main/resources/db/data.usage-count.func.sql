-- 使用次数管理 根菜单
INSERT INTO component_func (id, code, name, func_group_id, icon, is_disabled, disabled_reason, url, permissions, type_dict_id, active_name, is_show, component_of, remark, seq, level, parent_id, parent_id1, parent_id2, parent_id3, parent_id4, parent_id5, parent_id6, parent_id7, parent_id8, parent_id9, parent_id10, version, tenant_id, create_at, create_by, update_at, update_by) VALUES (1714940111231201281, 'usage_count_manage', '使用次数管理', 1, 'Scissor', 0, null, null, null, 21, null, 1, 'usage-count', null, 10, 1, null, null, null, null, null, null, null, null, null, null, null, 1, null, '2023-10-19 17:42:39', 1, null, null);
-- 使用次数定义管理 页面
INSERT INTO component_func (id, code, name, func_group_id, icon, is_disabled, disabled_reason, url, permissions, type_dict_id, active_name, is_show, component_of, remark, seq, level, parent_id, parent_id1, parent_id2, parent_id3, parent_id4, parent_id5, parent_id6, parent_id7, parent_id8, parent_id9, parent_id10, version, tenant_id, create_at, create_by, update_at, update_by) VALUES (1714940826309701633, 'usage_count_define_page', '使用次数定义管理', 1, 'Paperclip', 0, null, '/admin/usageCountDefineManagePage', null, 22, null, 1, 'usage-count', null, 10, 2, 1714940111231201281, 1714940111231201281, null, null, null, null, null, null, null, null, null, 1, null, '2023-10-19 17:45:30', 1, null, null);
INSERT INTO component_func (id, code, name, func_group_id, icon, is_disabled, disabled_reason, url, permissions, type_dict_id, active_name, is_show, component_of, remark, seq, level, parent_id, parent_id1, parent_id2, parent_id3, parent_id4, parent_id5, parent_id6, parent_id7, parent_id8, parent_id9, parent_id10, version, tenant_id, create_at, create_by, update_at, update_by) VALUES (1714940991103905793, 'usage_count_define_page_query', '使用次数定义管理查询', 1, 'Link', 0, null, null, 'adminUsageCountDefineManagePage,admin:web:usageCountDefine:pageQuery', 1605497232817737729, null, 1, 'usage-count', null, 10, 3, 1714940826309701633, 1714940111231201281, 1714940826309701633, null, null, null, null, null, null, null, null, 1, null, '2023-10-19 17:46:09', 1, null, null);
INSERT INTO component_func (id, code, name, func_group_id, icon, is_disabled, disabled_reason, url, permissions, type_dict_id, active_name, is_show, component_of, remark, seq, level, parent_id, parent_id1, parent_id2, parent_id3, parent_id4, parent_id5, parent_id6, parent_id7, parent_id8, parent_id9, parent_id10, version, tenant_id, create_at, create_by, update_at, update_by) VALUES (1714940991653359617, 'usage_count_define_page_create', '使用次数定义管理添加', 1, 'Link', 0, null, null, 'adminUsageCountDefineManageAdd,admin:web:usageCountDefine:create', 1605497232817737729, null, 1, 'usage-count', null, 10, 3, 1714940826309701633, 1714940111231201281, 1714940826309701633, null, null, null, null, null, null, null, null, 1, null, '2023-10-19 17:46:09', 1, null, null);
INSERT INTO component_func (id, code, name, func_group_id, icon, is_disabled, disabled_reason, url, permissions, type_dict_id, active_name, is_show, component_of, remark, seq, level, parent_id, parent_id1, parent_id2, parent_id3, parent_id4, parent_id5, parent_id6, parent_id7, parent_id8, parent_id9, parent_id10, version, tenant_id, create_at, create_by, update_at, update_by) VALUES (1714940992341225474, 'usage_count_define_page_delete', '使用次数定义管理删除', 1, 'Link', 0, null, null, 'admin:web:usageCountDefine:delete', 1605497232817737729, null, 1, 'usage-count', null, 10, 3, 1714940826309701633, 1714940111231201281, 1714940826309701633, null, null, null, null, null, null, null, null, 1, null, '2023-10-19 17:46:09', 1, null, null);
INSERT INTO component_func (id, code, name, func_group_id, icon, is_disabled, disabled_reason, url, permissions, type_dict_id, active_name, is_show, component_of, remark, seq, level, parent_id, parent_id1, parent_id2, parent_id3, parent_id4, parent_id5, parent_id6, parent_id7, parent_id8, parent_id9, parent_id10, version, tenant_id, create_at, create_by, update_at, update_by) VALUES (1714940992861319169, 'usage_count_define_page_update', '使用次数定义管理修改', 1, 'Link', 0, null, null, 'adminUsageCountDefineManageUpdate,admin:web:usageCountDefine:update', 1605497232817737729, null, 1, 'usage-count', null, 10, 3, 1714940826309701633, 1714940111231201281, 1714940826309701633, null, null, null, null, null, null, null, null, 1, null, '2023-10-19 17:46:09', 1, null, null);
-- 使用次数配置管理 页面
INSERT INTO component_func (id, code, name, func_group_id, icon, is_disabled, disabled_reason, url, permissions, type_dict_id, active_name, is_show, component_of, remark, seq, level, parent_id, parent_id1, parent_id2, parent_id3, parent_id4, parent_id5, parent_id6, parent_id7, parent_id8, parent_id9, parent_id10, version, tenant_id, create_at, create_by, update_at, update_by) VALUES (1714941254296481793, 'usage_count_config_page', '使用次数配置管理', 1, 'Tools', 0, null, '/admin/usageCountConfigManagePage', null, 22, null, 1, 'usage-count', null, 10, 2, 1714940111231201281, 1714940111231201281, null, null, null, null, null, null, null, null, null, 1, null, '2023-10-19 17:47:12', 1, null, null);
INSERT INTO component_func (id, code, name, func_group_id, icon, is_disabled, disabled_reason, url, permissions, type_dict_id, active_name, is_show, component_of, remark, seq, level, parent_id, parent_id1, parent_id2, parent_id3, parent_id4, parent_id5, parent_id6, parent_id7, parent_id8, parent_id9, parent_id10, version, tenant_id, create_at, create_by, update_at, update_by) VALUES (1714941420239925250, 'usage_count_config_page_query', '使用次数配置管理查询', 1, 'Link', 0, null, null, 'adminUsageCountConfigManagePage,admin:web:usageCountConfig:pageQuery,admin:web:usageCountDefine:queryList,admin:web:tenant:queryList', 1605497232817737729, null, 1, 'usage-count', null, 10, 3, 1714941254296481793, 1714940111231201281, 1714941254296481793, null, null, null, null, null, null, null, null, 1, null, '2023-10-19 17:47:51', 1, null, null);
INSERT INTO component_func (id, code, name, func_group_id, icon, is_disabled, disabled_reason, url, permissions, type_dict_id, active_name, is_show, component_of, remark, seq, level, parent_id, parent_id1, parent_id2, parent_id3, parent_id4, parent_id5, parent_id6, parent_id7, parent_id8, parent_id9, parent_id10, version, tenant_id, create_at, create_by, update_at, update_by) VALUES (1714941420638384130, 'usage_count_config_page_create', '使用次数配置管理添加', 1, 'Link', 0, null, null, 'adminUsageCountConfigManageAdd,admin:web:usageCountConfig:create,admin:web:usageCountDefine:queryList,admin:web:tenant:queryList', 1605497232817737729, null, 1, 'usage-count', null, 10, 3, 1714941254296481793, 1714940111231201281, 1714941254296481793, null, null, null, null, null, null, null, null, 1, null, '2023-10-19 17:47:51', 1, null, null);
INSERT INTO component_func (id, code, name, func_group_id, icon, is_disabled, disabled_reason, url, permissions, type_dict_id, active_name, is_show, component_of, remark, seq, level, parent_id, parent_id1, parent_id2, parent_id3, parent_id4, parent_id5, parent_id6, parent_id7, parent_id8, parent_id9, parent_id10, version, tenant_id, create_at, create_by, update_at, update_by) VALUES (1714941421028454402, 'usage_count_config_page_delete', '使用次数配置管理删除', 1, 'Link', 0, null, null, 'admin:web:usageCountConfig:delete', 1605497232817737729, null, 1, 'usage-count', null, 10, 3, 1714941254296481793, 1714940111231201281, 1714941254296481793, null, null, null, null, null, null, null, null, 1, null, '2023-10-19 17:47:52', 1, null, null);
INSERT INTO component_func (id, code, name, func_group_id, icon, is_disabled, disabled_reason, url, permissions, type_dict_id, active_name, is_show, component_of, remark, seq, level, parent_id, parent_id1, parent_id2, parent_id3, parent_id4, parent_id5, parent_id6, parent_id7, parent_id8, parent_id9, parent_id10, version, tenant_id, create_at, create_by, update_at, update_by) VALUES (1714941421401747458, 'usage_count_config_page_update', '使用次数配置管理修改', 1, 'Link', 0, null, null, 'adminUsageCountConfigManageUpdate,admin:web:usageCountConfig:update,admin:web:usageCountDefine:queryList,admin:web:tenant:queryList', 1605497232817737729, null, 1, 'usage-count', null, 10, 3, 1714941254296481793, 1714940111231201281, 1714941254296481793, null, null, null, null, null, null, null, null, 1, null, '2023-10-19 17:47:52', 1, null, null);
-- 使用次数记录管理 页面
INSERT INTO component_func (id, code, name, func_group_id, icon, is_disabled, disabled_reason, url, permissions, type_dict_id, active_name, is_show, component_of, remark, seq, level, parent_id, parent_id1, parent_id2, parent_id3, parent_id4, parent_id5, parent_id6, parent_id7, parent_id8, parent_id9, parent_id10, version, tenant_id, create_at, create_by, update_at, update_by) VALUES (1714941802739478530, 'usage_count_record_page', '使用次数记录管理', 1, 'Collection', 0, null, '/admin/usageCountRecordManagePage', null, 22, null, 1, 'usage-count', null, 10, 2, 1714940111231201281, 1714940111231201281, null, null, null, null, null, null, null, null, null, 1, null, '2023-10-19 17:49:23', 1, null, null);
INSERT INTO component_func (id, code, name, func_group_id, icon, is_disabled, disabled_reason, url, permissions, type_dict_id, active_name, is_show, component_of, remark, seq, level, parent_id, parent_id1, parent_id2, parent_id3, parent_id4, parent_id5, parent_id6, parent_id7, parent_id8, parent_id9, parent_id10, version, tenant_id, create_at, create_by, update_at, update_by) VALUES (1714941945270317057, 'usage_count_record_page_query', '使用次数记录管理查询', 1, 'Link', 0, null, null, 'adminUsageCountRecordManagePage,admin:web:usageCountRecord:pageQuery,admin:web:usageCountDefine:queryList,admin:web:usageCountConfig:queryList,admin:web:user:queryList,admin:web:tenant:queryList', 1605497232817737729, null, 1, 'usage-count', null, 10, 3, 1714941802739478530, 1714940111231201281, 1714941802739478530, null, null, null, null, null, null, null, null, 1, null, '2023-10-19 17:49:56', 1, null, null);
INSERT INTO component_func (id, code, name, func_group_id, icon, is_disabled, disabled_reason, url, permissions, type_dict_id, active_name, is_show, component_of, remark, seq, level, parent_id, parent_id1, parent_id2, parent_id3, parent_id4, parent_id5, parent_id6, parent_id7, parent_id8, parent_id9, parent_id10, version, tenant_id, create_at, create_by, update_at, update_by) VALUES (1714941945685553154, 'usage_count_record_page_delete', '使用次数记录管理删除', 1, 'Link', 0, null, null, 'admin:web:usageCountRecord:delete', 1605497232817737729, null, 1, 'usage-count', null, 10, 3, 1714941802739478530, 1714940111231201281, 1714941802739478530, null, null, null, null, null, null, null, null, 1, null, '2023-10-19 17:49:57', 1, null, null);
INSERT INTO component_func (id, code, name, func_group_id, icon, is_disabled, disabled_reason, url, permissions, type_dict_id, active_name, is_show, component_of, remark, seq, level, parent_id, parent_id1, parent_id2, parent_id3, parent_id4, parent_id5, parent_id6, parent_id7, parent_id8, parent_id9, parent_id10, version, tenant_id, create_at, create_by, update_at, update_by) VALUES (1717081583392935937, 'usage_count_record_detail_page_query', '使用次数记录明细', 1, 'Link', 0, null, null, 'admin:web:UsageCountRecordDetail:pageQuery,admin:web:usageCountDefine:queryList,admin:web:user:queryList,admin:web:tenant:queryList', 1605497232817737729, null, 1, 'usage-count', null, 10, 3, 1714941802739478530, 1714940111231201281, 1714941802739478530, null, null, null, null, null, null, null, null, 2, null, '2023-10-25 15:32:06', 1, '2023-10-25 15:35:33', 1);