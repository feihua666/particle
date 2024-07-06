package com.particle.user.adapter.web.admin;

import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.component.light.share.dict.oplog.OpLogConstants;
import com.particle.global.dataaudit.op.OpLog;
import com.particle.global.dto.dataconstraint.DataConstraintContext;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.user.adapter.tool.PasswordTool;
import com.particle.user.app.structmapping.UserAppStructMapping;
import com.particle.user.client.api.IUserApplicationService;
import com.particle.user.client.api.representation.IUserRepresentationApplicationService;
import com.particle.user.client.dto.command.UserCreateCommand;
import com.particle.user.client.dto.command.UserUpdateCommand;
import com.particle.user.client.dto.command.representation.UserPageQueryCommand;
import com.particle.user.client.dto.command.representation.UserQueryListCommand;
import com.particle.user.client.dto.data.UserVO;
import com.particle.user.client.dto.data.UserWithRoleVO;
import com.particle.user.client.identifier.dto.command.UserIdentifierPwdCommand;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@Tag(name = "用户pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/user")
public class UserAdminWebController extends AbstractBaseWebAdapter {

	@Autowired
	private IUserApplicationService iUserApplicationService;
	@Autowired
	private IUserRepresentationApplicationService iUserRepresentationApplicationService;

	@PreAuthorize("hasAuthority('admin:web:user:create')")
	@Operation(summary = "添加用户")
	@PostMapping("/create")
	@OpLog(name = "添加用户",module = OpLogConstants.Module.user,type = OpLogConstants.Type.create)
	public SingleResponse<UserVO> create(@RequestBody UserCreateCommand userCreateCommand,@RequestBody UserIdentifierPwdCommand userIdentifierPwdCommand){
		PasswordTool.encodePassword(userIdentifierPwdCommand);

		return iUserApplicationService.create(userCreateCommand, userIdentifierPwdCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:user:delete')")
	@Operation(summary = "删除用户")
	@DeleteMapping("/delete")
	@OpLog(name = "删除用户",module = OpLogConstants.Module.user,type = OpLogConstants.Type.delete)
	public SingleResponse<UserVO> delete(@RequestBody IdCommand deleteCommand){
		deleteCommand.dcdo(DataConstraintConstants.data_object_user_user, DataConstraintContext.Action.delete.name());
		return iUserApplicationService.delete(deleteCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:user:update')")
	@Operation(summary = "更新用户")
	@PutMapping("/update")
	@OpLog(name = "更新用户",module = OpLogConstants.Module.user,type = OpLogConstants.Type.update)
	public SingleResponse<UserVO> update(@RequestBody UserUpdateCommand userUpdateCommand){
		userUpdateCommand.dcdo(DataConstraintConstants.data_object_user_user,DataConstraintContext.Action.update.name());
		return iUserApplicationService.update(userUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:user:update')")
	@Operation(summary = "用户更新详情")
	@GetMapping("/detail-for-update")
	public SingleResponse<UserVO> queryDetailForUpdate(IdCommand userQueryDetailForUpdateCommand){
		return iUserRepresentationApplicationService.queryDetailForUpdate(userQueryDetailForUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:user:detail')")
	@Operation(summary = "用户详情展示")
	@GetMapping("/detail")
	public SingleResponse<UserVO> queryDetail(IdCommand userQueryDetailCommand){
		return iUserRepresentationApplicationService.queryDetail(userQueryDetailCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:user:queryList')")
	@Operation(summary = "列表查询用户")
	@GetMapping("/list")
	public MultiResponse<UserVO> queryList(UserQueryListCommand userQueryListCommand){
		userQueryListCommand.dcdo(DataConstraintConstants.data_object_user_user,DataConstraintContext.Action.query.name());
		MultiResponse<UserVO> userVOMultiResponse = iUserRepresentationApplicationService.queryList(userQueryListCommand);

		if (userVOMultiResponse.isNotEmpty() && userQueryListCommand.getIsIncludeRoleInfo() != null && userQueryListCommand.getIsIncludeRoleInfo()) {
			List<UserVO> collect = userVOMultiResponse.getData().stream().map(item -> UserAppStructMapping.instance.mapUserWithRoleVO(item)).collect(Collectors.toList());
			userVOMultiResponse.setData(collect);
		}
		return userVOMultiResponse;
	}

	@PreAuthorize("hasAuthority('admin:web:user:pageQuery')")
	@Operation(summary = "分页查询用户")
	@GetMapping("/page")
	public PageResponse<UserVO> pageQueryList(UserPageQueryCommand userPageQueryCommand){
		userPageQueryCommand.dcdo(DataConstraintConstants.data_object_user_user,DataConstraintContext.Action.query.name());
		PageResponse<UserVO> userVOPageResponse = iUserRepresentationApplicationService.pageQuery(userPageQueryCommand);
		if (userVOPageResponse.isNotEmpty() && userPageQueryCommand.getIsIncludeRoleInfo() != null && userPageQueryCommand.getIsIncludeRoleInfo()) {
			List<UserVO> collect = userVOPageResponse.getData().stream().map(item -> UserAppStructMapping.instance.mapUserWithRoleVO(item)).collect(Collectors.toList());
			userVOPageResponse.setData(collect);
		}
		return userVOPageResponse;
	}


}