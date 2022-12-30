package com.particle.global.security.security.login;

import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

/**
 * <p>
 * 用户权限，包括用户角色和用户权限颗粒
 * </p>
 *
 * @author yangwei
 * @since 2022-11-28 09:35
 */
@Data
@Builder
public class UserGrantedAuthority implements GrantedAuthority {


	/**
	 * 授权的权限 {@link UserGrantedAuthority#grantedPermission} 对应的角色
	 */
	private GrantedRole grantedPermissionRole;


	/**
	 * 授权的权限
	 */
	private GrantedPermission grantedPermission;

	@Override
	public String getAuthority() {
		if (grantedPermission == null) {
			if (grantedPermissionRole != null) {
				return grantedPermissionRole.getCode();
			}
			return null;
		}
		return grantedPermission.getPermission();
	}

	/**
	 * 单纯user权限
	 * @return
	 */
	public static UserGrantedAuthority userGrantedAuthority = UserGrantedAuthority.builder()
			.grantedPermission(
					GrantedPermission.create(GrantedPermission.Source.sys).permission(GrantedPermission.userGrantedPermissionCode).build()
			).build();

}
