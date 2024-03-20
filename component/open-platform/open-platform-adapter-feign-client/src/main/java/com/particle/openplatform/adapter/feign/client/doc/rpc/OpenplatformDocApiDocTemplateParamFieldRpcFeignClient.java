package com.particle.openplatform.adapter.feign.client.doc.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 开放接口文档模板参数字段远程调用
 * </p>
 *
 * @author yw
 * @since 2024-03-18 16:48:56
 */
@FeignClient(name = "${particle.feign-client.name.open-platform:open-platform}",path = "/rpc/openplatform_doc_api_doc_template_param_field")
public interface OpenplatformDocApiDocTemplateParamFieldRpcFeignClient {









}
