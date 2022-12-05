package com.particle.role.adapter.rolefuncrel.web.admin;

import com.particle.role.client.rolefuncrel.api.IRoleFuncRelApplicationService;
import com.particle.role.client.rolefuncrel.api.representation.IRoleFuncRelRepresentationApplicationService;
import com.particle.role.client.rolefuncrel.dto.command.RoleFuncRelCreateCommand;
import com.particle.role.client.rolefuncrel.dto.data.RoleFuncRelVO;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.role.client.rolefuncrel.dto.command.RoleFuncRelUpdateCommand;
import com.particle.role.client.rolefuncrel.dto.command.representation.RoleFuncRelPageQueryCommand;
import com.particle.role.client.rolefuncrel.dto.command.representation.RoleFuncRelQueryListCommand;
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
 * 角色菜单功能关系后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@Api(tags = "角色菜单功能关系pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/role-func-rel")
public class RoleFuncRelAdminWebController extends AbstractBaseWebAdapter {

	@Autowired
	private IRoleFuncRelApplicationService iRoleFuncRelApplicationService;
	@Autowired
	private IRoleFuncRelRepresentationApplicationService iRoleFuncRelRepresentationApplicationService;

	@PreAuthorize("hasAuthority('admin:web:roleFuncRel:create')")
	@ApiOperation("添加角色菜单功能关系")
	@PostMapping("/create")
	public SingleResponse<RoleFuncRelVO> create(@RequestBody RoleFuncRelCreateCommand roleFuncRelCreateCommand){
		return iRoleFuncRelApplicationService.create(roleFuncRelCreateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:roleFuncRel:delete')")
	@ApiOperation("删除角色菜单功能关系")
	@DeleteMapping("/delete")
	public SingleResponse<RoleFuncRelVO> delete(@RequestBody IdCommand roleFuncRelDeleteCommand){
		return iRoleFuncRelApplicationService.delete(roleFuncRelDeleteCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:roleFuncRel:update')")
	@ApiOperation("更新角色菜单功能关系")
	@PutMapping("/update")
	public SingleResponse<RoleFuncRelVO> update(@RequestBody RoleFuncRelUpdateCommand roleFuncRelUpdateCommand){
		return iRoleFuncRelApplicationService.update(roleFuncRelUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:roleFuncRel:update')")
	@ApiOperation("角色菜单功能关系更新详情")
	@GetMapping("/detail-for-update")
	public SingleResponse<RoleFuncRelVO> queryDetailForUpdate(IdCommand roleFuncRelQueryDetailForUpdateCommand){
		return iRoleFuncRelRepresentationApplicationService.queryDetailForUpdate(roleFuncRelQueryDetailForUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:roleFuncRel:detail')")
	@ApiOperation("角色菜单功能关系详情展示")
	@GetMapping("/detail")
	public SingleResponse<RoleFuncRelVO> queryDetail(IdCommand roleFuncRelQueryDetailCommand){
		return iRoleFuncRelRepresentationApplicationService.queryDetail(roleFuncRelQueryDetailCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:roleFuncRel:queryList')")
	@ApiOperation("列表查询角色菜单功能关系")
	@GetMapping("/list")
	public MultiResponse<RoleFuncRelVO> queryList(RoleFuncRelQueryListCommand roleFuncRelQueryListCommand){
		return iRoleFuncRelRepresentationApplicationService.queryList(roleFuncRelQueryListCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:roleFuncRel:pageQuery')")
	@ApiOperation("分页查询角色菜单功能关系")
	@GetMapping("/page")
	public PageResponse<RoleFuncRelVO> pageQueryList(RoleFuncRelPageQueryCommand roleFuncRelPageQueryCommand){
		return iRoleFuncRelRepresentationApplicationService.pageQuery(roleFuncRelPageQueryCommand);
	}

}