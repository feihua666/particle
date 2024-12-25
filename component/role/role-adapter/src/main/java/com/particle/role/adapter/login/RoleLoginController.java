package com.particle.role.adapter.login;

import cn.hutool.core.collection.CollectionUtil;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.security.security.login.AbstractUserDetailsService;
import com.particle.global.security.security.login.GrantedRole;
import com.particle.global.security.security.login.LoginUser;
import com.particle.global.security.security.login.LoginUserTool;
import com.particle.global.security.tenant.GrantedTenant;
import com.particle.role.app.structmapping.RoleAppStructMapping;
import com.particle.role.client.dto.data.RoleVO;
import com.particle.role.infrastructure.dos.RoleDO;
import com.particle.role.infrastructure.service.IRoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.javers.common.string.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
	@Autowired
	private IRoleService iRoleService;

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

	@Operation(summary = "当前登录用户的角色")
	@PreAuthorize("hasAuthority('user')")
	@GetMapping("/getList")
	@ResponseStatus(HttpStatus.OK)
	public MultiResponse<RoleVO> getList(@Parameter(hidden = true) LoginUser loginUser) {
		// 超级管理员或租户超级管理员，查询全部角色
        if (loginUser.getIsSuperAdmin() || loginUser.getIsTenantSuperAdmin()) {
			List<RoleDO> roleDOS = iRoleService.list();
			List<RoleVO> roleVOS = RoleAppStructMapping.instance.roleDOsToRoleVOs(roleDOS);
			return MultiResponse.of(roleVOS);
        }
		List<GrantedRole> roles = loginUser.getRoles();
        if (CollectionUtil.isEmpty(roles)) {
            return MultiResponse.buildSuccess();
        }
		List<RoleDO> byRoleIds = iRoleService.getByRoleIds(roles.stream().map(GrantedRole::getId).collect(Collectors.toList()), null);
		List<RoleVO> roleVOS = RoleAppStructMapping.instance.roleDOsToRoleVOs(byRoleIds);
		return MultiResponse.of(roleVOS);

	}
}
