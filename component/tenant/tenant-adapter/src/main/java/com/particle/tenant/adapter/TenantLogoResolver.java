package com.particle.tenant.adapter;

import cn.hutool.core.util.StrUtil;
import com.particle.global.security.tenant.TenantTool;
import com.particle.global.web.filter.LogoFilter;
import com.particle.tenant.infrastructure.dos.TenantDO;
import com.particle.tenant.infrastructure.service.ITenantService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 基于租户的配置 logo 获取
 * </p>
 *
 * @author yangwei
 * @since 2024/6/4 13:20
 */
@Slf4j
@Order(LogoFilter.LogoResolver.componentBaseOrder + 100)
@Component
public class TenantLogoResolver implements LogoFilter.LogoResolver {

    @Autowired
    private ITenantService iTenantService;

    @Override
    public LogoFilter.LogoResolveResult resolve(HttpServletRequest request) {
        if (!TenantTool.isTenantEnable()) {
            return null;
        }
        Long tenantId = TenantTool.getTenantId();
        if (tenantId == null) {
            return null;
        }
        TenantDO byIdIgnoreTenantLimit = iTenantService.getByIdIgnoreTenantLimit(tenantId);
        if (byIdIgnoreTenantLimit == null) {
            return null;
        }
        String tenantLogoJson = byIdIgnoreTenantLimit.getTenantLogoJson();
        if (StrUtil.isEmpty(tenantLogoJson)) {
            return null;
        }
        return LogoFilter.resolveByAmbiguousString(tenantLogoJson,"tenant");
    }
}
