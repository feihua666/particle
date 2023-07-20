package com.particle.user.adapter.identifier.login;

import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.Response;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.global.security.security.PasswordEncryptEnum;
import com.particle.global.security.security.login.LoginUser;
import com.particle.user.adapter.tool.PasswordTool;
import com.particle.user.app.identifier.structmapping.UserIdentifierPwdAppStructMapping;
import com.particle.user.client.identifier.api.IUserIdentifierPwdApplicationService;
import com.particle.user.client.identifier.dto.command.*;
import com.particle.user.client.identifier.dto.data.UserIdentifierPwdVO;
import com.particle.user.infrastructure.identifier.dos.UserIdentifierDO;
import com.particle.user.infrastructure.identifier.dos.UserIdentifierPwdDO;
import com.particle.user.infrastructure.identifier.service.IUserIdentifierPwdService;
import com.particle.user.infrastructure.identifier.service.IUserIdentifierService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 用户账号登录标识密码，登录用户相关
 * </p>
 *
 * @author yangwei
 * @since 2023-05-04 16:59:38
 */
@RestController
@RequestMapping("/user-identifier-pwd/login")
@Tag(name = "用户账号登录标识密码，登录用户相关")
public class IdentifierPwdLoginController {

	@Autowired
	private IUserIdentifierPwdService iUserIdentifierPwdService;
	@Autowired
	private IUserIdentifierService iUserIdentifierService;
	@Autowired
	private IUserIdentifierPwdApplicationService iUserIdentifierPwdApplicationService;

	/**
	 * 密码支持不同的登录标识设置独立的密码
	 * @param loginUser
	 * @return
	 */
	@Operation(summary = "获取当前登录用户的登录标识密码")
	@PreAuthorize("hasAuthority('user')")
	@GetMapping("/identifier-pwd")
	@ResponseStatus(HttpStatus.OK)
	public MultiResponse<UserIdentifierPwdVO> identifierPwd(@Parameter(hidden = true) LoginUser loginUser) {
		List<UserIdentifierPwdDO> byUserId = iUserIdentifierPwdService.getByUserId(loginUser.getId());
		List<UserIdentifierPwdVO> userIdentifierPwdVOS = UserIdentifierPwdAppStructMapping.instance.userIdentifierPwdDOsToUserIdentifierPwdVOs(byUserId);
		return MultiResponse.of(userIdentifierPwdVOS);
	}

	/**
	 * 根据登录标识修改密码
	 * @param userIdentifierUpdatePasswordCommand
	 * @param loginUser
	 * @return
	 */
	@Operation(summary = "修改当前登录用户的登录标识密码")
	@PreAuthorize("hasAuthority('user')")
	@PostMapping("/identifier-pwd-update")
	@ResponseStatus(HttpStatus.OK)
	public Response identifierPwdUpdate(@Valid @RequestBody UserIdentifierUpdatePasswordCommand userIdentifierUpdatePasswordCommand, @Parameter(hidden = true) LoginUser loginUser) {
		UserIdentifierPwdDO byUserId = iUserIdentifierPwdService.getByIdentifierId(userIdentifierUpdatePasswordCommand.getUserIdentifierId());
		// 不匹配说明是手动传参
		Assert.isTrue(loginUser.getId().equals(byUserId.getUserId()),ErrorCodeGlobalEnum.ILLEGAL_REQUEST_ERROR);
		boolean b = PasswordEncryptEnum.matchPassword(userIdentifierUpdatePasswordCommand.getOldPassword(), byUserId.getPwdEncryptFlag(), byUserId.getPwd());
		if (!b) {
			return Response.buildFailure(ErrorCodeGlobalEnum.INVALID_PASSWORD_ERROR,"原密码不正确");
		}
		PasswordTool.encodePassword(userIdentifierUpdatePasswordCommand);
		return iUserIdentifierPwdApplicationService.identifierResetPassword(userIdentifierUpdatePasswordCommand);
	}

	/**
	 * 会修改用户所有登录标识的密码密码
	 * @param userUpdatePwdCommand
	 * @return
	 */
	@PreAuthorize("hasAuthority('user')")
	@Operation(summary = "修改当前登录用户密码")
	@PostMapping("/user/updatePassword")
	public Response userUpdatePassword(@Valid @RequestBody UserUpdatePwdCommand userUpdatePwdCommand,@Parameter(hidden = true) LoginUser loginUser){
		List<UserIdentifierPwdDO> byUserId = iUserIdentifierPwdService.getByUserId(loginUser.getId());
		boolean isPasswordValid = false;
		// 有一个匹配说明密码正确
		for (UserIdentifierPwdDO userIdentifierPwdDO : byUserId) {
			isPasswordValid = PasswordEncryptEnum.matchPassword(userUpdatePwdCommand.getOldPassword(), userIdentifierPwdDO.getPwdEncryptFlag(), userIdentifierPwdDO.getPwd());
			if (!isPasswordValid) {
				continue;
			}
		}
		if (!isPasswordValid) {
			return Response.buildFailure(ErrorCodeGlobalEnum.INVALID_PASSWORD_ERROR,"原密码不正确");
		}
		// 上面校验原密码没问题，直接重置
		UserResetPwdCommand userResetPwdCommand = userIdentifierPwdCommandToUserResetPwdCommand(userUpdatePwdCommand, loginUser.getId());
		PasswordTool.encodePassword(userResetPwdCommand);
		return iUserIdentifierPwdApplicationService.userResetPassword(userResetPwdCommand);
	}


	/**
	 * 会修改用户所有登录标识的密码密码
	 * 该方法不能裸奔，必须配置动态验证码
	 * @param userFindBackPwdCommand
	 * @return
	 */
	@Operation(summary = "用户找回密码")
	@PostMapping("/user/findBackPassword")
	public Response findBackPassword(@Valid @RequestBody UserFindBackPwdCommand userFindBackPwdCommand){
		UserIdentifierDO byIdentifier = iUserIdentifierService.getByIdentifier(userFindBackPwdCommand.getIdentifier());
		if (byIdentifier == null) {
			Response.buildFailure(ErrorCodeGlobalEnum.INVALID_ACCOUNT_ERROR, "账号不存在");
		}

		UserResetPwdCommand userResetPwdCommand = userIdentifierPwdCommandToUserResetPwdCommand(userFindBackPwdCommand,byIdentifier.getUserId());
		PasswordTool.encodePassword(userResetPwdCommand);

		return iUserIdentifierPwdApplicationService.userResetPassword(userResetPwdCommand);
	}

	/**
	 * 映射
	 * @param userIdentifierPwdCommand
	 * @param userId
	 * @return
	 */
	private UserResetPwdCommand userIdentifierPwdCommandToUserResetPwdCommand(UserIdentifierPwdCommand userIdentifierPwdCommand,Long userId) {
		UserResetPwdCommand userResetPwdCommand = new UserResetPwdCommand();
		userResetPwdCommand.setPassword(userIdentifierPwdCommand.getPassword());
		userResetPwdCommand.setIsPwdExpired(userIdentifierPwdCommand.getIsPwdExpired());
		userResetPwdCommand.setPwdExpiredReason(userIdentifierPwdCommand.getPwdExpiredReason());
		userResetPwdCommand.setPwdExpireAt(userIdentifierPwdCommand.getPwdExpireAt());
		userResetPwdCommand.setIsPwdNeedUpdate(userIdentifierPwdCommand.getIsPwdNeedUpdate());
		userResetPwdCommand.setPwdNeedUpdateMessage(userIdentifierPwdCommand.getPwdNeedUpdateMessage());

		userResetPwdCommand.setUserId(userId);
		return userResetPwdCommand;
	}
}
