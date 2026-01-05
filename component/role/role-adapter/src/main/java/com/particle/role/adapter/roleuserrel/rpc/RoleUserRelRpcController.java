package com.particle.role.adapter.roleuserrel.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.common.client.dto.command.BatchIdCommand;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.Response;
import com.particle.global.dto.response.SingleResponse;
import com.particle.role.adapter.feign.client.roleuserrel.rpc.RoleUserRelRpcFeignClient;
import com.particle.role.app.roleuserrel.structmapping.RoleUserRelAppStructMapping;
import com.particle.role.client.roleuserrel.api.IRoleUserRelApplicationService;
import com.particle.role.client.roleuserrel.api.representation.IRoleUserRelRepresentationApplicationService;
import com.particle.role.client.roleuserrel.dto.command.RoleUserRelWithTenantIdCreateCommand;
import com.particle.role.client.roleuserrel.dto.command.UserAssignRoleCommand;
import com.particle.role.client.roleuserrel.dto.command.representation.RoleUserRelQueryListCommand;
import com.particle.role.client.roleuserrel.dto.data.RoleUserRelVO;
import com.particle.role.infrastructure.roleuserrel.dos.RoleUserRelDO;
import com.particle.role.infrastructure.roleuserrel.service.IRoleUserRelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    private IRoleUserRelRepresentationApplicationService iRoleUserRelRepresentationApplicationService;
    @Autowired
    private IRoleUserRelService roleUserRelService;

    @Operation(summary = "添加角色用户关系")
    @Override
	public SingleResponse<RoleUserRelVO> createWithTenantId(@RequestBody RoleUserRelWithTenantIdCreateCommand roleUserRelCreateCommand){
        return iRoleUserRelApplicationService.create(roleUserRelCreateCommand);
	}
    @Operation(summary = "用户分配角色")
    @Override
    public Response userAssignRole(@RequestBody UserAssignRoleCommand cf) {
        return iRoleUserRelApplicationService.userAssignRole(cf);
    }

    @Operation(summary = "清空用户下的所有角色")
    @Override
    public Response deleteByUserId(@RequestBody IdCommand userIdCommand) {
        return iRoleUserRelApplicationService.deleteByUserId(userIdCommand);
    }
    @Operation(summary = "列表查询角色用户关系")
    @Override
    public MultiResponse<RoleUserRelVO> queryList(RoleUserRelQueryListCommand roleUserRelQueryListCommand){
        return iRoleUserRelRepresentationApplicationService.queryList(roleUserRelQueryListCommand);
    }
    @Operation(summary = "根据roleIds查询角色用户关系")
    @Override
    public MultiResponse<RoleUserRelVO> queryListByRoleIds(BatchIdCommand batchIdCommand) {
        List<RoleUserRelDO> roleUserRelDOS = roleUserRelService.getByRoleIds(batchIdCommand.getIds());
        List<RoleUserRelVO> roleUserRelVOS = RoleUserRelAppStructMapping.instance.roleUserRelDOsToRoleUserRelVOs(roleUserRelDOS);

        return MultiResponse.of(roleUserRelVOS);
    }
}
