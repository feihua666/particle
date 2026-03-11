-- import classpath:db/data.cms.func.sql
-- import classpath:db/data.cms.dict.sql

-- 默认站点
INSERT INTO component_cms_site (id, code, name, title, domain, dynamic_domain, dynamic_deploy_path, site_context_path, template_path, template_index, template404_path, template404_index, template403_path, template403_index, template_channel_path, template_channel_index, template_content_path, template_content_index, static_save_path, static_domain, static_deploy_path, is_prime_site, profile, is_public, public_at, is_enable_backend_record, remark, pv, init_pv, iv, uv, version, tenant_id, create_at, create_by, update_at, update_by) VALUES (2010911277722058753, null, '默认站点', null, 'http://localhost:8080', '', null, null, 'default', 'index.ftlh', '404', '404.ftlh', null, null, null, null, null, null, null, null, null, 0, null, 0, null, 0, null, 0, 0, 0, 0, 2, 1, '2026-01-13 11:06:24', 1818106787283390466, '2026-01-13 20:56:01', 1818106787283390466);
