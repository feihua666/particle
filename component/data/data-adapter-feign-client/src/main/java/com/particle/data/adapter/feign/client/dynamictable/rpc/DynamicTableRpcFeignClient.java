package com.particle.data.adapter.feign.client.dynamictable.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 动态数据表格远程调用
 * </p>
 *
 * @author yw
 * @since 2025-11-05 20:29:35
 */
@FeignClient(name = "${particle.feign-client.name.data:data}",path = "/rpc/dynamic_table")
public interface DynamicTableRpcFeignClient {









}
