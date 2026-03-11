package com.particle.data.adapter.feign.client.dynamicdata.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 动态数据分类远程调用
 * </p>
 *
 * @author yw
 * @since 2025-11-05 20:30:37
 */
@FeignClient(name = "${particle.feign-client.data.name:data-start}", contextId = "dynamicDataCategoryRpcFeignClient", url = "${particle.feign-client.data.url:}", path = "/rpc/dynamic_data_category")
public interface DynamicDataCategoryRpcFeignClient {









}
