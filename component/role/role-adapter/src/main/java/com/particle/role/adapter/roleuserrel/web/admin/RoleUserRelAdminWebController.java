package com.particle.role.adapter.roleuserrel.web.admin;

import com.particle.role.client.roleuserrel.api.IRoleUserRelApplicationService;
import com.particle.role.client.roleuserrel.api.representation.IRoleUserRelRepresentationApplicationService;
import com.particle.role.client.roleuserrel.dto.command.RoleUserRelCreateCommand;
import com.particle.role.client.roleuserrel.dto.data.RoleUserRelVO;
import com.particle.role.client.roleuserrel.dto.command.representation.RoleUserRelQueryDetailForUpdateCommand;
import com.particle.role.client.roleuserrel.dto.command.representation.RoleUserRelQueryDetailCommand;
import com.particle.role.client.roleuserrel.dto.command.RoleUserRelDeleteCommand;
import com.particle.role.client.roleuserrel.dto.command.RoleUserRelUpdateCommand;
import com.particle.role.client.roleuserrel.dto.command.representation.RoleUserRelPageQueryCommand;
import com.particle.role.client.roleuserrel.dto.command.representation.RoleUserRelQueryListCommand;
import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.global.dto.response.SingleResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
/**
 * <p>
 * 角色用户关系后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@Api(tags = "角色用户关系pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/role-user-rel")
public class RoleUserRelAdminWebController extends AbstractBaseWebAdapter {

	@Autowired
	private IRoleUserRelApplicationService iRoleUserRelApplicationService;
	@Autowired
	private IRoleUserRelRepresentationApplicationService iRoleUserRelRepresentationApplicationService;

	@PreAuthorize("hasAuthority('admin:web:roleUserRel:create')")
	@ApiOperation("添加角色用户关系")
	@PostMapping("/create")
	public SingleResponse<RoleUserRelVO> create(@RequestBody RoleUserRelCreateCommand roleUserRelCreateCommand){
		return iRoleUserRelApplicationService.create(roleUserRelCreateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:roleUserRel:delete')")
	@ApiOperation("删除角色用户关系")
	@DeleteMapping("/delete")
	public SingleResponse<RoleUserRelVO> delete(@RequestBody RoleUserRelDeleteCommand roleUserRelDeleteCommand){
		return iRoleUserRelApplicationService.delete(roleUserRelDeleteCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:roleUserRel:update')")
	@ApiOperation("更新角色用户关系")
	@PutMapping("/update")
	public SingleResponse<RoleUserRelVO> update(@RequestBody RoleUserRelUpdateCommand roleUserRelUpdateCommand){
		return iRoleUserRelApplicationService.update(roleUserRelUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:roleUserRel:update')")
	@ApiOperation("角色用户关系更新详情")
	@GetMapping("/detail-for-update")
	public SingleResponse<RoleUserRelVO> queryDetailForUpdate(RoleUserRelQueryDetailForUpdateCommand roleUserRelQueryDetailForUpdateCommand){
		return iRoleUserRelRepresentationApplicationService.queryDetailForUpdate(roleUserRelQueryDetailForUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:roleUserRel:detail')")
	@ApiOperation("角色用户关系详情展示")
	@GetMapping("/detail")
	public SingleResponse<RoleUserRelVO> queryDetail(RoleUserRelQueryDetailCommand roleUserRelQueryDetailCommand){
		return iRoleUserRelRepresentationApplicationService.queryDetail(roleUserRelQueryDetailCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:roleUserRel:queryList')")
	@ApiOperation("列表查询角色用户关系")
	@GetMapping("/list")
	public MultiResponse<RoleUserRelVO> queryList(RoleUserRelQueryListCommand roleUserRelQueryListCommand){
		return iRoleUserRelRepresentationApplicationService.queryList(roleUserRelQueryListCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:roleUserRel:pageQuery')")
	@ApiOperation("分页查询角色用户关系")
	@GetMapping("/page")
	public PageResponse<RoleUserRelVO> pageQueryList(RoleUserRelPageQueryCommand roleUserRelPageQueryCommand){
		return iRoleUserRelRepresentationApplicationService.pageQuery(roleUserRelPageQueryCommand);
	}

}