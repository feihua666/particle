package com.particle.role.adapter.roleuserrel.web.front;

import com.particle.common.client.dto.command.IdCommand;
import com.particle.component.light.share.dict.oplog.OpLogConstants;
import com.particle.global.dataaudit.op.OpLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.Response;
import com.particle.global.dto.response.SingleResponse;
import com.particle.role.client.roleuserrel.api.IRoleUserRelApplicationService;
import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.role.client.roleuserrel.api.representation.IRoleUserRelRepresentationApplicationService;
import com.particle.role.client.roleuserrel.dto.command.RoleAssignUserCommand;
import com.particle.role.client.roleuserrel.dto.command.RoleUserRelCreateCommand;
import com.particle.role.client.roleuserrel.dto.command.UserAssignRoleCommand;
import com.particle.role.client.roleuserrel.dto.command.representation.RoleUserRelPageQueryCommand;
import com.particle.role.client.roleuserrel.dto.command.representation.RoleUserRelQueryListCommand;
import com.particle.role.client.roleuserrel.dto.data.RoleUserRelVO;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 角色用户关系前台应用pc或平板端前端适配器
 * 主要用于pc或平板端前台应用
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@Tag(name = "角色用户关系pc或平板端前台应用相关接口")
@RestController
@RequestMapping("/front/web/role-user-rel")
public class RoleUserRelFrontWebController extends AbstractBaseWebAdapter {


	@Autowired
	private IRoleUserRelApplicationService iRoleUserRelApplicationService;
	@Autowired
	private IRoleUserRelRepresentationApplicationService iRoleUserRelRepresentationApplicationService;

	@PreAuthorize("hasAuthority('front:web:roleUserRel:create')")
	@Operation(summary = "添加角色用户关系")
	@PostMapping("/create")
	@OpLog(name = "添加角色用户关系",module = OpLogConstants.Module.role,type = OpLogConstants.Type.create)
	public SingleResponse<RoleUserRelVO> create(@RequestBody RoleUserRelCreateCommand roleUserRelCreateCommand){
		return iRoleUserRelApplicationService.create(roleUserRelCreateCommand);
	}

	@PreAuthorize("hasAuthority('front:web:roleUserRel:delete')")
	@Operation(summary = "删除角色用户关系")
	@DeleteMapping("/delete")
	@OpLog(name = "删除角色用户关系",module = OpLogConstants.Module.role,type = OpLogConstants.Type.delete)
	public SingleResponse<RoleUserRelVO> delete(@RequestBody IdCommand roleUserRelDeleteCommand){
		return iRoleUserRelApplicationService.delete(roleUserRelDeleteCommand);
	}

	@PreAuthorize("hasAuthority('front:web:roleUserRel:detail')")
	@Operation(summary = "角色用户关系详情展示")
	@GetMapping("/detail")
	public SingleResponse<RoleUserRelVO> queryDetail(IdCommand roleUserRelQueryDetailCommand){
		return iRoleUserRelRepresentationApplicationService.queryDetail(roleUserRelQueryDetailCommand);
	}

	@PreAuthorize("hasAuthority('front:web:roleUserRel:queryList')")
	@Operation(summary = "列表查询角色用户关系")
	@GetMapping("/list")
	public MultiResponse<RoleUserRelVO> queryList(RoleUserRelQueryListCommand roleUserRelQueryListCommand){
		return iRoleUserRelRepresentationApplicationService.queryList(roleUserRelQueryListCommand);
	}

	@PreAuthorize("hasAuthority('front:web:roleUserRel:pageQuery')")
	@Operation(summary = "分页查询角色用户关系")
	@GetMapping("/page")
	public PageResponse<RoleUserRelVO> pageQueryList(RoleUserRelPageQueryCommand roleUserRelPageQueryCommand){
		return iRoleUserRelRepresentationApplicationService.pageQuery(roleUserRelPageQueryCommand);
	}

	@Operation(summary = "角色分配用户")
	@PreAuthorize("hasAuthority('front:web:roleUserRel:roleAssignUser')")
	@PostMapping("/role/assign/user")
	@ResponseStatus(HttpStatus.CREATED)
	@OpLog(name = "角色分配用户",module = OpLogConstants.Module.role,type = OpLogConstants.Type.relAsign)
	public Response roleAssignUser(@RequestBody RoleAssignUserCommand cf) {
		return iRoleUserRelApplicationService.roleAssignUser(cf);
	}

	@Operation(summary = "根据角色ID查询已分配的用户id")
	@PreAuthorize("hasAuthority('front:web:roleUserRel:queryUserIdsByRoleId')")
	@GetMapping("/queryUserIdsByRoleId")
	@ResponseStatus(HttpStatus.OK)
	public MultiResponse<Long> queryUserIdsByRoleId(IdCommand roleIdCommand) {
		return iRoleUserRelRepresentationApplicationService.queryUserIdsByRoleId( roleIdCommand);
	}

	@Operation(summary = "清空角色下的所有用户")
	@PreAuthorize("hasAuthority('front:web:roleUserRel:deleteByRoleId')")
	@DeleteMapping("/deleteByRoleId")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@OpLog(name = "清空角色下的所有用户",module = OpLogConstants.Module.role,type = OpLogConstants.Type.delete)
	public Response deleteByRoleId(@RequestBody IdCommand roleIdCommand) {
		return iRoleUserRelApplicationService.deleteByRoleId(roleIdCommand);
	}


	@Operation(summary = "用户分配角色")
	@PreAuthorize("hasAuthority('front:web:roleUserRel:userAssignRole')")
	@PostMapping("/user/assign/role")
	@ResponseStatus(HttpStatus.CREATED)
	@OpLog(name = "用户分配角色",module = OpLogConstants.Module.role,type = OpLogConstants.Type.delete)
	public Response userAssignRole(@RequestBody UserAssignRoleCommand cf) {
		return iRoleUserRelApplicationService.userAssignRole(cf);
	}

	@Operation(summary = "根据用户ID查询已分配的角色id")
	@PreAuthorize("hasAuthority('front:web:roleUserRel:queryRoleIdsByUserId')")
	@GetMapping("/queryRoleIdsByUserId")
	@ResponseStatus(HttpStatus.OK)
	public MultiResponse<Long> queryRoleIdsByUserId( IdCommand userIdCommand) {
		return iRoleUserRelRepresentationApplicationService.queryRoleIdsByUserId( userIdCommand);

	}

	@Operation(summary = "清空用户下的所有角色")
	@PreAuthorize("hasAuthority('front:web:roleUserRel:deleteByUserId')")
	@DeleteMapping("/deleteByUserId")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@OpLog(name = "清空用户下的所有角色",module = OpLogConstants.Module.role,type = OpLogConstants.Type.delete)
	public Response deleteByUserId(@RequestBody IdCommand roleIdCommand) {
		return iRoleUserRelApplicationService.deleteByUserId(roleIdCommand);
	}
}