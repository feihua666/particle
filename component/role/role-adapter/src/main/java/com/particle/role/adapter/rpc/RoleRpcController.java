package com.particle.role.adapter.rpc;

import com.google.common.collect.Lists;
import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.global.dto.dataconstraint.DataConstraintContext;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.role.adapter.feign.client.rpc.RoleRpcFeignClient;
import com.particle.role.app.structmapping.RoleAppStructMapping;
import com.particle.role.client.api.IRoleApplicationService;
import com.particle.role.client.api.representation.IRoleRepresentationApplicationService;
import com.particle.role.client.dto.command.RoleCreateWithTenantIdCommand;
import com.particle.role.client.dto.command.representation.*;
import com.particle.role.client.dto.data.RoleVO;
import com.particle.role.infrastructure.dos.RoleDO;
import com.particle.role.infrastructure.service.IRoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    private IRoleRepresentationApplicationService iRoleRepresentationApplicationService;

    @Autowired
    private IRoleService iRoleService;
	@Operation(summary = "添加角色")
	@Override
	public SingleResponse<RoleVO> createWithTenantId(@RequestBody RoleCreateWithTenantIdCommand roleCreateWithTenantIdCommand){
        return iRoleApplicationService.createWithTenantId(roleCreateWithTenantIdCommand);
	}

    @Operation(summary = "根据用户id查询角色")
    @Override
    public MultiResponse<RoleVO> queryListByUserId(RoleQueryListByUserIdCommand roleQueryListByUserIdCommand) {
        List<RoleDO> roleDOS = iRoleService.listByUserId(roleQueryListByUserIdCommand.getId(), roleQueryListByUserIdCommand.getIsDisabled());
        List<RoleVO> roleVOS = RoleAppStructMapping.instance.roleDOsToRoleVOs(roleDOS);
        return MultiResponse.of(roleVOS);
    }
    @Operation(summary = "根据用户ids查询角色")
    @Override
    public MultiResponse<RoleVO> queryListByUserIds(RoleQueryListByUserIdsCommand roleQueryListByUserIdsCommand) {
        List<RoleDO> roleDOS = iRoleService.listByUserIds(roleQueryListByUserIdsCommand.getIds(), roleQueryListByUserIdsCommand.getIsDisabled());
        List<RoleVO> roleVOS = RoleAppStructMapping.instance.roleDOsToRoleVOs(roleDOS);
        return MultiResponse.of(roleVOS);
    }
    @Operation(summary = "根据角色id查询角色")
    @Override
    public SingleResponse<RoleVO> queryListByRoleId(RoleQueryListByRoleIdCommand roleQueryListByRoleIdCommand) {
        List<RoleDO> roleDOList = iRoleService.listByRoleIds(Lists.newArrayList(roleQueryListByRoleIdCommand.getId()), roleQueryListByRoleIdCommand.getIsDisabled());
        List<RoleVO> roleVOS = RoleAppStructMapping.instance.roleDOsToRoleVOs(roleDOList);
        RoleVO roleVO = null;
        if (roleVOS.size() > 0) {
            roleVO = roleVOS.get(0);
        }
        return SingleResponse.of(roleVO);
    }
    @Operation(summary = "根据角色id查询角色")
    @Override
    public MultiResponse<RoleVO> queryListByRoleIds(RoleQueryListByRoleIdsCommand roleQueryListByRoleIdsCommand) {
        List<RoleDO> roleDOS = iRoleService.listByRoleIds(roleQueryListByRoleIdsCommand.getIds(), roleQueryListByRoleIdsCommand.getIsDisabled());
        List<RoleVO> roleVOS = RoleAppStructMapping.instance.roleDOsToRoleVOs(roleDOS);
        return MultiResponse.of(roleVOS);
    }
    @Operation(summary = "列表查询角色")
    @Override
    public MultiResponse<RoleVO> queryList(RoleQueryListCommand roleQueryListCommand){
        return iRoleRepresentationApplicationService.queryList(roleQueryListCommand);
    }
}
