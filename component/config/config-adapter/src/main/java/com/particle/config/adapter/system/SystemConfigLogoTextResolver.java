package com.particle.config.adapter.system;

import cn.hutool.core.util.StrUtil;
import com.particle.config.infrastructure.system.dos.SystemConfigDO;
import com.particle.config.infrastructure.system.service.ISystemConfigService;
import com.particle.global.web.filter.LogoTextFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 基于系统配置的配置 logoText 获取
 * </p>
 *
 * @author yangwei
 * @since 2024/6/4 13:20
 */
@Slf4j
@Order(LogoTextFilter.LogoTextResolver.componentBaseOrder + 80)
@Component
public class SystemConfigLogoTextResolver implements LogoTextFilter.LogoTextResolver {

    private static final String SYSTEM_LOGO_TEXT = "system.logo.text";

    @Autowired
    private ISystemConfigService iSystemConfigService;

    @Override
    public LogoTextFilter.LogoTextResolveResult resolve(HttpServletRequest request) {

        SystemConfigDO byCode = iSystemConfigService.getByCode(SYSTEM_LOGO_TEXT);
        if (byCode == null) {
            return null;
        }
        String value = byCode.getValue();

        if (StrUtil.isEmpty(value)) {
            return null;
        }
        return LogoTextFilter.resolveByAmbiguousString(value,"system config");
    }
}
