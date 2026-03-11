package com.particle.cms.adapter.dynamic.service;

/**
 * <p>
 * 权限校验服务
 * 此类可选，需要动态配置
 * </p>
 *
 * @author yangwei
 * @since 2026/1/20 09:35
 */
public interface ICmsPermissionService {

    public boolean hasPermission(String permission);
}
