package com.particle.cms.adapter.feign.client.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 模板内容远程调用
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:13:35
 */
@FeignClient(name = "${particle.feign-client.name.cms:cms}",path = "/rpc/cms_template_content")
public interface CmsTemplateContentRpcFeignClient {









}
