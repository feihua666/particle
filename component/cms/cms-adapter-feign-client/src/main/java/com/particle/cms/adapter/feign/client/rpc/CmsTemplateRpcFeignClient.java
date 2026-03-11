package com.particle.cms.adapter.feign.client.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 模板远程调用
 * </p>
 *
 * @author yw
 * @since 2026-01-21 21:03:36
 */
@FeignClient(name = "${particle.feign-client.cms.name:cms-start}", contextId = "cmsTemplateRpcFeignClient", url = "${particle.feign-client.cms.url:}", path = "/rpc/cms_template")
public interface CmsTemplateRpcFeignClient {









}
