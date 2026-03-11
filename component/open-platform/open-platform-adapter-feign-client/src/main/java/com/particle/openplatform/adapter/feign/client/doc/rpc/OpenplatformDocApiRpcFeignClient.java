package com.particle.openplatform.adapter.feign.client.doc.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 开放接口文档接口远程调用
 * </p>
 *
 * @author yw
 * @since 2024-03-14 14:56:01
 */
@FeignClient(name = "${particle.feign-client.openplatform.name:open-platform-start}", contextId = "openplatformDocApiRpcFeignClient", url = "${particle.feign-client.openplatform.url:}", path = "/rpc/openplatform_doc_api")
public interface OpenplatformDocApiRpcFeignClient {









}
