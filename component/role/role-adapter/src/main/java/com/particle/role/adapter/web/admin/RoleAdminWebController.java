package com.particle.role.adapter.web.admin;

import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.role.client.api.IRoleApplicationService;
import com.particle.role.client.api.representation.IRoleRepresentationApplicationService;
import com.particle.role.client.dto.command.RoleCreateCommand;
import com.particle.role.client.dto.command.RoleUpdateCommand;
import com.particle.role.client.dto.command.representation.RolePageQueryCommand;
import com.particle.role.client.dto.command.representation.RoleQueryListCommand;
import com.particle.role.client.dto.data.RoleVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
/**
 * <p>
 * 角色后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@Api(tags = "角色pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/role")
public class RoleAdminWebController extends AbstractBaseWebAdapter {

	@Autowired
	private IRoleApplicationService iRoleApplicationService;
	@Autowired
	private IRoleRepresentationApplicationService iRoleRepresentationApplicationService;

	@PreAuthorize("hasAuthority('admin:web:role:create')")
	@ApiOperation("添加角色")
	@PostMapping("/create")
	public SingleResponse<RoleVO> create(@RequestBody RoleCreateCommand roleCreateCommand){
		return iRoleApplicationService.create(roleCreateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:role:delete')")
	@ApiOperation("删除角色")
	@DeleteMapping("/delete")
	public SingleResponse<RoleVO> delete(@RequestBody IdCommand roleDeleteCommand){
		return iRoleApplicationService.delete(roleDeleteCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:role:update')")
	@ApiOperation("更新角色")
	@PutMapping("/update")
	public SingleResponse<RoleVO> update(@RequestBody RoleUpdateCommand roleUpdateCommand){
		return iRoleApplicationService.update(roleUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:role:update')")
	@ApiOperation("角色更新详情")
	@GetMapping("/detail-for-update")
	public SingleResponse<RoleVO> queryDetailForUpdate(IdCommand roleQueryDetailForUpdateCommand){
		return iRoleRepresentationApplicationService.queryDetailForUpdate(roleQueryDetailForUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:role:detail')")
	@ApiOperation("角色详情展示")
	@GetMapping("/detail")
	public SingleResponse<RoleVO> queryDetail(IdCommand roleQueryDetailCommand){
		return iRoleRepresentationApplicationService.queryDetail(roleQueryDetailCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:role:queryList')")
	@ApiOperation("列表查询角色")
	@GetMapping("/list")
	public MultiResponse<RoleVO> queryList(RoleQueryListCommand roleQueryListCommand){
		return iRoleRepresentationApplicationService.queryList(roleQueryListCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:role:pageQuery')")
	@ApiOperation("分页查询角色")
	@GetMapping("/page")
	public PageResponse<RoleVO> pageQueryList(RolePageQueryCommand rolePageQueryCommand){
		return iRoleRepresentationApplicationService.pageQuery(rolePageQueryCommand);
	}

}