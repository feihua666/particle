package com.particle.role.adapter.web.front;

import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.component.light.share.dict.oplog.OpLogConstants;
import com.particle.global.dataaudit.op.OpLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.security.security.login.LoginUser;
import com.particle.role.adapter.web.admin.RoleAdminWebController;
import com.particle.role.client.api.IRoleApplicationService;
import com.particle.role.client.api.representation.IRoleRepresentationApplicationService;
import com.particle.role.client.dto.command.RoleCreateCommand;
import com.particle.role.client.dto.command.RoleUpdateCommand;
import com.particle.role.client.dto.command.representation.RolePageQueryCommand;
import com.particle.role.client.dto.command.representation.RoleQueryListCommand;
import com.particle.role.client.dto.data.RoleVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 角色前台应用pc或平板端前端适配器
 * 主要用于pc或平板端前台应用
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@Tag(name = "角色pc或平板端前台应用相关接口")
@RestController
@RequestMapping("/front/web/role")
public class RoleFrontWebController extends AbstractBaseWebAdapter {

	@Autowired
	private IRoleApplicationService iRoleApplicationService;
	@Autowired
	private IRoleRepresentationApplicationService iRoleRepresentationApplicationService;

	@PreAuthorize("hasAuthority('front:web:role:create')")
	@Operation(summary = "添加角色")
	@PostMapping("/create")
	@OpLog(name = "添加角色",module = OpLogConstants.Module.role,type = OpLogConstants.Type.create)
	public SingleResponse<RoleVO> create(@RequestBody RoleCreateCommand roleCreateCommand, @Parameter(hidden = true) LoginUser loginUser){
		RoleAdminWebController.superAdminCheck(LoginUser.super_admin_role.equals(roleCreateCommand.getCode()) || (roleCreateCommand.getIsSuperadmin() != null && roleCreateCommand.getIsSuperadmin()), loginUser);
		return iRoleApplicationService.create(roleCreateCommand);
	}

	@PreAuthorize("hasAuthority('front:web:role:delete')")
	@Operation(summary = "删除角色")
	@DeleteMapping("/delete")
	@OpLog(name = "删除角色",module = OpLogConstants.Module.role,type = OpLogConstants.Type.delete)
	public SingleResponse<RoleVO> delete(@RequestBody IdCommand roleDeleteCommand){
		return iRoleApplicationService.delete(roleDeleteCommand);
	}

	@PreAuthorize("hasAuthority('front:web:role:update')")
	@Operation(summary = "更新角色")
	@PutMapping("/update")
	@OpLog(name = "更新角色",module = OpLogConstants.Module.role,type = OpLogConstants.Type.update)
	public SingleResponse<RoleVO> update(@RequestBody RoleUpdateCommand roleUpdateCommand, @Parameter(hidden = true) LoginUser loginUser){

		RoleAdminWebController.superAdminCheck(LoginUser.super_admin_role.equals(roleUpdateCommand.getCode()) || (roleUpdateCommand.getIsSuperadmin() != null && roleUpdateCommand.getIsSuperadmin()), loginUser);
		return iRoleApplicationService.update(roleUpdateCommand);
	}

	@PreAuthorize("hasAuthority('front:web:role:update')")
	@Operation(summary = "角色更新详情")
	@GetMapping("/detail-for-update")
	public SingleResponse<RoleVO> queryDetailForUpdate(IdCommand roleQueryDetailForUpdateCommand){
		return iRoleRepresentationApplicationService.queryDetailForUpdate(roleQueryDetailForUpdateCommand);
	}

	@PreAuthorize("hasAuthority('front:web:role:detail')")
	@Operation(summary = "角色详情展示")
	@GetMapping("/detail")
	public SingleResponse<RoleVO> queryDetail(IdCommand roleQueryDetailCommand){
		return iRoleRepresentationApplicationService.queryDetail(roleQueryDetailCommand);
	}

	@PreAuthorize("hasAuthority('front:web:role:queryList')")
	@Operation(summary = "列表查询角色")
	@GetMapping("/list")
	public MultiResponse<RoleVO> queryList(RoleQueryListCommand roleQueryListCommand){
		return iRoleRepresentationApplicationService.queryList(roleQueryListCommand);
	}

	@PreAuthorize("hasAuthority('front:web:role:pageQuery')")
	@Operation(summary = "分页查询角色")
	@GetMapping("/page")
	public PageResponse<RoleVO> pageQueryList(RolePageQueryCommand rolePageQueryCommand){
		return iRoleRepresentationApplicationService.pageQuery(rolePageQueryCommand);
	}
}
