package com.particle.dept.adapter.feign.client.deptuserrel.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 部门用户关系远程调用
 * </p>
 *
 * @author yw
 * @since 2023-05-17 10:28:42
 */
@FeignClient(name = "${particle.feign-client.name.dept:dept}",path = "/rpc/dept_user_rel")
public interface DeptUserRelRpcFeignClient {









}
