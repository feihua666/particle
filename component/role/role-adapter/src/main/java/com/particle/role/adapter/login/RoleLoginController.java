package com.particle.role.adapter.login;

import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.security.security.login.LoginUser;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Parameter;

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
@Tag(name = "角色功能，登录用户相关")
public class RoleLoginController {

	@Operation(summary = "切换当前登录用户角色")
	@PreAuthorize("hasAuthority('user')")
	@PostMapping("/changeRole")
	@ResponseStatus(HttpStatus.OK)
	public SingleResponse<LoginUser> changeRole(@RequestBody IdCommand idCommand, @Parameter(hidden = true) LoginUser loginUser) {
		loginUser.changeRole(idCommand.getId());
		return SingleResponse.of(loginUser);
	}
}
