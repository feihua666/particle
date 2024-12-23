package com.particle.role.adapter.feign.client.roleuserrel.rpc;

import com.particle.role.client.roleuserrel.dto.command.RoleUserRelWithTenantIdCreateCommand;
import com.particle.role.client.roleuserrel.dto.data.RoleUserRelVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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

	/**
	 * 添加角色用户关系
	 *
	 * @param roleUserRelCreateCommand
	 * @return
	 */
	@PostMapping("/add")
	public RoleUserRelVO add(@RequestBody RoleUserRelWithTenantIdCreateCommand roleUserRelCreateCommand);




}
