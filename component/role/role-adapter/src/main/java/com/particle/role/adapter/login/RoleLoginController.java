package com.particle.role.adapter.login;

import cn.hutool.core.collection.CollectionUtil;
import com.particle.common.client.dto.command.CommonIdCommand;
import com.particle.global.dto.login.GrantedRole;
import com.particle.global.dto.login.GrantedTenant;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.ExceptionFactory;
import com.particle.global.light.share.code.ErrorCodeGlobalEnum;
import com.particle.global.security.security.login.AbstractUserDetailsService;
import com.particle.global.dto.login.LoginUser;
import com.particle.global.security.security.login.LoginTool;
import com.particle.role.app.structmapping.RoleAppStructMapping;
import com.particle.role.client.dto.data.RoleVO;
import com.particle.role.infrastructure.dos.RoleDO;
import com.particle.role.infrastructure.service.IRoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
	 * 角色切换
	 * @param commonIdCommand
	 * @param loginUser
	 * @return
	 */
	@Operation(summary = "切换当前登录用户角色")
	@PreAuthorize("hasAuthority('user')")
	@PostMapping("/changeRole")
	@ResponseStatus(HttpStatus.OK)
	public SingleResponse<LoginUser> changeRole(@Valid @RequestBody CommonIdCommand commonIdCommand, @Parameter(hidden = true) LoginUser loginUser) {

		GrantedRole grantedRole = null;
		List<GrantedRole> roles = loginUser.getRoles();
		if (CollectionUtil.isNotEmpty( roles)) {
			grantedRole = roles.stream().filter(item -> item.getId().equals(commonIdCommand.getId())).findFirst().orElse(null);
		}
		if (grantedRole == null) {
			throw ExceptionFactory.bizException(ErrorCodeGlobalEnum.BAD_REQUEST_ERROR,"切换角色失败，角色不存在");
		}
		loginUser.clearUserGrantedAuthorities();
		Long defaultRoleId = commonIdCommand.getId();
		abstractUserDetailsService.loginUserDetailsFillRoleAndAuthority(loginUser,defaultRoleId);

		// 需要刷新一下权限，否则权限不会生效
		LoginTool.refreshAuthorities(loginUser);
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
		List<RoleDO> byRoleIds = iRoleService.listByRoleIds(roles.stream().map(GrantedRole::getId).collect(Collectors.toList()), null);
		List<RoleVO> roleVOS = RoleAppStructMapping.instance.roleDOsToRoleVOs(byRoleIds);
		return MultiResponse.of(roleVOS);

	}
}
