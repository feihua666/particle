package com.particle.role.adapter.rolefuncrel.web.admin;

import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.Response;
import com.particle.global.dto.response.SingleResponse;
import com.particle.role.client.rolefuncrel.api.IRoleFuncRelApplicationService;
import com.particle.role.client.rolefuncrel.api.representation.IRoleFuncRelRepresentationApplicationService;
import com.particle.role.client.rolefuncrel.dto.command.RoleFuncRelCreateCommand;
import com.particle.role.client.rolefuncrel.dto.command.representation.RoleFuncRelPageQueryCommand;
import com.particle.role.client.rolefuncrel.dto.command.representation.RoleFuncRelQueryListCommand;
import com.particle.role.client.rolefuncrel.dto.data.RoleFuncRelVO;
import com.particle.role.client.rolefuncrel.dto.command.FuncAssignRoleCommand;
import com.particle.role.client.rolefuncrel.dto.command.RoleAssignFuncCommand;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 角色菜单功能关系后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@Tag(name = "角色菜单功能关系pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/role-func-rel")
public class RoleFuncRelAdminWebController extends AbstractBaseWebAdapter {

	@Autowired
	private IRoleFuncRelApplicationService iRoleFuncRelApplicationService;
	@Autowired
	private IRoleFuncRelRepresentationApplicationService iRoleFuncRelRepresentationApplicationService;

	@PreAuthorize("hasAuthority('admin:web:roleFuncRel:create')")
	@Operation(summary = "添加角色菜单功能关系")
	@PostMapping("/create")
	public SingleResponse<RoleFuncRelVO> create(@RequestBody RoleFuncRelCreateCommand roleFuncRelCreateCommand){
		return iRoleFuncRelApplicationService.create(roleFuncRelCreateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:roleFuncRel:delete')")
	@Operation(summary = "删除角色菜单功能关系")
	@DeleteMapping("/delete")
	public SingleResponse<RoleFuncRelVO> delete(@RequestBody IdCommand roleFuncRelDeleteCommand){
		return iRoleFuncRelApplicationService.delete(roleFuncRelDeleteCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:roleFuncRel:detail')")
	@Operation(summary = "角色菜单功能关系详情展示")
	@GetMapping("/detail")
	public SingleResponse<RoleFuncRelVO> queryDetail(IdCommand roleFuncRelQueryDetailCommand){
		return iRoleFuncRelRepresentationApplicationService.queryDetail(roleFuncRelQueryDetailCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:roleFuncRel:queryList')")
	@Operation(summary = "列表查询角色菜单功能关系")
	@GetMapping("/list")
	public MultiResponse<RoleFuncRelVO> queryList(RoleFuncRelQueryListCommand roleFuncRelQueryListCommand){
		return iRoleFuncRelRepresentationApplicationService.queryList(roleFuncRelQueryListCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:roleFuncRel:pageQuery')")
	@Operation(summary = "分页查询角色菜单功能关系")
	@GetMapping("/page")
	public PageResponse<RoleFuncRelVO> pageQueryList(RoleFuncRelPageQueryCommand roleFuncRelPageQueryCommand){
		return iRoleFuncRelRepresentationApplicationService.pageQuery(roleFuncRelPageQueryCommand);
	}

	@Operation(summary = "角色分配功能")
	@PreAuthorize("hasAuthority('admin:web:roleFuncRel:roleAssignFunc')")
	@PostMapping("/role/assign/func")
	@ResponseStatus(HttpStatus.CREATED)
	public Response roleAssignFunc(@RequestBody RoleAssignFuncCommand cf) {
		return iRoleFuncRelApplicationService.roleAssignFunc(cf);
	}

	@Operation(summary = "根据角色ID查询已分配的功能id")
	@PreAuthorize("hasAuthority('admin:web:roleFuncRel:queryFuncIdsByRoleId')")
	@GetMapping("/queryFuncIdsByRoleId")
	@ResponseStatus(HttpStatus.OK)
	public MultiResponse<Long> queryFuncIdsByRoleId(IdCommand roleIdCommand) {
		return iRoleFuncRelRepresentationApplicationService.queryFuncIdsByRoleId( roleIdCommand);
	}

	@Operation(summary = "清空角色下的所有功能")
	@PreAuthorize("hasAuthority('admin:web:roleFuncRel:deleteByRoleId')")
	@DeleteMapping("/deleteByRoleId")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public Response deleteByRoleId(@RequestBody IdCommand roleIdCommand) {
		return iRoleFuncRelApplicationService.deleteByRoleId(roleIdCommand);
	}


	@Operation(summary = "功能分配角色")
	@PreAuthorize("hasAuthority('admin:web:roleFuncRel:funcAssignRole')")
	@PostMapping("/func/assign/role")
	@ResponseStatus(HttpStatus.CREATED)
	public Response funcAssignRole(@RequestBody FuncAssignRoleCommand cf) {
		return iRoleFuncRelApplicationService.funcAssignRole(cf);
	}

	@Operation(summary = "根据功能ID查询已分配的角色id")
	@PreAuthorize("hasAuthority('admin:web:roleFuncRel:queryRoleIdsByFuncId')")
	@GetMapping("/queryRoleIdsByFuncId")
	@ResponseStatus(HttpStatus.OK)
	public MultiResponse<Long> queryByFuncId(IdCommand funcIdCommand) {
		return iRoleFuncRelRepresentationApplicationService.queryRoleIdsByFuncId( funcIdCommand);

	}

	@Operation(summary = "清空功能下的所有角色")
	@PreAuthorize("hasAuthority('admin:web:roleFuncRel:deleteByFuncId')")
	@DeleteMapping("/deleteByFuncId")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public Response deleteByFuncId(@RequestBody IdCommand roleIdCommand) {
		return iRoleFuncRelApplicationService.deleteByFuncId(roleIdCommand);
	}
}