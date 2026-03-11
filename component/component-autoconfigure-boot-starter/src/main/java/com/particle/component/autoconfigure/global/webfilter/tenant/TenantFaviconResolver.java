package com.particle.component.autoconfigure.global.webfilter.tenant;

import cn.hutool.core.util.StrUtil;
import com.particle.common.client.dto.command.CommonIdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.tool.tenant.TenantTool;
import com.particle.global.web.filter.FaviconFilter;
import com.particle.tenant.adapter.feign.client.rpc.TenantRpcFeignClient;
import com.particle.tenant.client.dto.data.TenantRpcVO;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;

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
public class TenantFaviconResolver implements FaviconFilter.FaviconResolver {

    @Autowired
    private TenantRpcFeignClient tenantRpcFeignClient;

    @Override
    public FaviconFilter.FaviconResolveResult resolve(HttpServletRequest request) {
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
        String tenantFaviconJson = tenantVOSingleResponse.getData().getTenantFaviconJson();
        if (StrUtil.isEmpty(tenantFaviconJson)) {
            return null;
        }
        return FaviconFilter.resolveByAmbiguousString(tenantFaviconJson,"tenant");
    }
}
