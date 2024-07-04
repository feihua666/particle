package com.particle.role.adapter.feign.client.roledatascoperel.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 角色数据范围关系远程调用
 * </p>
 *
 * @author yw
 * @since 2024-07-01 16:45:06
 */
@FeignClient(name = "${particle.feign-client.name.role:role}",path = "/rpc/role_data_scope_rel")
public interface RoleDataScopeRelRpcFeignClient {









}
