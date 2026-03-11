package com.particle.component.autoconfigure.global.webfilter.config;

import cn.hutool.core.util.StrUtil;
import com.particle.config.adapter.feign.client.system.rpc.SystemConfigRpcFeignClient;
import com.particle.config.client.system.dto.data.SystemConfigVO;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.web.filter.WebTitleFilter;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;

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
public class SystemConfigWebTitleResolver implements WebTitleFilter.WebTitleResolver {

    private static final String SYSTEM_WEB_TITLE = "system.web.title";

    @Autowired
    private SystemConfigRpcFeignClient systemConfigRpcFeignClient;

    @Override
    public WebTitleFilter.WebTitleResolveResult resolve(HttpServletRequest request) {

        MultiResponse<SystemConfigVO> systemConfigVOSingleResponse = systemConfigRpcFeignClient.queryByTag(SYSTEM_WEB_TITLE,false);
        if (systemConfigVOSingleResponse == null || systemConfigVOSingleResponse.getData() ==  null || systemConfigVOSingleResponse.getData().size() == 0) {
            return null;
        }
        String value = systemConfigVOSingleResponse.getData().iterator().next().getValue();

        if (StrUtil.isEmpty(value)) {
            return null;
        }
        return WebTitleFilter.resolveByAmbiguousString(value,"system web title");
    }
}
