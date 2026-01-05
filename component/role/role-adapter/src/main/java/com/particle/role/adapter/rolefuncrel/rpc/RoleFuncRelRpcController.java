package com.particle.role.adapter.rolefuncrel.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.Response;
import com.particle.role.adapter.feign.client.rolefuncrel.rpc.RoleFuncRelRpcFeignClient;
import com.particle.role.app.rolefuncrel.structmapping.RoleFuncRelAppStructMapping;
import com.particle.role.client.rolefuncrel.api.IRoleFuncRelApplicationService;
import com.particle.role.client.rolefuncrel.api.representation.IRoleFuncRelRepresentationApplicationService;
import com.particle.role.client.rolefuncrel.dto.command.RoleFuncRelDeleteWithTenantIdCommand;
import com.particle.role.client.rolefuncrel.dto.command.representation.RoleFuncRelQueryListByFuncIdsCommand;
import com.particle.role.client.rolefuncrel.dto.command.representation.RoleFuncRelQueryListByRoleIdsCommand;
import com.particle.role.client.rolefuncrel.dto.command.representation.RoleFuncRelQueryListCommand;
import com.particle.role.client.rolefuncrel.dto.data.RoleFuncRelVO;
import com.particle.role.infrastructure.rolefuncrel.dos.RoleFuncRelDO;
import com.particle.role.infrastructure.rolefuncrel.service.IRoleFuncRelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 角色菜单功能关系远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@Tag(name = "角色菜单功能关系远程调用相关接口")
@RestController
@RequestMapping("/rpc/role-func-rel")
public class RoleFuncRelRpcController extends AbstractBaseRpcAdapter implements RoleFuncRelRpcFeignClient {

	@Autowired
	private IRoleFuncRelApplicationService iRoleFuncRelApplicationService;
    @Autowired
    private IRoleFuncRelRepresentationApplicationService iRoleFuncRelRepresentationApplicationService;
    @Autowired
    private IRoleFuncRelService iRoleFuncRelService;

	@Operation(summary = "删除功能id范围外的角色功能关系数据")
	@Override
	public Response deleteWithTenantId(@RequestBody RoleFuncRelDeleteWithTenantIdCommand roleFuncRelDeleteWithTenantIdCommand) {
		return iRoleFuncRelApplicationService.deleteWithTenantId(roleFuncRelDeleteWithTenantIdCommand);
	}
    @Operation(summary = "列表查询角色菜单功能关系")
    @Override
    public MultiResponse<RoleFuncRelVO> queryList(RoleFuncRelQueryListCommand roleFuncRelQueryListCommand) {
        return iRoleFuncRelRepresentationApplicationService.queryList(roleFuncRelQueryListCommand);
    }
    @Operation(summary = "根据roleIds角色功能关系数据")
    @Override
    public MultiResponse<RoleFuncRelVO> queryListByRoleIds(RoleFuncRelQueryListByRoleIdsCommand roleFuncRelQueryListByRoleIdsCommand) {
        List<RoleFuncRelDO> roleFuncRelDOS = iRoleFuncRelService.listByRoleIds(roleFuncRelQueryListByRoleIdsCommand.getIds());
        List<RoleFuncRelVO> roleFuncRelVOS = RoleFuncRelAppStructMapping.instance.roleFuncRelDOsToRoleFuncRelVOs(roleFuncRelDOS);
        return MultiResponse.of(roleFuncRelVOS);
    }
    @Operation(summary = "根据funcIds角色功能关系数据")
    @Override
    public MultiResponse<RoleFuncRelVO> queryListByFuncIds(RoleFuncRelQueryListByFuncIdsCommand roleFuncRelQueryListByFuncIdsCommand) {
        List<RoleFuncRelDO> roleFuncRelDOS = iRoleFuncRelService.listByFuncIds(roleFuncRelQueryListByFuncIdsCommand.getIds());
        List<RoleFuncRelVO> roleFuncRelVOS = RoleFuncRelAppStructMapping.instance.roleFuncRelDOsToRoleFuncRelVOs(roleFuncRelDOS);
        return MultiResponse.of(roleFuncRelVOS);
    }
}
