package com.particle.config.adapter.system;

import cn.hutool.core.util.StrUtil;
import com.particle.config.infrastructure.system.dos.SystemConfigDO;
import com.particle.config.infrastructure.system.service.ISystemConfigService;
import com.particle.global.web.filter.WebTitleFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 基于系统配置的配置 webTitle 获取
 * </p>
 *
 * @author yangwei
 * @since 2024/6/4 13:20
 */
@Slf4j
@Order(WebTitleFilter.WebTitleResolver.componentBaseOrder + 80)
@Component
public class SystemConfigWebTitleResolver implements WebTitleFilter.WebTitleResolver {

    private static final String SYSTEM_WEB_TITLE = "system.web.title";

    @Autowired
    private ISystemConfigService iSystemConfigService;

    @Override
    public WebTitleFilter.WebTitleResolveResult resolve(HttpServletRequest request) {

        SystemConfigDO byCode = iSystemConfigService.getByCode(SYSTEM_WEB_TITLE);
        if (byCode == null) {
            return null;
        }
        String value = byCode.getValue();

        if (StrUtil.isEmpty(value)) {
            return null;
        }
        return WebTitleFilter.resolveByAmbiguousString(value,"system config");
    }
}
