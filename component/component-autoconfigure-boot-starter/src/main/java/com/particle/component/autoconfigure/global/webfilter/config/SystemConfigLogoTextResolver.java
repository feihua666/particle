package com.particle.component.autoconfigure.global.webfilter.config;

import cn.hutool.core.util.StrUtil;
import com.particle.config.adapter.feign.client.system.rpc.SystemConfigRpcFeignClient;
import com.particle.config.client.system.dto.data.SystemConfigVO;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.web.filter.LogoTextFilter;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;

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
public class SystemConfigLogoTextResolver implements LogoTextFilter.LogoTextResolver {

    private static final String SYSTEM_LOGO_TEXT = "system.logo.text";

    @Autowired
    private SystemConfigRpcFeignClient systemConfigRpcFeignClient;

    @Override
    public LogoTextFilter.LogoTextResolveResult resolve(HttpServletRequest request) {

        MultiResponse<SystemConfigVO> systemConfigVOSingleResponse = systemConfigRpcFeignClient.queryByTag(SYSTEM_LOGO_TEXT,false);
        if (systemConfigVOSingleResponse == null || systemConfigVOSingleResponse.getData() ==  null || systemConfigVOSingleResponse.getData().size() == 0) {
            return null;
        }
        String value = systemConfigVOSingleResponse.getData().iterator().next().getValue();

        if (StrUtil.isEmpty(value)) {
            return null;
        }
        return LogoTextFilter.resolveByAmbiguousString(value,"system logo text");
    }
}
