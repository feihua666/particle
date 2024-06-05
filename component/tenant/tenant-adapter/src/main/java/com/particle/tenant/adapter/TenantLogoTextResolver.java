package com.particle.tenant.adapter;

import cn.hutool.core.util.StrUtil;
import com.particle.global.security.tenant.TenantTool;
import com.particle.global.web.filter.LogoTextFilter;
import com.particle.tenant.infrastructure.dos.TenantDO;
import com.particle.tenant.infrastructure.service.ITenantService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 基于租户的配置 logoText 获取
 * </p>
 *
 * @author yangwei
 * @since 2024/6/4 13:20
 */
@Slf4j
@Order(LogoTextFilter.LogoTextResolver.componentBaseOrder + 100)
@Component
public class TenantLogoTextResolver implements LogoTextFilter.LogoTextResolver {

    @Autowired
    private ITenantService iTenantService;

    @Override
    public LogoTextFilter.LogoTextResolveResult resolve(HttpServletRequest request) {
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
        String tenantLogoTextJson = byIdIgnoreTenantLimit.getTenantLogoTextJson();
        if (StrUtil.isEmpty(tenantLogoTextJson)) {
            return null;
        }
        return LogoTextFilter.resolveByAmbiguousString(tenantLogoTextJson,"tenant");
    }
}
