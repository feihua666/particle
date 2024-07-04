package com.particle.dataconstraint.adapter.feign.client.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 数据范围自定义数据关系远程调用
 * </p>
 *
 * @author yw
 * @since 2024-06-28 13:10:55
 */
@FeignClient(name = "${particle.feign-client.name.data-constraint:data-constraint}",path = "/rpc/data_scope_custom_data_rel")
public interface DataScopeCustomDataRelRpcFeignClient {









}
