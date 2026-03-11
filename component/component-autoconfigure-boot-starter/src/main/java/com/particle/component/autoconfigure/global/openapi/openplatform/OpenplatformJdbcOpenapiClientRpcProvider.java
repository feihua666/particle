package com.particle.component.autoconfigure.global.openapi.openplatform;

import com.particle.global.dto.response.SingleResponse;
import com.particle.global.openapi.api.GlobalOpenapiClientProvider;
import com.particle.global.openapi.data.OpenapiClient;
import com.particle.openplatform.adapter.feign.client.globalopenapi.rpc.OpenplatformGlobalJdbcOpenapiClientRpcFeignClient;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 *
 * </p>
 *
 * @author yangwei
 * @since 2026/3/10 15:50
 */
public class OpenplatformJdbcOpenapiClientRpcProvider implements GlobalOpenapiClientProvider {

    @Autowired
    private OpenplatformGlobalJdbcOpenapiClientRpcFeignClient openplatformGlobalJdbcOpenapiClientRpcFeignClient;
    @Override
    public OpenapiClient getOpenapiClientByClientId(String clientId, boolean includeSecret, boolean includeAuthorities) {
        SingleResponse<OpenapiClient> openapiClientByClientId = openplatformGlobalJdbcOpenapiClientRpcFeignClient.getOpenapiClientByClientId(clientId, includeSecret, includeAuthorities);
        return openapiClientByClientId.getData();
    }
}
