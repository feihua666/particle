package com.particle.user.adapter.web.admin;

import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.component.light.share.dict.oplog.OpLogConstants;
import com.particle.global.dataaudit.op.OpLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.user.adapter.tool.PasswordTool;
import com.particle.user.client.api.IUserApplicationService;
import com.particle.user.client.api.representation.IUserRepresentationApplicationService;
import com.particle.user.client.dto.command.UserCreateCommand;
import com.particle.user.client.dto.command.UserUpdateCommand;
import com.particle.user.client.dto.command.representation.UserPageQueryCommand;
import com.particle.user.client.dto.command.representation.UserQueryListCommand;
import com.particle.user.client.dto.data.UserVO;
import com.particle.user.client.identifier.dto.command.UserIdentifierPwdCommand;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
/**
 * <p>
 * 用户后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@Api(tags = "用户pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/user")
public class UserAdminWebController extends AbstractBaseWebAdapter {

	@Autowired
	private IUserApplicationService iUserApplicationService;
	@Autowired
	private IUserRepresentationApplicationService iUserRepresentationApplicationService;

	@PreAuthorize("hasAuthority('admin:web:user:create')")
	@ApiOperation("添加用户")
	@PostMapping("/create")
	@OpLog(name = "添加用户",module = OpLogConstants.Module.user,type = OpLogConstants.Type.create)
	public SingleResponse<UserVO> create(@RequestBody UserCreateCommand userCreateCommand,@RequestBody UserIdentifierPwdCommand userIdentifierPwdCommand){
		PasswordTool.encodePassword(userIdentifierPwdCommand);

		return iUserApplicationService.create(userCreateCommand, userIdentifierPwdCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:user:delete')")
	@ApiOperation("删除用户")
	@DeleteMapping("/delete")
	@OpLog(name = "删除用户",module = OpLogConstants.Module.user,type = OpLogConstants.Type.delete)
	public SingleResponse<UserVO> delete(@RequestBody IdCommand userDeleteCommand){
		return iUserApplicationService.delete(userDeleteCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:user:update')")
	@ApiOperation("更新用户")
	@PutMapping("/update")
	@OpLog(name = "更新用户",module = OpLogConstants.Module.user,type = OpLogConstants.Type.update)
	public SingleResponse<UserVO> update(@RequestBody UserUpdateCommand userUpdateCommand){
		return iUserApplicationService.update(userUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:user:update')")
	@ApiOperation("用户更新详情")
	@GetMapping("/detail-for-update")
	public SingleResponse<UserVO> queryDetailForUpdate(IdCommand userQueryDetailForUpdateCommand){
		return iUserRepresentationApplicationService.queryDetailForUpdate(userQueryDetailForUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:user:detail')")
	@ApiOperation("用户详情展示")
	@GetMapping("/detail")
	public SingleResponse<UserVO> queryDetail(IdCommand userQueryDetailCommand){
		return iUserRepresentationApplicationService.queryDetail(userQueryDetailCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:user:queryList')")
	@ApiOperation("列表查询用户")
	@GetMapping("/list")
	public MultiResponse<UserVO> queryList(UserQueryListCommand userQueryListCommand){
		return iUserRepresentationApplicationService.queryList(userQueryListCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:user:pageQuery')")
	@ApiOperation("分页查询用户")
	@GetMapping("/page")
	public PageResponse<UserVO> pageQueryList(UserPageQueryCommand userPageQueryCommand){
		return iUserRepresentationApplicationService.pageQuery(userPageQueryCommand);
	}

}