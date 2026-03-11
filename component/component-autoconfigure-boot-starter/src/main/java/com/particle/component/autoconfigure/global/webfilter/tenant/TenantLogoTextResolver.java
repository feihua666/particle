package com.particle.component.autoconfigure.global.webfilter.tenant;

import cn.hutool.core.util.StrUtil;
import com.particle.common.client.dto.command.CommonIdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.tool.tenant.TenantTool;
import com.particle.global.web.filter.LogoTextFilter;
import com.particle.tenant.adapter.feign.client.rpc.TenantRpcFeignClient;
import com.particle.tenant.client.dto.data.TenantRpcVO;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;

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
public class TenantLogoTextResolver implements LogoTextFilter.LogoTextResolver {

    @Autowired
    private TenantRpcFeignClient tenantRpcFeignClient;

    @Override
    public LogoTextFilter.LogoTextResolveResult resolve(HttpServletRequest request) {
        if (!TenantTool.isTenantEnable()) {
            return null;
        }
        Long tenantId = TenantTool.getTenantId();
        if (tenantId == null) {
            return null;
        }
        SingleResponse<TenantRpcVO> tenantVOSingleResponse = tenantRpcFeignClient.getById(CommonIdCommand.create(tenantId));
        if (tenantVOSingleResponse == null || tenantVOSingleResponse.getData() == null) {
            return null;
        }
        String tenantLogoTextJson = tenantVOSingleResponse.getData().getTenantLogoTextJson();
        if (StrUtil.isEmpty(tenantLogoTextJson)) {
            return null;
        }
        return LogoTextFilter.resolveByAmbiguousString(tenantLogoTextJson,"tenant");
    }
}
