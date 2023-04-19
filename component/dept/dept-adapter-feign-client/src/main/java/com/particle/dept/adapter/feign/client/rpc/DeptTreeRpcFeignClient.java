package com.particle.dept.adapter.feign.client.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 部门树远程调用
 * </p>
 *
 * @author yw
 * @since 2023-04-12 11:41:43
 */
@FeignClient(name = "${particle.feign-client.name.dept:dept}",path = "/rpc/dept_tree")
public interface DeptTreeRpcFeignClient {









}
