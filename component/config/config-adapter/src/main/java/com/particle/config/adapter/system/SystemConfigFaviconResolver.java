package com.particle.config.adapter.system;

import cn.hutool.core.util.StrUtil;
import com.particle.config.infrastructure.system.dos.SystemConfigDO;
import com.particle.config.infrastructure.system.service.ISystemConfigService;
import com.particle.global.web.filter.FaviconFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 基于系统配置的配置 favicon.ico 获取
 * </p>
 *
 * @author yangwei
 * @since 2024/6/4 13:20
 */
@Slf4j
@Order(FaviconFilter.FaviconResolver.componentBaseOrder + 80)
@Component
public class SystemConfigFaviconResolver implements FaviconFilter.FaviconResolver {

    private static final String SYSTEM_FAVICON = "system.favicon";

    @Autowired
    private ISystemConfigService iSystemConfigService;

    @Override
    public FaviconFilter.FaviconResolveResult resolve(HttpServletRequest request) {

        SystemConfigDO byCode = iSystemConfigService.getByCode(SYSTEM_FAVICON);
        if (byCode == null) {
            return null;
        }
        String value = byCode.getValue();

        if (StrUtil.isEmpty(value)) {
            return null;
        }
        return FaviconFilter.resolveByAmbiguousString(value,"system config");
    }
}
