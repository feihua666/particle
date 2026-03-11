package com.particle.openplatform.adapter.feign.client.doc.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 开放接口文档模板示例代码远程调用
 * </p>
 *
 * @author yw
 * @since 2024-03-18 17:04:26
 */
@FeignClient(name = "${particle.feign-client.openplatform.name:open-platform-start}", contextId = "openplatformDocApiDocTemplateExampleCodeRpcFeignClient", url = "${particle.feign-client.openplatform.url:}", path = "/rpc/openplatform_doc_api_doc_template_example_code")
public interface OpenplatformDocApiDocTemplateExampleCodeRpcFeignClient {









}
