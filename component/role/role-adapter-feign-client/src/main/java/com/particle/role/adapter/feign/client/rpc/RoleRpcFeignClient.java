package com.particle.role.adapter.feign.client.rpc;

import com.particle.role.client.dto.command.RoleCreateWithTenantIdCommand;
import com.particle.role.client.dto.data.RoleVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * <p>
 * 角色远程调用
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@FeignClient(name = "${particle.feign-client.name.role:role}",path = "/rpc")
public interface RoleRpcFeignClient {


	/**
	 * 添加角色
	 * @param roleCreateWithTenantIdCommand
	 * @return
	 */
	@PostMapping("/add")
	public RoleVO add(@RequestBody RoleCreateWithTenantIdCommand roleCreateWithTenantIdCommand);






}
