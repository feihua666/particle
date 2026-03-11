package com.particle.openplatform.adapter.feign.client.globalopenapi.rpc;

import com.particle.global.dto.response.SingleResponse;
import com.particle.global.openapi.data.ApiInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <p>
 * 开放接口批量查询记录明细远程调用
 * </p>
 *
 * @author yw
 * @since 2024-09-19 11:46:36
 */
@FeignClient(name = "${particle.feign-client.openplatform.name:open-platform-start}", contextId = "openplatformGlobalOpenapiApiInfoRpcFeignClient", url = "${particle.feign-client.openplatform.url:}", path = "/rpc/openplatform_global_openapi_api_info")
public interface OpenplatformGlobalOpenapiApiInfoRpcFeignClient {

    /**
     * 获取apiUrl的配置信息
     * @param apiUrl
     * @param appId
     * @return
     */
    @GetMapping("/getApiInfo")
    public SingleResponse<ApiInfo> getApiInfo(@RequestParam String apiUrl, @RequestParam String appId);

}
