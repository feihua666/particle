package com.particle.role.adapter.feign.client.roleuserrel.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 角色用户关系远程调用
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@FeignClient(name = "${particle.feign-client.name.roleUserRel:roleUserRel}",path = "/rpc")
public interface RoleUserRelRpcFeignClient {









}
