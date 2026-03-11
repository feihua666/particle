package com.particle.component.autoconfigure.global.openapi.openplatform;

import com.particle.global.dto.response.SingleResponse;
import com.particle.global.openapi.api.GlobalOpenapiApiInfoProvider;
import com.particle.global.openapi.data.ApiInfo;
import com.particle.openplatform.adapter.feign.client.globalopenapi.rpc.OpenplatformGlobalOpenapiApiInfoRpcFeignClient;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 *
 * </p>
 *
 * @author yangwei
 * @since 2026/3/9 19:34
 */
public class OpenPlatformOpenapiApiInfoRpcProvider implements GlobalOpenapiApiInfoProvider {

    @Autowired
    private OpenplatformGlobalOpenapiApiInfoRpcFeignClient openplatformGlobalOpenapiApiInfoRpcFeignClient;

    @Override
    public ApiInfo getApiInfo(String apiUrl, String appId) {
        SingleResponse<ApiInfo> apiInfo = openplatformGlobalOpenapiApiInfoRpcFeignClient.getApiInfo(apiUrl, appId);
        return apiInfo.getData();
    }
}
