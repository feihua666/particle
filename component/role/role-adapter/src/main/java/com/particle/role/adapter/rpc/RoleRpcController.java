package com.particle.role.adapter.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.role.adapter.feign.client.rpc.RoleRpcFeignClient;
import com.particle.role.app.structmapping.RoleAppStructMapping;
import com.particle.role.client.api.IRoleApplicationService;
import com.particle.role.client.dto.command.RoleCreateWithTenantIdCommand;
import com.particle.role.client.dto.data.RoleVO;
import com.particle.role.infrastructure.dos.RoleDO;
import com.particle.role.infrastructure.service.IRoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 角色远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@Tag(name = "角色远程调用相关接口")
@RestController
@RequestMapping("/rpc/role")
public class RoleRpcController extends AbstractBaseRpcAdapter implements RoleRpcFeignClient {

	@Autowired
	private IRoleApplicationService iRoleApplicationService;
	@Autowired
	private IRoleService iRoleService;


	@Operation(summary = "添加角色")
	@PostMapping("/add")
	public RoleVO add(@RequestBody RoleCreateWithTenantIdCommand roleCreateWithTenantIdCommand){

		RoleDO roleDO = new RoleDO();
		roleDO.setCode(roleCreateWithTenantIdCommand.getCode());
		roleDO.setName(roleCreateWithTenantIdCommand.getName());
		roleDO.setIsDisabled(roleCreateWithTenantIdCommand.getIsDisabled());
		roleDO.setDisabledReason(roleCreateWithTenantIdCommand.getDisabledReason());
		roleDO.setIsSuperadmin(roleCreateWithTenantIdCommand.getIsSuperadmin());
		roleDO.setRemark(roleCreateWithTenantIdCommand.getRemark());
		roleDO.setSeq(roleCreateWithTenantIdCommand.getSeq());
		roleDO.setParentId(roleCreateWithTenantIdCommand.getParentId());
		roleDO.setTenantId(roleCreateWithTenantIdCommand.getTenantId());


		RoleDO add = iRoleService.add(roleDO);

		return RoleAppStructMapping.instance.roleDOToRoleVO(add);
	}

}