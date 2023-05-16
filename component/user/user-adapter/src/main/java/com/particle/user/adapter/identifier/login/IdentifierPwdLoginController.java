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
import com.particle.user.client.identifier.dto.command.UserIdentifierUpdatePasswordCommand;
import com.particle.user.client.identifier.dto.data.UserIdentifierPwdVO;
import com.particle.user.infrastructure.identifier.dos.UserIdentifierPwdDO;
import com.particle.user.infrastructure.identifier.service.IUserIdentifierPwdService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

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
@Api(tags = "用户账号登录标识密码，登录用户相关")
public class IdentifierPwdLoginController {

	@Autowired
	private IUserIdentifierPwdService iUserIdentifierPwdService;
	@Autowired
	private IUserIdentifierPwdApplicationService iUserIdentifierPwdApplicationService;

	@ApiOperation("获取当前登录用户的登录标识密码")
	@PreAuthorize("hasAuthority('user')")
	@GetMapping("/identifier-pwd")
	@ResponseStatus(HttpStatus.OK)
	public MultiResponse<UserIdentifierPwdVO> identifierPwd(@ApiIgnore LoginUser loginUser) {
		List<UserIdentifierPwdDO> byUserId = iUserIdentifierPwdService.getByUserId(loginUser.getId());
		List<UserIdentifierPwdVO> userIdentifierPwdVOS = UserIdentifierPwdAppStructMapping.instance.userIdentifierPwdDOsToUserIdentifierPwdVOs(byUserId);
		return MultiResponse.of(userIdentifierPwdVOS);
	}

	@ApiOperation("修改当前登录用户的登录标识密码")
	@PreAuthorize("hasAuthority('user')")
	@PostMapping("/identifier-pwd-update")
	@ResponseStatus(HttpStatus.OK)
	public Response identifierPwdUpdate(@Valid @RequestBody UserIdentifierUpdatePasswordCommand userIdentifierUpdatePasswordCommand, @ApiIgnore LoginUser loginUser) {
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
}
