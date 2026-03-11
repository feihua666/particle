package com.particle.openplatform.adapter.feign.client.globalopenapi.rpc;

import com.particle.global.dto.response.Response;
import com.particle.global.openapi.collect.OpenapiContext;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * <p>
 *
 * </p>
 *
 * @author yangwei
 * @since 2026/3/10 16:21
 */
@FeignClient(name = "${particle.feign-client.openplatform.name:open-platform-start}", contextId = "openplatformGlobalOpenapiCollectPersistentServiceRpcFeignClient", url = "${particle.feign-client.openplatform.url:}", path = "/rpc/openplatform_global_openapi_collect_persistent")
public interface OpenplatformGlobalOpenapiCollectPersistentServiceRpcFeignClient {


    /**
     * 以开放平台为入口的统一保存开放平台调用记录，包括供应商调用记录
     * @param openapiContext
     */
    @PostMapping("/save")
    Response save(@RequestBody OpenapiContext openapiContext);

    /**
     * 保存供应商调用记录
     * 在某一个接口中，调用多次供应商数据时，可能会很大，单独保存，以降低内存使用
     * @param openapiContext
     */
    @PostMapping("/save_provider")
    Response saveProvider(@RequestBody OpenapiContext openapiContext);
}
