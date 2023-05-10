package com.particle.tracking.adapter.feign.client.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 埋点页面远程调用
 * </p>
 *
 * @author yw
 * @since 2023-05-10 11:39:06
 */
@FeignClient(name = "${particle.feign-client.name.tracking:tracking}",path = "/rpc/tracking_page")
public interface TrackingPageRpcFeignClient {









}
