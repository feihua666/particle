package com.particle.user.adapter.identifier.web.admin;

import cn.hutool.core.util.StrUtil;
import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.component.light.share.dataconstraint.DataConstraintConstants;
import com.particle.global.dto.dataconstraint.DataConstraintContext;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.Response;
import com.particle.global.dto.response.SingleResponse;
import com.particle.user.adapter.tool.PasswordTool;
import com.particle.user.client.identifier.api.IUserIdentifierPwdApplicationService;
import com.particle.user.client.identifier.api.representation.IUserIdentifierPwdRepresentationApplicationService;
import com.particle.user.client.identifier.dto.command.*;
import com.particle.user.client.identifier.dto.command.representation.UserIdentifierPwdPageQueryCommand;
import com.particle.user.client.identifier.dto.command.representation.UserIdentifierPwdQueryListCommand;
import com.particle.user.client.identifier.dto.data.UserIdentifierPwdVO;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 用户密码后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@Tag(name = "用户密码pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/user-identifier-pwd")
public class UserIdentifierPwdAdminWebController extends AbstractBaseWebAdapter {

	@Autowired
	private IUserIdentifierPwdApplicationService iUserIdentifierPwdApplicationService;
	@Autowired
	private IUserIdentifierPwdRepresentationApplicationService iUserIdentifierPwdRepresentationApplicationService;

	@PreAuthorize("hasAuthority('admin:web:userIdentifierPwd:create')")
	@Operation(summary = "添加用户密码")
	@PostMapping("/create")
	public SingleResponse<UserIdentifierPwdVO> create(@RequestBody UserIdentifierPwdCreateCommand userIdentifierPwdCreateCommand,@RequestBody UserIdentifierPwdCommand userIdentifierPwdCommand){
		PasswordTool.encodePassword(userIdentifierPwdCommand);

		return iUserIdentifierPwdApplicationService.create(userIdentifierPwdCreateCommand, userIdentifierPwdCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:userIdentifierPwd:delete')")
	@Operation(summary = "删除用户密码")
	@DeleteMapping("/delete")
	public SingleResponse<UserIdentifierPwdVO> delete(@RequestBody IdCommand deleteCommand){
		deleteCommand.dcdo(DataConstraintConstants.data_object_user_identifier_pwd, DataConstraintContext.Action.delete.name());
		return iUserIdentifierPwdApplicationService.delete(deleteCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:userIdentifierPwd:update')")
	@Operation(summary = "更新用户密码")
	@PutMapping("/update")
	public SingleResponse<UserIdentifierPwdVO> update(@RequestBody UserIdentifierPwdUpdateCommand userIdentifierPwdUpdateCommand){
		userIdentifierPwdUpdateCommand.dcdo(DataConstraintConstants.data_object_user_identifier_pwd,DataConstraintContext.Action.update.name());
		return iUserIdentifierPwdApplicationService.update(userIdentifierPwdUpdateCommand);
	}

	/**
	 * 加密密码
	 * @param userIdentifierPwdUpdateCommand
	 */
	public static void encodePassword(UserIdentifierPwdUpdateCommand userIdentifierPwdUpdateCommand) {
		if (StrUtil.isNotEmpty(userIdentifierPwdUpdateCommand.getPwd())) {

			UserIdentifierPwdCommand userIdentifierPwdCommand = new UserIdentifierPwdCommand();
			userIdentifierPwdCommand.setPassword(userIdentifierPwdUpdateCommand.getPwd());
			PasswordTool.encodePassword(userIdentifierPwdCommand);

			userIdentifierPwdUpdateCommand.setPwd(userIdentifierPwdCommand.getPwdEncoded());
			userIdentifierPwdUpdateCommand.setComplexity(userIdentifierPwdCommand.getPwdComplexity());
			userIdentifierPwdUpdateCommand.setPwdEncryptFlag(userIdentifierPwdCommand.getPwdEncryptFlag());

		}else {

			userIdentifierPwdUpdateCommand.setPwd(null);
			userIdentifierPwdUpdateCommand.setComplexity(null);
			userIdentifierPwdUpdateCommand.setPwdEncryptFlag(null);

		}
	}

	@PreAuthorize("hasAuthority('admin:web:userIdentifierPwd:update')")
	@Operation(summary = "用户密码更新详情")
	@GetMapping("/detail-for-update")
	public SingleResponse<UserIdentifierPwdVO> queryDetailForUpdate(IdCommand userIdentifierPwdQueryDetailForUpdateCommand){
		return iUserIdentifierPwdRepresentationApplicationService.queryDetailForUpdate(userIdentifierPwdQueryDetailForUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:userIdentifierPwd:detail')")
	@Operation(summary = "用户密码详情展示")
	@GetMapping("/detail")
	public SingleResponse<UserIdentifierPwdVO> queryDetail(IdCommand userIdentifierPwdQueryDetailCommand){
		return iUserIdentifierPwdRepresentationApplicationService.queryDetail(userIdentifierPwdQueryDetailCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:userIdentifierPwd:queryList')")
	@Operation(summary = "列表查询用户密码")
	@GetMapping("/list")
	public MultiResponse<UserIdentifierPwdVO> queryList(UserIdentifierPwdQueryListCommand userIdentifierPwdQueryListCommand){
		userIdentifierPwdQueryListCommand.dcdo(DataConstraintConstants.data_object_user_identifier_pwd,DataConstraintContext.Action.query.name());
		return iUserIdentifierPwdRepresentationApplicationService.queryList(userIdentifierPwdQueryListCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:userIdentifierPwd:pageQuery')")
	@Operation(summary = "分页查询用户密码")
	@GetMapping("/page")
	public PageResponse<UserIdentifierPwdVO> pageQueryList(UserIdentifierPwdPageQueryCommand userIdentifierPwdPageQueryCommand){
		userIdentifierPwdPageQueryCommand.dcdo(DataConstraintConstants.data_object_user_identifier_pwd,DataConstraintContext.Action.query.name());
		return iUserIdentifierPwdRepresentationApplicationService.pageQuery(userIdentifierPwdPageQueryCommand);
	}
	@PreAuthorize("hasAuthority('admin:web:userIdentifierPwd:identifier:resetPassword')")
	@Operation(summary = "重置用户登录标识密码")
	@PostMapping("/identifier/resetPassword")
	public Response identifierResetPassword(@RequestBody UserIdentifierResetPasswordCommand userIdentifierResetPasswordCommand){
		PasswordTool.encodePassword(userIdentifierResetPasswordCommand);
		return iUserIdentifierPwdApplicationService.identifierResetPassword(userIdentifierResetPasswordCommand);
	}

	/**
	 * 会重置用户所有登录标识的密码密码
	 * @param userResetPasswordCommand
	 * @return
	 */
	@PreAuthorize("hasAuthority('admin:web:userIdentifierPwd:user:resetPassword')")
	@Operation(summary = "重置用户密码")
	@PostMapping("/user/resetPassword")
	public Response userResetPassword(@RequestBody UserResetPwdCommand userResetPasswordCommand){
		PasswordTool.encodePassword(userResetPasswordCommand);
		return iUserIdentifierPwdApplicationService.userResetPassword(userResetPasswordCommand);
	}

}