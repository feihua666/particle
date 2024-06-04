package com.particle.tenant.adapter;

import cn.hutool.core.util.StrUtil;
import com.particle.global.security.tenant.TenantTool;
import com.particle.global.web.filter.FaviconFilter;
import com.particle.tenant.infrastructure.dos.TenantDO;
import com.particle.tenant.infrastructure.service.ITenantService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 基于租户的配置 favicon.ico 获取
 * </p>
 *
 * @author yangwei
 * @since 2024/6/4 13:20
 */
@Slf4j
@Order(FaviconFilter.FaviconResolver.componentBaseOrder + 100)
@Component
public class TenantFaviconResolver implements FaviconFilter.FaviconResolver {

    @Autowired
    private ITenantService iTenantService;

    @Override
    public FaviconFilter.FaviconResolveResult resolve(HttpServletRequest request) {
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
        String tenantFaviconJson = byIdIgnoreTenantLimit.getTenantFaviconJson();
        if (StrUtil.isEmpty(tenantFaviconJson)) {
            return null;
        }
        return FaviconFilter.resolveByAmbiguousString(tenantFaviconJson,"tenant");
    }
}
