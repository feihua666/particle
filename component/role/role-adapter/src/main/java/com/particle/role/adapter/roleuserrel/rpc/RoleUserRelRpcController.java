package com.particle.role.adapter.roleuserrel.rpc;

import com.particle.role.app.roleuserrel.structmapping.RoleUserRelAppStructMapping;
import com.particle.role.client.roleuserrel.api.IRoleUserRelApplicationService;
import com.particle.role.adapter.feign.client.roleuserrel.rpc.RoleUserRelRpcFeignClient;
import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.global.dto.response.SingleResponse;
import com.particle.role.client.roleuserrel.dto.command.RoleUserRelCreateCommand;
import com.particle.role.client.roleuserrel.dto.command.RoleUserRelWithTenantIdCreateCommand;
import com.particle.role.client.roleuserrel.dto.data.RoleUserRelVO;
import com.particle.role.infrastructure.roleuserrel.dos.RoleUserRelDO;
import com.particle.role.infrastructure.roleuserrel.service.IRoleUserRelService;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 角色用户关系远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@Tag(name = "角色用户关系远程调用相关接口")
@RestController
@RequestMapping("/rpc/role-user-rel")
public class RoleUserRelRpcController extends AbstractBaseRpcAdapter implements RoleUserRelRpcFeignClient {

	@Autowired
	private IRoleUserRelApplicationService iRoleUserRelApplicationService;

	@Autowired
	private IRoleUserRelService iRoleUserRelService;

	@Operation(summary = "添加角色用户关系")
	@PostMapping("/add")
	public RoleUserRelVO add(@RequestBody RoleUserRelWithTenantIdCreateCommand roleUserRelCreateCommand){
		RoleUserRelDO roleUserRelDO = new RoleUserRelDO();
		roleUserRelDO.setRoleId(roleUserRelCreateCommand.getRoleId());
		roleUserRelDO.setUserId(roleUserRelCreateCommand.getUserId());
		roleUserRelDO.setTenantId(roleUserRelCreateCommand.getTenantId());

		RoleUserRelDO add = iRoleUserRelService.add(roleUserRelDO);

		return RoleUserRelAppStructMapping.instance.roleUserRelDOToRoleUserRelVO(add);
	}







}