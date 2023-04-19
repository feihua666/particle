package com.particle.dept.adapter.feign.client.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 部门树名称远程调用
 * </p>
 *
 * @author yw
 * @since 2023-04-12 11:42:10
 */
@FeignClient(name = "${particle.feign-client.name.dept:dept}",path = "/rpc/dept_tree_name")
public interface DeptTreeNameRpcFeignClient {









}
