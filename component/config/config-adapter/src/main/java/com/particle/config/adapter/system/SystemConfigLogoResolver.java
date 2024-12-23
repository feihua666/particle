package com.particle.config.adapter.system;

import cn.hutool.core.util.StrUtil;
import com.particle.config.infrastructure.system.dos.SystemConfigDO;
import com.particle.config.infrastructure.system.service.ISystemConfigService;
import com.particle.global.web.filter.LogoFilter;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 基于系统配置的配置 logo 获取
 * </p>
 *
 * @author yangwei
 * @since 2024/6/4 13:20
 */
@Slf4j
@Order(LogoFilter.LogoResolver.componentBaseOrder + 80)
@Component
public class SystemConfigLogoResolver implements LogoFilter.LogoResolver {

    private static final String SYSTEM_LOGO = "system.logo";

    @Autowired
    private ISystemConfigService iSystemConfigService;

    @Override
    public LogoFilter.LogoResolveResult resolve(HttpServletRequest request) {

        SystemConfigDO byCode = iSystemConfigService.getByCode(SYSTEM_LOGO);
        if (byCode == null) {
            return null;
        }
        String value = byCode.getValue();

        if (StrUtil.isEmpty(value)) {
            return null;
        }
        return LogoFilter.resolveByAmbiguousString(value,"system config");
    }
}
