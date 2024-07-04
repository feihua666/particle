package com.particle.dataconstraint.adapter.feign.client.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 数据范围远程调用
 * </p>
 *
 * @author yw
 * @since 2024-06-28 13:10:38
 */
@FeignClient(name = "${particle.feign-client.name.data-constraint:data-constraint}",path = "/rpc/data_scope")
public interface DataScopeRpcFeignClient {









}
