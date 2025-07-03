package com.particle.cms.adapter.feign.client.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 站点首页访问记录远程调用
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:15:10
 */
@FeignClient(name = "${particle.feign-client.name.cms:cms}",path = "/rpc/cms_site_index_view_record")
public interface CmsSiteIndexViewRecordRpcFeignClient {









}
