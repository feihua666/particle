package com.particle.user.adapter.login;

import com.particle.global.dto.response.Response;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.global.security.security.config.WebSecurityConfig;
import com.particle.global.security.security.login.LoginUser;
import com.particle.user.client.dto.command.UserCreateCommand;
import com.particle.user.client.dto.data.UserVO;
import io.swagger.annotations.*;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

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
public class LoginController {

	/**
	 * 登录接口，这里只为了出接口文档 具体处理逻辑已经在 spring security 中处理
	 * @param loginCommand
	 * @return
	 */
	@ApiOperation("登录")
	@ApiImplicitParams({
			// header 参见{@link ParameterType}
			@ApiImplicitParam(name = UserAuthenticationResultServiceImpl.login_header_device_id,value = "设备id，在登录日志是使用",paramType="header")
	})
	@PostMapping(value=WebSecurityConfig.login_processing_url,consumes= MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public SingleResponse<LoginUser> login(@ApiParam LoginCommand loginCommand){
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
		@ApiModelProperty(value = "登录用户名",required = true,position = 1)
		private String username;
		@ApiModelProperty(value = "登录用户密码",required = true,position = 2)
		private String password;
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

	@PreAuthorize("hasAuthority('user')")
	@ApiOperation("获取当前登录用户信息")
	@GetMapping("/userinfo")
	@ResponseStatus(HttpStatus.OK)
	public SingleResponse<LoginUser> info(@ApiIgnore LoginUser loginUser) {
		return SingleResponse.of(loginUser);
	}

}
