package com.particle.tracking.adapter.feign.client.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 页面埋点记录远程调用
 * </p>
 *
 * @author yw
 * @since 2023-05-10 11:44:01
 */
@FeignClient(name = "${particle.feign-client.name.tracking:tracking}",path = "/rpc/tracking_page_record")
public interface TrackingPageRecordRpcFeignClient {









}
