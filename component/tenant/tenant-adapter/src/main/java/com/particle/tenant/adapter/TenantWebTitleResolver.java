package com.particle.tenant.adapter;

import cn.hutool.core.util.StrUtil;
import com.particle.global.security.tenant.TenantTool;
import com.particle.global.web.filter.WebTitleFilter;
import com.particle.tenant.infrastructure.dos.TenantDO;
import com.particle.tenant.infrastructure.service.ITenantService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 基于租户的配置 webTitle 获取
 * </p>
 *
 * @author yangwei
 * @since 2024/6/4 13:20
 */
@Slf4j
@Order(WebTitleFilter.WebTitleResolver.componentBaseOrder + 100)
@Component
public class TenantWebTitleResolver implements WebTitleFilter.WebTitleResolver {

    @Autowired
    private ITenantService iTenantService;

    @Override
    public WebTitleFilter.WebTitleResolveResult resolve(HttpServletRequest request) {
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
        String tenantWebTitleJson = byIdIgnoreTenantLimit.getTenantWebTitleJson();
        if (StrUtil.isEmpty(tenantWebTitleJson)) {
            return null;
        }
        return WebTitleFilter.resolveByAmbiguousString(tenantWebTitleJson,"tenant");
    }
}
