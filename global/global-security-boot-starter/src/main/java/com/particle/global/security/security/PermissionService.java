package com.particle.global.security.security;

import com.particle.global.security.security.login.LoginUser;
import com.particle.global.security.security.login.LoginUserTool;
import com.particle.global.tool.thread.ThreadContextTool;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.PatternMatchUtils;
import org.springframework.util.StringUtils;

import java.util.Collection;

/**
 * <p>
 * 自定义一个权限校验,优先从 threadlocal 获取，方便根据url匹配
 * </p>
 *
 * @author yangwei
 * @since 2023-08-16 11:02
 */
@Component("pms")
public class PermissionService {

	public static final String permissionKey = "permissionServiceKey";

	/**
	 * 判断接口是否有xxx:xxx权限
	 * @param permission 权限
	 * @return {boolean}
	 */
	public boolean hasPermission(String permission) {

		LoginUser loginUser = LoginUserTool.getLoginUser();
		if (loginUser != null && loginUser.getIsSuperAdmin() != null && loginUser.getIsSuperAdmin()) {
			return true;
		}


		String permissionTemp = ((String) ThreadContextTool.get(permissionKey));


		SecurityContext context = SecurityContextHolder.getContext();
		if (context == null) {
			return false;
		}
		Authentication authentication = context.getAuthentication();
		if (authentication == null) {
			return false;
		}
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		String finalPermissionTemp = permissionTemp;
		return authorities.stream().map(GrantedAuthority::getAuthority).filter(StringUtils::hasText)
				.anyMatch(x -> PatternMatchUtils.simpleMatch(finalPermissionTemp, x) || PatternMatchUtils.simpleMatch(permission, x));
	}

	public static void putPermission(String permission) {
		ThreadContextTool.put(permissionKey,permission);
	}

	public static void clear() {
		ThreadContextTool.remove(permissionKey);

	}
}
