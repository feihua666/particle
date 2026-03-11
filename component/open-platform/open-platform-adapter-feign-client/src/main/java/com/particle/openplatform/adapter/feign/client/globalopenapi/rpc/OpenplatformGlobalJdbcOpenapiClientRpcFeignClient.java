package com.particle.openplatform.adapter.feign.client.globalopenapi.rpc;

import com.particle.global.dto.response.SingleResponse;
import com.particle.global.openapi.data.OpenapiClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <p>
 *
 * </p>
 *
 * @author yangwei
 * @since 2026/3/10 15:43
 */
@FeignClient(name = "${particle.feign-client.openplatform.name:open-platform-start}", contextId = "openplatformGlobalJdbcOpenapiClientRpcFeignClient", url = "${particle.feign-client.openplatform.url:}", path = "/rpc/openplatform_global_openapi_client")
public interface OpenplatformGlobalJdbcOpenapiClientRpcFeignClient {

    /**
     * 根据客户端id获取客户端信息
     * @param clientId
     * @param includeSecret 是否包括密码，为了提高查询效率省去不必要的查询
     * @param includeAuthorities 是否包括权限，为了提高查询效率省去不必要的查询
     * @return
     */
    @GetMapping("/getOpenapiClientByClientId")
    public SingleResponse<OpenapiClient> getOpenapiClientByClientId(@RequestParam String clientId,
                                                     @RequestParam boolean includeSecret,
                                                     @RequestParam boolean includeAuthorities);
}
