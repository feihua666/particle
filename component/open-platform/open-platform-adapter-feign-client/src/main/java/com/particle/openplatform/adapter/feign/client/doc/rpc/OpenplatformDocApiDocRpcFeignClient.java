package com.particle.openplatform.adapter.feign.client.doc.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 开放接口文档远程调用
 * </p>
 *
 * @author yw
 * @since 2024-03-14 14:56:37
 */
@FeignClient(name = "${particle.feign-client.openplatform.name:open-platform-start}", contextId = "openplatformDocApiDocRpcFeignClient", url = "${particle.feign-client.openplatform.url:}", path = "/rpc/openplatform_doc_api_doc")
public interface OpenplatformDocApiDocRpcFeignClient {









}
