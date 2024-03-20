package com.particle.openplatform.adapter.feign.client.doc.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 开放接口文档响应码远程调用
 * </p>
 *
 * @author yw
 * @since 2024-03-18 14:44:43
 */
@FeignClient(name = "${particle.feign-client.name.open-platform:open-platform}",path = "/rpc/openplatform_doc_api_doc_response_code")
public interface OpenplatformDocApiDocResponseCodeRpcFeignClient {









}
