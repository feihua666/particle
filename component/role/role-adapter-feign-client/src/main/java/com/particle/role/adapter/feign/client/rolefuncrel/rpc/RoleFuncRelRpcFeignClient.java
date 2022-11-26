package com.particle.role.adapter.feign.client.rolefuncrel.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 角色菜单功能关系远程调用
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@FeignClient(name = "${particle.feign-client.name.roleFuncRel:roleFuncRel}",path = "/rpc")
public interface RoleFuncRelRpcFeignClient {









}
