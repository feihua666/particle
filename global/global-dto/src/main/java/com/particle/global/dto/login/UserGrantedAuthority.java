package com.particle.global.dto.login;

import com.particle.global.dto.basic.DTO;
import lombok.Builder;
import lombok.Data;

/**
 * <p>
 * 用户权限，包括用户角色和用户权限颗粒
 * </p>
 *
 * @author yangwei
 * @since 2022-11-28 09:35
 */
@Data
public class UserGrantedAuthority extends DTO {


	/**
	 * 授权的权限 {@link UserGrantedAuthority#grantedPermission} 对应的角色
	 */
	private GrantedRole grantedPermissionRole;


	/**
	 * 授权的权限
	 */
	private GrantedPermission grantedPermission;

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
	 * 单纯 user 权限，和角色无关
	 *
	 * @return
	 */
	public static UserGrantedAuthority userGrantedAuthority = create(
			null,
			GrantedPermission.create(GrantedPermission.Source.sys).permission(GrantedPermission.userGrantedPermissionCode).build()
	);

    /**
     * 角色和对应的权限
     *
     * @param grantedPermissionRole
     * @param grantedPermission
     * @return
     */
	public static UserGrantedAuthority create(GrantedRole grantedPermissionRole,GrantedPermission grantedPermission) {
		UserGrantedAuthority UserGrantedAuthority = new UserGrantedAuthority();
		UserGrantedAuthority.grantedPermissionRole = grantedPermissionRole;
		UserGrantedAuthority.grantedPermission = grantedPermission;

		return UserGrantedAuthority;
	}
}
