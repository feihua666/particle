package com.particle.component.autoconfigure.cms.global.security;

import com.particle.cms.adapter.dynamic.service.ICmsPermissionService;
import com.particle.global.security.security.SecurityPermissionService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * 权限校验服务
 * 此类可选，需要动态配置
 * </p>
 *
 * @author yangwei
 * @since 2026/1/20 09:35
 */
public class CmsPermissionServiceImpl implements ICmsPermissionService {

    @Autowired
    private SecurityPermissionService securityPermissionService;

    @Override
    public boolean hasPermission(String permission) {

        return securityPermissionService.hasPermission( permission);
    }
}
