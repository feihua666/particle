package com.particle.openplatform.adapter.globalopenapi.rpc;

import com.particle.global.dto.response.SingleResponse;
import com.particle.global.openapi.data.OpenapiClient;
import com.particle.openplatform.adapter.feign.client.globalopenapi.rpc.OpenplatformGlobalJdbcOpenapiClientRpcFeignClient;
import com.particle.openplatform.adapter.globalopenapi.OpenplatformGlobalJdbcOpenapiClientProviderImpl;
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
 * @since 2026/3/9 19:57
 */
@Tag(name = "客户端远程调用相关接口")
@RestController
@RequestMapping("/rpc/openplatform_global_openapi_client")
public class OpenplatformGlobalJdbcOpenapiClientRpcController implements OpenplatformGlobalJdbcOpenapiClientRpcFeignClient {

    @Autowired
    private OpenplatformGlobalJdbcOpenapiClientProviderImpl openplatformGlobalJdbcOpenapiClientProviderImpl;

    @Operation(summary = "获取client信息")
    @Override
    public SingleResponse<OpenapiClient> getOpenapiClientByClientId(String clientId, boolean includeSecret, boolean includeAuthorities) {
        OpenapiClient openapiClientByClientId = openplatformGlobalJdbcOpenapiClientProviderImpl.getOpenapiClientByClientId(clientId, includeSecret, includeAuthorities);
        return SingleResponse.of(openapiClientByClientId);
    }
}
