-- 数据范围应用字典，字典组
INSERT INTO component_dict (id, code, name, value, value_unit, is_system, is_public, is_group, is_disabled, disabled_reason, private_flag, private_flag_memo, group_flag, group_flag_memo, tags, remark, seq, level, parent_id, parent_id1, parent_id2, parent_id3, parent_id4, parent_id5, parent_id6, parent_id7, parent_id8, parent_id9, parent_id10, version, tenant_id, create_at, create_by, update_at, update_by) VALUES (1806576190169817090, 'data_constraint', '数据范围应用字典', '', null, 1, 1, 1, 0, null, null, null, null, null, null, null, 10, 1, null, null, null, null, null, null, null, null, null, null, null, 1, null, '2024-06-28 14:31:43', 1, '2024-06-28 14:31:43', null);
-- 数据范围自定义数据交互方式，字典组
INSERT INTO component_dict (id, code, name, value, value_unit, is_system, is_public, is_group, is_disabled, disabled_reason, private_flag, private_flag_memo, group_flag, group_flag_memo, tags, remark, seq, level, parent_id, parent_id1, parent_id2, parent_id3, parent_id4, parent_id5, parent_id6, parent_id7, parent_id8, parent_id9, parent_id10, version, tenant_id, create_at, create_by, update_at, update_by) VALUES (1806576637307789314, 'data_scope_custom_data_type', '数据范围自定义数据交互方式', '', null, 1, 1, 1, 0, null, null, null, null, null, null, null, 10, 2, 1806576190169817090, 1806576190169817090, null, null, null, null, null, null, null, null, null, 1, null, '2024-06-28 14:33:30', 1, '2024-06-28 14:33:30', null);
-- 数据范围自定义数据交互方式，字典项
INSERT INTO component_dict (id, code, name, value, value_unit, is_system, is_public, is_group, is_disabled, disabled_reason, private_flag, private_flag_memo, group_flag, group_flag_memo, tags, remark, seq, level, parent_id, parent_id1, parent_id2, parent_id3, parent_id4, parent_id5, parent_id6, parent_id7, parent_id8, parent_id9, parent_id10, version, tenant_id, create_at, create_by, update_at, update_by) VALUES (1806576936844009473, null, '表格列表', 'table_list', null, 1, 1, 0, 0, null, null, null, null, null, null, null, 10, 3, 1806576637307789314, 1806576190169817090, 1806576637307789314, null, null, null, null, null, null, null, null, 1, null, '2024-06-28 14:34:41', 1, '2024-06-28 14:34:41', null);
INSERT INTO component_dict (id, code, name, value, value_unit, is_system, is_public, is_group, is_disabled, disabled_reason, private_flag, private_flag_memo, group_flag, group_flag_memo, tags, remark, seq, level, parent_id, parent_id1, parent_id2, parent_id3, parent_id4, parent_id5, parent_id6, parent_id7, parent_id8, parent_id9, parent_id10, version, tenant_id, create_at, create_by, update_at, update_by) VALUES (1806577066674495490, null, '树形列表', 'tree_list', null, 1, 1, 0, 0, null, null, null, null, null, null, null, 10, 3, 1806576637307789314, 1806576190169817090, 1806576637307789314, null, null, null, null, null, null, null, null, 1, null, '2024-06-28 14:35:12', 1, '2024-06-28 14:35:12', null);

-- 约束条件内容类型 字典组
INSERT INTO component_dict (id, code, name, value, value_unit, is_system, is_public, is_group, is_disabled, disabled_reason, private_flag, private_flag_memo, group_flag, group_flag_memo, tags, remark, seq, level, parent_id, parent_id1, parent_id2, parent_id3, parent_id4, parent_id5, parent_id6, parent_id7, parent_id8, parent_id9, parent_id10, version, tenant_id, create_at, create_by, update_at, update_by) VALUES (1809105188678606850, 'data_scope_constraint_content_type', '约束条件内容类型', '', null, 1, 1, 1, 0, null, null, null, null, null, null, null, 10, 2, 1806576190169817090, 1806576190169817090, null, null, null, null, null, null, null, null, null, 1, null, '2024-07-05 14:01:03', 1, '2024-07-05 14:01:03', null);
-- 约束条件内容类型 字典项
INSERT INTO component_dict (id, code, name, value, value_unit, is_system, is_public, is_group, is_disabled, disabled_reason, private_flag, private_flag_memo, group_flag, group_flag_memo, tags, remark, seq, level, parent_id, parent_id1, parent_id2, parent_id3, parent_id4, parent_id5, parent_id6, parent_id7, parent_id8, parent_id9, parent_id10, version, tenant_id, create_at, create_by, update_at, update_by) VALUES (1809105346426380290, null, '原始sql', 'sql_raw', null, 1, 1, 0, 0, null, null, null, null, null, null, null, 10, 3, 1809105188678606850, 1806576190169817090, 1809105188678606850, null, null, null, null, null, null, null, null, 1, null, '2024-07-05 14:01:41', 1, '2024-07-05 14:01:41', null);
INSERT INTO component_dict (id, code, name, value, value_unit, is_system, is_public, is_group, is_disabled, disabled_reason, private_flag, private_flag_memo, group_flag, group_flag_memo, tags, remark, seq, level, parent_id, parent_id1, parent_id2, parent_id3, parent_id4, parent_id5, parent_id6, parent_id7, parent_id8, parent_id9, parent_id10, version, tenant_id, create_at, create_by, update_at, update_by) VALUES (1809105406434287617, null, 'enjoy模板渲染sql', 'sql_enjoy_template', null, 1, 1, 0, 0, null, null, null, null, null, null, null, 10, 3, 1809105188678606850, 1806576190169817090, 1809105188678606850, null, null, null, null, null, null, null, null, 1, null, '2024-07-05 14:01:55', 1, '2024-07-05 14:01:55', null);
INSERT INTO component_dict (id, code, name, value, value_unit, is_system, is_public, is_group, is_disabled, disabled_reason, private_flag, private_flag_memo, group_flag, group_flag_memo, tags, remark, seq, level, parent_id, parent_id1, parent_id2, parent_id3, parent_id4, parent_id5, parent_id6, parent_id7, parent_id8, parent_id9, parent_id10, version, tenant_id, create_at, create_by, update_at, update_by) VALUES (1809105475220873218, null, 'groovy脚本直接处理', 'groovy_script', null, 1, 1, 0, 0, null, null, null, null, null, null, null, 10, 3, 1809105188678606850, 1806576190169817090, 1809105188678606850, null, null, null, null, null, null, null, null, 1, null, '2024-07-05 14:02:12', 1, '2024-07-05 14:02:12', null);