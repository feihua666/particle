package com.particle.data.adapter.feign.client.dynamicdata.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 动态数据指标远程调用
 * </p>
 *
 * @author yw
 * @since 2025-11-05 20:31:12
 */
@FeignClient(name = "${particle.feign-client.name.data:data}",path = "/rpc/dynamic_data_indicator")
public interface DynamicDataIndicatorRpcFeignClient {









}
