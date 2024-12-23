package com.particle.user.adapter.login;

import com.fasterxml.jackson.annotation.JsonView;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.captcha.endpoint.CaptchaVerifyCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.Response;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.global.security.security.config.WebSecurityConfig;
import com.particle.global.security.security.login.AbstractUserDetailsService;
import com.particle.global.security.security.login.LoginUser;
import com.particle.global.security.security.login.LoginUserTool;
import com.particle.global.security.tenant.GrantedTenant;
import com.particle.global.security.tenant.ITenantResolveService;
import com.particle.user.app.login.structmapping.UserLoginDeviceAppStructMapping;
import com.particle.user.app.login.structmapping.UserLoginRecordAppStructMapping;
import com.particle.user.client.api.IUserApplicationService;
import com.particle.user.client.dto.command.UserUpdateInfoCommand;
import com.particle.user.client.login.dto.command.LoginUserUpdateAvatarCommand;
import com.particle.user.client.login.dto.command.LoginUserUpdateGenderCommand;
import com.particle.user.client.login.dto.command.LoginUserUpdateNameCommand;
import com.particle.user.client.login.dto.command.LoginUserUpdateNicknameCommand;
import com.particle.user.client.login.dto.data.UserLoginDeviceVO;
import com.particle.user.client.login.dto.data.UserLoginRecordVO;
import com.particle.user.infrastructure.login.dos.UserLoginDeviceDO;
import com.particle.user.infrastructure.login.dos.UserLoginRecordDO;
import com.particle.user.infrastructure.login.service.IUserLoginDeviceService;
import com.particle.user.infrastructure.login.service.IUserLoginRecordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * <p>
 * 登录
 * </p>
 *
 * @author yangwei
 * @since 2022-11-25 16:46
 */
@Tag(name = "用户登录相关接口")
@RestController
@RequestMapping
public class UserLoginController {

	@Autowired
	private IUserLoginRecordService iUserLoginRecordService;
	@Autowired
	private IUserLoginDeviceService iUserLoginDeviceService;
	@Autowired
	private IUserApplicationService iUserApplicationService;

	/**
	 * 登录接口，这里只为了出接口文档 具体处理逻辑已经在 spring security 中处理
	 * @param loginCommand
	 * @return
	 */
	@JsonView(LoginUser.UserWebView.class)
	@Operation(summary = "登录")
	@Parameters({
			// header 参见{@link ParameterType}
			@Parameter(name = UserAuthenticationResultServiceImpl.login_header_device_id,description = "设备id，在登录日志是使用",in= ParameterIn.HEADER)
	})
	@PostMapping(value=WebSecurityConfig.login_processing_url,consumes= MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public SingleResponse<LoginUser> login( @Valid @ParameterObject LoginCommand loginCommand){
		return SingleResponse.buildSuccess();
	}


	/**
	 * 动态验证码登录接口，这里只为了出接口文档 具体处理逻辑已经在 spring security 中处理
	 * @param loginCommand
	 * @return
	 */
	@Operation(summary = "动态验证码登录")
	@Parameters({
			// header 参见{@link ParameterType}
			@Parameter(name = UserAuthenticationResultServiceImpl.login_header_device_id,description = "设备id，在登录日志是使用",in= ParameterIn.HEADER)
	})
	@PostMapping(value=UserDefaultLoginCustomWebSecurityConfigure.login_captcha_url,consumes= MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public SingleResponse<LoginUser> dynamicCaptchaLogin( @Valid @ParameterObject LoginCommand loginCommand){
		return SingleResponse.buildSuccess();
	}

	/**
	 * 登录接口，这里只为了出接口文档 具体处理逻辑已经在 spring security 中处理
	 * @return
	 */
	@Operation(summary = "退出登录")
	@PostMapping(value=WebSecurityConfig.logout_processing_url)
	public Response logout(){
		return Response.buildSuccess();
	}

	@Schema(description = "登录参数")
	@Data
	public static class LoginCommand{
		@NotEmpty(message = "登录用户名不能为空")
		@Schema(description = "登录用户名,动态验证码登录时支持邮件和手机号",requiredMode = Schema.RequiredMode.REQUIRED)
		private String username;

		@NotEmpty(message = "登录用户密码不能为空")
		@Schema(description = "登录用户密码，动态验证码登录时为动态验证码",requiredMode = Schema.RequiredMode.REQUIRED)
		private String password;


		/**
		 * 该字段名和 {@link CaptchaVerifyCommand#captchaUniqueIdentifierFieldName}保持一致
		 */
		@Schema(description = "验证码唯一标识,在开启验证码时必填")
		private String captchaUniqueIdentifier;

		/**
		 * 该字段名和 {@link CaptchaVerifyCommand#captchaValueFieldName}保持一致
		 */
		@Schema(description = "用户输入值,在开启验证码时必填、动态验证码登录时必填")
		private String captchaValue;
	}
	@Operation(summary = "判断用户是否登录")
	@GetMapping("/hasLogin")
	@ResponseStatus(HttpStatus.OK)
	public Response hasLogin() {
		boolean b = SecurityContextHolder.getContext().getAuthentication() != null &&
				SecurityContextHolder.getContext().getAuthentication().isAuthenticated() &&
				//when Anonymous Authentication is enabled
				!(SecurityContextHolder.getContext().getAuthentication()
						instanceof AnonymousAuthenticationToken);
		if (!b) {
			return Response.buildFailure(ErrorCodeGlobalEnum.UNAUTHORIZED_ERROR, "用户未登录");
		}
		return Response.buildSuccess();
	}

	@JsonView(LoginUser.UserWebView.class)
	@PreAuthorize("hasAuthority('user')")
	@Operation(summary = "获取当前登录用户信息")
	@GetMapping("/userinfo")
	@ResponseStatus(HttpStatus.OK)
	public SingleResponse<LoginUser> info(@Parameter(hidden = true) LoginUser loginUser) {
		return SingleResponse.of(loginUser);
	}


	@Autowired
	private AbstractUserDetailsService abstractUserDetailsService;
	@Autowired
	private ITenantResolveService iTenantResolveService;


	@Operation(summary = "切换当前登录用户租户")
	@PreAuthorize("hasAuthority('user')")
	@PostMapping("/changeTenant")
	@ResponseStatus(HttpStatus.OK)
	public SingleResponse<LoginUser> changeTenant(@Valid @RequestBody IdCommand idCommand, @Parameter(hidden = true) LoginUser loginUser, HttpServletRequest httpServletRequest) {

		loginUser.clearUserGrantedAuthorities();
		GrantedTenant grantedTenant = iTenantResolveService.resolveGrantedTenant(httpServletRequest,false);

		abstractUserDetailsService.loginUserDetailsFill(loginUser, idCommand.getId(), Optional.ofNullable(grantedTenant).map(GrantedTenant::getId).orElse(null));
		// 需要刷新一下权限，否则权限不会生效
		LoginUserTool.refreshAuthorities(loginUser.getAuthorities());
		return SingleResponse.of(loginUser);
	}

	@Operation(summary = "切换当前登录用户角色")
	@PreAuthorize("hasAuthority('user')")
	@PostMapping("/changeRole")
	@ResponseStatus(HttpStatus.OK)
	public SingleResponse<LoginUser> changeRole(@Valid @RequestBody IdCommand idCommand, @Parameter(hidden = true) LoginUser loginUser) {

		loginUser.changeRole(idCommand.getId());
		abstractUserDetailsService.loginUserDetailsFill(loginUser, Optional.ofNullable(loginUser.getCurrentTenant()).map(GrantedTenant::getId).orElse(null), Optional.ofNullable(loginUser.getCurrentTenant()).map(GrantedTenant::getId).orElse(null));

		// 需要刷新一下权限，否则权限不会生效
		LoginUserTool.refreshAuthorities(loginUser.getAuthorities());
		return SingleResponse.of(loginUser);
	}


	@Operation(summary = "获取当前登录用户的登录记录")
	@PreAuthorize("hasAuthority('user')")
	@GetMapping("/loginRecord")
	@ResponseStatus(HttpStatus.OK)
	public MultiResponse<UserLoginRecordVO> loginRecord(@Parameter(hidden = true) LoginUser loginUser) {
		List<UserLoginRecordDO> byUserId = iUserLoginRecordService.getByUserId(loginUser.getId());
		List<UserLoginRecordVO> userLoginRecordVOS = UserLoginRecordAppStructMapping.instance.userLoginRecordDOsToUserLoginRecordVOs(byUserId);
		return MultiResponse.of(userLoginRecordVOS);
	}

	@Operation(summary = "获取当前登录用户的登录设备")
	@PreAuthorize("hasAuthority('user')")
	@GetMapping("/loginDevice")
	@ResponseStatus(HttpStatus.OK)
	public MultiResponse<UserLoginDeviceVO> loginDevice(@Parameter(hidden = true) LoginUser loginUser) {
		List<UserLoginDeviceDO> byUserId = iUserLoginDeviceService.getByUserId(loginUser.getId());
		List<UserLoginDeviceVO> userLoginDeviceVOS = UserLoginDeviceAppStructMapping.instance.userLoginDeviceDOsToUserLoginDeviceVOs(byUserId);
		return MultiResponse.of(userLoginDeviceVOS);
	}

	/**
	 * 修改当前登录用户头像
	 * @param avatarCommand
	 * @param loginUser
	 * @return
	 */
	@Operation(summary = "修改当前登录用户头像")
	@PreAuthorize("hasAuthority('user')")
	@PostMapping("/user/update/avatar")
	@ResponseStatus(HttpStatus.OK)
	public Response userUpdateAvatar(@Valid @RequestBody LoginUserUpdateAvatarCommand avatarCommand, @Parameter(hidden = true) LoginUser loginUser) {
		UserUpdateInfoCommand userUpdateInfoCommand = UserUpdateInfoCommand.createByLoginUserUpdateAvatarCommand(avatarCommand,loginUser.getId());
		return iUserApplicationService.updateUserInfo(userUpdateInfoCommand);
	}
	/**
	 * 修改当前登录用户性别
	 * @param genderCommand
	 * @param loginUser
	 * @return
	 */
	@Operation(summary = "修改当前登录用户性别")
	@PreAuthorize("hasAuthority('user')")
	@PostMapping("/user/update/gender")
	@ResponseStatus(HttpStatus.OK)
	public Response userUpdateGender(@Valid @RequestBody LoginUserUpdateGenderCommand genderCommand, @Parameter(hidden = true) LoginUser loginUser) {
		UserUpdateInfoCommand userUpdateInfoCommand = UserUpdateInfoCommand.createByLoginUserUpdateGenderCommand(genderCommand,loginUser.getId());
		return iUserApplicationService.updateUserInfo(userUpdateInfoCommand);
	}
	/**
	 * 修改当前登录用户姓名
	 * @param nameCommand
	 * @param loginUser
	 * @return
	 */
	@Operation(summary = "修改当前登录用户姓名")
	@PreAuthorize("hasAuthority('user')")
	@PostMapping("/user/update/name")
	@ResponseStatus(HttpStatus.OK)
	public Response userUpdateName(@Valid @RequestBody LoginUserUpdateNameCommand nameCommand, @Parameter(hidden = true) LoginUser loginUser) {
		UserUpdateInfoCommand userUpdateInfoCommand = UserUpdateInfoCommand.createByLoginUserUpdateNameCommand(nameCommand,loginUser.getId());
		return iUserApplicationService.updateUserInfo(userUpdateInfoCommand);
	}
	/**
	 * 修改当前登录用户昵称
	 * @param nicknameCommand
	 * @param loginUser
	 * @return
	 */
	@Operation(summary = "修改当前登录用户昵称")
	@PreAuthorize("hasAuthority('user')")
	@PostMapping("/user/update/nickname")
	@ResponseStatus(HttpStatus.OK)
	public Response userUpdateNickname(@Valid @RequestBody LoginUserUpdateNicknameCommand nicknameCommand, @Parameter(hidden = true) LoginUser loginUser) {
		UserUpdateInfoCommand userUpdateInfoCommand = UserUpdateInfoCommand.createByLoginUserUpdateNicknameCommand(nicknameCommand,loginUser.getId());
		return iUserApplicationService.updateUserInfo(userUpdateInfoCommand);
	}
}
