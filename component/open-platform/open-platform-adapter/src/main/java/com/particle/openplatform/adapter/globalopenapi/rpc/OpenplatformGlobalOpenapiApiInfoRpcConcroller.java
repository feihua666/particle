package com.particle.openplatform.adapter.globalopenapi.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.openapi.data.ApiInfo;
import com.particle.openplatform.adapter.feign.client.globalopenapi.rpc.OpenplatformGlobalOpenapiApiInfoRpcFeignClient;
import com.particle.openplatform.adapter.globalopenapi.OpenPlatformGlobalOpenapiApiInfoProviderImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *
 * </p>
 *
 * @author yangwei
 * @since 2026/3/9 18:32
 */
@Tag(name = "接口信息远程调用相关接口")
@RestController
@RequestMapping("/rpc/openplatform_global_openapi_api_info")
public class OpenplatformGlobalOpenapiApiInfoRpcConcroller  extends AbstractBaseRpcAdapter implements OpenplatformGlobalOpenapiApiInfoRpcFeignClient {

    @Autowired
    private OpenPlatformGlobalOpenapiApiInfoProviderImpl openPlatformGlobalOpenapiApiInfoProviderImpl;

    @Operation(summary = "获取apiUrl的配置信息")
    @Override
    public SingleResponse<ApiInfo> getApiInfo(String apiUrl, String appId) {
        ApiInfo apiInfo = openPlatformGlobalOpenapiApiInfoProviderImpl.getApiInfo(apiUrl, appId);
        return SingleResponse.of(apiInfo);
    }
}
