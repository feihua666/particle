package com.particle.global.autoconfigure.feign;

import com.particle.global.light.share.constant.GlobalRpcConstants;
import com.particle.global.light.share.login.LoginConstants;
import com.particle.global.tool.login.TokenTool;
import com.particle.global.tool.tenant.TenantTool;
import feign.RequestInterceptor;
import feign.RequestTemplate;

/**
 * <p>
 * 全局feign拦截器，添加租户id和token
 * </p>
 *
 * @author yangwei
 * @since 2026/3/7 17:17
 */
public class GlobalFeignInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate template) {
        Long tenantId = TenantTool.getTenantId();
        if (tenantId != null) {
            template.header(GlobalRpcConstants.request_header_tenant_id, tenantId.toString());
        }
        String token = TokenTool.getToken();
        if (token != null) {
            template.header(LoginConstants.header_c_token, token);
        }
    }
}
