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
import com.particle.user.app.login.structmapping.UserLoginDeviceAppStructMapping;
import com.particle.user.app.login.structmapping.UserLoginRecordAppStructMapping;
import com.particle.user.client.login.dto.data.UserLoginDeviceVO;
import com.particle.user.client.login.dto.data.UserLoginRecordVO;
import com.particle.user.infrastructure.login.dos.UserLoginDeviceDO;
import com.particle.user.infrastructure.login.dos.UserLoginRecordDO;
import com.particle.user.infrastructure.login.service.IUserLoginDeviceService;
import com.particle.user.infrastructure.login.service.IUserLoginRecordService;
import io.swagger.annotations.*;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * <p>
 * 登录
 * </p>
 *
 * @author yangwei
 * @since 2022-11-25 16:46
 */
@Api(tags = "用户登录相关接口")
@RestController
@RequestMapping
public class UserLoginController {

	@Autowired
	private IUserLoginRecordService iUserLoginRecordService;
	@Autowired
	private IUserLoginDeviceService iUserLoginDeviceService;

	/**
	 * 登录接口，这里只为了出接口文档 具体处理逻辑已经在 spring security 中处理
	 * @param loginCommand
	 * @return
	 */
	@JsonView(LoginUser.UserWebView.class)
	@ApiOperation("登录")
	@ApiImplicitParams({
			// header 参见{@link ParameterType}
			@ApiImplicitParam(name = UserAuthenticationResultServiceImpl.login_header_device_id,value = "设备id，在登录日志是使用",paramType="header")
	})
	@PostMapping(value=WebSecurityConfig.login_processing_url,consumes= MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public SingleResponse<LoginUser> login( @Valid LoginCommand loginCommand){
		return SingleResponse.buildSuccess();
	}


	/**
	 * 动态验证码登录接口，这里只为了出接口文档 具体处理逻辑已经在 spring security 中处理
	 * @param loginCommand
	 * @return
	 */
	@ApiOperation("动态验证码登录")
	@ApiImplicitParams({
			// header 参见{@link ParameterType}
			@ApiImplicitParam(name = UserAuthenticationResultServiceImpl.login_header_device_id,value = "设备id，在登录日志是使用",paramType="header")
	})
	@PostMapping(value=UserDefaultLoginCustomWebSecurityConfigure.login_captcha_url,consumes= MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public SingleResponse<LoginUser> dynamicCaptchaLogin( @Valid LoginCommand loginCommand){
		return SingleResponse.buildSuccess();
	}

	/**
	 * 登录接口，这里只为了出接口文档 具体处理逻辑已经在 spring security 中处理
	 * @return
	 */
	@ApiOperation("退出登录")
	@PostMapping(value=WebSecurityConfig.logout_processing_url)
	public Response logout(){
		return Response.buildSuccess();
	}

	@ApiModel("登录参数")
	@Data
	public static class LoginCommand{
		@NotEmpty(message = "登录用户名不能为空")
		@ApiModelProperty(value = "登录用户名,动态验证码登录时支持邮件和手机号",required = true)
		private String username;

		@NotEmpty(message = "登录用户密码不能为空")
		@ApiModelProperty(value = "登录用户密码，动态验证码登录时为动态验证码",required = true)
		private String password;


		/**
		 * 该字段名和 {@link CaptchaVerifyCommand#captchaUniqueIdentifierFieldName}保持一致
		 */
		@ApiModelProperty("验证码唯一标识,在开启验证码时必填")
		private String captchaUniqueIdentifier;

		/**
		 * 该字段名和 {@link CaptchaVerifyCommand#captchaValueFieldName}保持一致
		 */
		@ApiModelProperty("用户输入值,在开启验证码时必填、动态验证码登录时必填")
		private String captchaValue;
	}
	@ApiOperation("判断用户是否登录")
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
	@ApiOperation("获取当前登录用户信息")
	@GetMapping("/userinfo")
	@ResponseStatus(HttpStatus.OK)
	public SingleResponse<LoginUser> info(@ApiIgnore LoginUser loginUser) {
		return SingleResponse.of(loginUser);
	}


	@Autowired
	private AbstractUserDetailsService abstractUserDetailsService;

	@ApiOperation("切换当前登录用户租户")
	@PreAuthorize("hasAuthority('user')")
	@PostMapping("/changeTenant")
	@ResponseStatus(HttpStatus.OK)
	public SingleResponse<LoginUser> changeTenant(@Valid @RequestBody IdCommand idCommand, @ApiIgnore LoginUser loginUser) {

		loginUser.clearUserGrantedAuthorities();
		abstractUserDetailsService.loginUserDetailsFill(loginUser, idCommand.getId());
		// 需要刷新一下权限，否则权限不会生效
		LoginUserTool.refreshAuthorities(loginUser.getAuthorities());
		return SingleResponse.of(loginUser);
	}

	@ApiOperation("切换当前登录用户角色")
	@PreAuthorize("hasAuthority('user')")
	@PostMapping("/changeRole")
	@ResponseStatus(HttpStatus.OK)
	public SingleResponse<LoginUser> changeRole(@Valid @RequestBody IdCommand idCommand, @ApiIgnore LoginUser loginUser) {

		loginUser.changeRole(idCommand.getId());
		// 需要刷新一下权限，否则权限不会生效
		LoginUserTool.refreshAuthorities(loginUser.getAuthorities());
		return SingleResponse.of(loginUser);
	}


	@ApiOperation("获取当前登录用户的登录记录")
	@PreAuthorize("hasAuthority('user')")
	@GetMapping("/loginRecord")
	@ResponseStatus(HttpStatus.OK)
	public MultiResponse<UserLoginRecordVO> loginRecord(@ApiIgnore LoginUser loginUser) {
		List<UserLoginRecordDO> byUserId = iUserLoginRecordService.getByUserId(loginUser.getId());
		List<UserLoginRecordVO> userLoginRecordVOS = UserLoginRecordAppStructMapping.instance.userLoginRecordDOsToUserLoginRecordVOs(byUserId);
		return MultiResponse.of(userLoginRecordVOS);
	}

	@ApiOperation("获取当前登录用户的登录设备")
	@PreAuthorize("hasAuthority('user')")
	@GetMapping("/loginDevice")
	@ResponseStatus(HttpStatus.OK)
	public MultiResponse<UserLoginDeviceVO> loginDevice(@ApiIgnore LoginUser loginUser) {
		List<UserLoginDeviceDO> byUserId = iUserLoginDeviceService.getByUserId(loginUser.getId());
		List<UserLoginDeviceVO> userLoginDeviceVOS = UserLoginDeviceAppStructMapping.instance.userLoginDeviceDOsToUserLoginDeviceVOs(byUserId);
		return MultiResponse.of(userLoginDeviceVOS);
	}
}
