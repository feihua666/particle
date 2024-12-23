package com.particle.role.adapter.roledatascoperel.web.admin;

import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.component.light.share.dict.oplog.OpLogConstants;
import com.particle.global.dataaudit.op.OpLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.Response;
import com.particle.global.dto.response.SingleResponse;
import com.particle.role.client.roledatascoperel.api.IRoleDataScopeRelApplicationService;
import com.particle.role.client.roledatascoperel.api.representation.IRoleDataScopeRelRepresentationApplicationService;
import com.particle.role.client.roledatascoperel.dto.command.DataScopeAssignRoleCommand;
import com.particle.role.client.roledatascoperel.dto.command.RoleAssignDataScopeCommand;
import com.particle.role.client.roledatascoperel.dto.command.RoleDataScopeRelCreateCommand;
import com.particle.role.client.roledatascoperel.dto.command.RoleDataScopeRelUpdateCommand;
import com.particle.role.client.roledatascoperel.dto.command.representation.RoleDataScopeRelPageQueryCommand;
import com.particle.role.client.roledatascoperel.dto.command.representation.RoleDataScopeRelQueryListCommand;
import com.particle.role.client.roledatascoperel.dto.data.RoleDataScopeRelVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
/**
 * <p>
 * 角色数据范围关系后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2024-07-01 16:45:06
 */
@Tag(name = "角色数据范围关系pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/role_data_scope_rel")
public class RoleDataScopeRelAdminWebController extends AbstractBaseWebAdapter {

	@Autowired
	private IRoleDataScopeRelApplicationService iRoleDataScopeRelApplicationService;
	@Autowired
	private IRoleDataScopeRelRepresentationApplicationService iRoleDataScopeRelRepresentationApplicationService;

	@PreAuthorize("hasAuthority('admin:web:roleDataScopeRel:create')")
	@Operation(summary = "添加角色数据范围关系")
	@PostMapping("/create")
	@OpLog(name = "添加角色数据范围关系",module = OpLogConstants.Module.role,type = OpLogConstants.Type.create)
	public SingleResponse<RoleDataScopeRelVO> create(@RequestBody RoleDataScopeRelCreateCommand roleDataScopeRelCreateCommand){
		return iRoleDataScopeRelApplicationService.create(roleDataScopeRelCreateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:roleDataScopeRel:delete')")
	@Operation(summary = "删除角色数据范围关系")
	@DeleteMapping("/delete")
	@OpLog(name = "删除角色数据范围关系",module = OpLogConstants.Module.role,type = OpLogConstants.Type.delete)
	public SingleResponse<RoleDataScopeRelVO> delete(@RequestBody IdCommand deleteCommand){
		return iRoleDataScopeRelApplicationService.delete(deleteCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:roleDataScopeRel:update')")
	@Operation(summary = "更新角色数据范围关系")
	@PutMapping("/update")
	@OpLog(name = "更新角色数据范围关系",module = OpLogConstants.Module.role,type = OpLogConstants.Type.update)
	public SingleResponse<RoleDataScopeRelVO> update(@RequestBody RoleDataScopeRelUpdateCommand roleDataScopeRelUpdateCommand){
		return iRoleDataScopeRelApplicationService.update(roleDataScopeRelUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:roleDataScopeRel:update')")
	@Operation(summary = "角色数据范围关系更新详情")
	@GetMapping("/detail-for-update")
	public SingleResponse<RoleDataScopeRelVO> queryDetailForUpdate(IdCommand detailForUpdateCommand){
		return iRoleDataScopeRelRepresentationApplicationService.queryDetailForUpdate(detailForUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:roleDataScopeRel:detail')")
	@Operation(summary = "角色数据范围关系详情展示")
	@GetMapping("/detail")
	public SingleResponse<RoleDataScopeRelVO> queryDetail(IdCommand detailCommand){
		return iRoleDataScopeRelRepresentationApplicationService.queryDetail(detailCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:roleDataScopeRel:queryList')")
	@Operation(summary = "列表查询角色数据范围关系")
	@GetMapping("/list")
	public MultiResponse<RoleDataScopeRelVO> queryList(RoleDataScopeRelQueryListCommand roleDataScopeRelQueryListCommand){
		return iRoleDataScopeRelRepresentationApplicationService.queryList(roleDataScopeRelQueryListCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:roleDataScopeRel:pageQuery')")
	@Operation(summary = "分页查询角色数据范围关系")
	@GetMapping("/page")
	public PageResponse<RoleDataScopeRelVO> pageQueryList(RoleDataScopeRelPageQueryCommand roleDataScopeRelPageQueryCommand){
		return iRoleDataScopeRelRepresentationApplicationService.pageQuery(roleDataScopeRelPageQueryCommand);
	}

	@Operation(summary = "角色分配数据范围")
	@PreAuthorize("hasAuthority('admin:web:roleDataScopeRel:roleAssignDataScope')")
	@PostMapping("/role/assign/dataScope")
	@ResponseStatus(HttpStatus.CREATED)
	@OpLog(name = "角色分配数据范围",module = OpLogConstants.Module.role,type = OpLogConstants.Type.relAsign)
	public Response roleAssignDataScope(@RequestBody RoleAssignDataScopeCommand cf) {
		return iRoleDataScopeRelApplicationService.roleAssignDataScope(cf);
	}

	@Operation(summary = "根据角色ID查询已分配的数据范围id")
	@PreAuthorize("hasAuthority('admin:web:roleDataScopeRel:queryDataScopeIdsByRoleId')")
	@GetMapping("/queryDataScopeIdsByRoleId")
	@ResponseStatus(HttpStatus.OK)
	public MultiResponse<Long> queryDataScopeIdsByRoleId(IdCommand idCommand) {
		return iRoleDataScopeRelRepresentationApplicationService.queryDataScopeIdsByRoleId( idCommand);
	}

	@Operation(summary = "清空角色下的所有数据范围")
	@PreAuthorize("hasAuthority('admin:web:roleDataScopeRel:deleteByRoleId')")
	@DeleteMapping("/deleteByRoleId")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@OpLog(name = "清空角色下的所有数据范围",module = OpLogConstants.Module.role,type = OpLogConstants.Type.delete)
	public Response deleteByRoleId(@RequestBody IdCommand idCommand) {
		return iRoleDataScopeRelApplicationService.deleteByRoleId(idCommand);
	}


	@Operation(summary = "数据范围分配角色")
	@PreAuthorize("hasAuthority('admin:web:roleDataScopeRel:dataScopeAssignRole')")
	@PostMapping("/dataScope/assign/role")
	@ResponseStatus(HttpStatus.CREATED)
	@OpLog(name = "数据范围分配角色",module = OpLogConstants.Module.role,type = OpLogConstants.Type.relAsign)
	public Response dataScopeAssignRole(@RequestBody DataScopeAssignRoleCommand cf) {
		return iRoleDataScopeRelApplicationService.dataScopeAssignRole(cf);
	}

	@Operation(summary = "根据数据范围ID查询已分配的角色id")
	@PreAuthorize("hasAuthority('admin:web:roleDataScopeRel:queryRoleIdsByDataScopeId')")
	@GetMapping("/queryRoleIdsByDataScopeId")
	@ResponseStatus(HttpStatus.OK)
	public MultiResponse<Long> queryByDataScopeId(IdCommand idCommand) {
		return iRoleDataScopeRelRepresentationApplicationService.queryRoleIdsByDataScopeId( idCommand);

	}

	@Operation(summary = "清空数据范围下的所有角色")
	@PreAuthorize("hasAuthority('admin:web:roleDataScopeRel:deleteByDataScopeId')")
	@DeleteMapping("/deleteByDataScopeId")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@OpLog(name = "清空数据范围下的所有角色",module = OpLogConstants.Module.role,type = OpLogConstants.Type.delete)
	public Response deleteByDataScopeId(@RequestBody IdCommand idCommand) {
		return iRoleDataScopeRelApplicationService.deleteByDataScopeId(idCommand);
	}

}
