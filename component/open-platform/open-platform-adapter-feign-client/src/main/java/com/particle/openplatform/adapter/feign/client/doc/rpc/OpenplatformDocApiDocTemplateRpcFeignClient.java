package com.particle.openplatform.adapter.feign.client.doc.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 开放接口文档模板远程调用
 * </p>
 *
 * @author yw
 * @since 2024-03-18 16:48:39
 */
@FeignClient(name = "${particle.feign-client.openplatform.name:open-platform-start}", contextId = "openplatformDocApiDocTemplateRpcFeignClient", url = "${particle.feign-client.openplatform.url:}", path = "/rpc/openplatform_doc_api_doc_template")
public interface OpenplatformDocApiDocTemplateRpcFeignClient {









}
