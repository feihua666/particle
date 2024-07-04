package com.particle.dataconstraint.adapter.feign.client.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 数据对象远程调用
 * </p>
 *
 * @author yw
 * @since 2024-06-28 13:10:18
 */
@FeignClient(name = "${particle.feign-client.name.data-constraint:data-constraint}",path = "/rpc/data_object")
public interface DataObjectRpcFeignClient {









}
