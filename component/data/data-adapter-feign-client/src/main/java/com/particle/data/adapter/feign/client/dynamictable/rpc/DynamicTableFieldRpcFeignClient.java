package com.particle.data.adapter.feign.client.dynamictable.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 动态数据表格字段远程调用
 * </p>
 *
 * @author yw
 * @since 2025-11-05 20:30:01
 */
@FeignClient(name = "${particle.feign-client.name.data:data}",path = "/rpc/dynamic_table_field")
public interface DynamicTableFieldRpcFeignClient {









}
