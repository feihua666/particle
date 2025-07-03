package com.particle.cms.adapter.feign.client.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 站点远程调用
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:14:04
 */
@FeignClient(name = "${particle.feign-client.name.cms:cms}",path = "/rpc/cms_site")
public interface CmsSiteRpcFeignClient {









}
