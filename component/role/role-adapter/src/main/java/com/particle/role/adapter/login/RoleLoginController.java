package com.particle.role.adapter.login;

import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.security.security.login.LoginUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

/**
 * <p>
 * 角色功能，登录用户相关
 * </p>
 *
 * @author yangwei
 * @since 2023-04-14 16:24
 */
@RestController
@RequestMapping("/role/login")
@Api(tags = "角色功能，登录用户相关")
public class RoleLoginController {

	@ApiOperation("切换当前登录用户角色")
	@PreAuthorize("hasAuthority('user')")
	@PostMapping("/changeRole")
	@ResponseStatus(HttpStatus.OK)
	public SingleResponse<LoginUser> changeRole(@RequestBody IdCommand idCommand, @ApiIgnore LoginUser loginUser) {
		loginUser.changeRole(idCommand.getId());
		return SingleResponse.of(loginUser);
	}
}
