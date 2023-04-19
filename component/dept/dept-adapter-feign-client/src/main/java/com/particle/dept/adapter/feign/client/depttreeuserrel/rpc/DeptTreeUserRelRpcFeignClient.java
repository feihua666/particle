package com.particle.dept.adapter.feign.client.depttreeuserrel.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 部门树用户关系远程调用
 * </p>
 *
 * @author yw
 * @since 2023-04-12 17:28:43
 */
@FeignClient(name = "${particle.feign-client.name.dept:dept}",path = "/rpc/dept_tree_user_rel")
public interface DeptTreeUserRelRpcFeignClient {









}
