package com.particle.dept.adapter.feign.client.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 部门远程调用
 * </p>
 *
 * @author yw
 * @since 2023-04-12 14:19:42
 */
@FeignClient(name = "${particle.feign-client.name.dept:dept}",path = "/rpc/dept")
public interface DeptRpcFeignClient {









}
