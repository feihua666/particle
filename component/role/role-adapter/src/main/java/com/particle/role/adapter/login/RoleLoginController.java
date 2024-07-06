package com.particle.role.adapter.login;

import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.security.security.login.AbstractUserDetailsService;
import com.particle.global.security.security.login.LoginUser;
import com.particle.global.security.security.login.LoginUserTool;
import com.particle.global.security.tenant.GrantedTenant;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Parameter;

import javax.validation.Valid;
import java.util.Optional;

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

	@Autowired
	private AbstractUserDetailsService abstractUserDetailsService;

	/**
	 * 这里也提供一个角色切换接口，仅多一种选择
	 * 参考{@link com.particle.user.adapter.login.UserLoginController#changeRole(IdCommand, LoginUser)}保持一致
	 * @param idCommand
	 * @param loginUser
	 * @return
	 */
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
}
