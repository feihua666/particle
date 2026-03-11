package com.particle.global.security.security.login;

import cn.hutool.core.collection.CollectionUtil;
import com.particle.global.dto.login.UserGrantedAuthority;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户权限，包括用户角色和用户权限颗粒
 * </p>
 *
 * @author yangwei
 * @since 2022-11-28 09:35
 */
@Data
public class SecurityUserGrantedAuthority extends UserGrantedAuthority implements GrantedAuthority {

	@Override
	public String getAuthority() {
		return super.getAuthority();
	}


	public static SecurityUserGrantedAuthority create(UserGrantedAuthority userGrantedAuthority) {
		SecurityUserGrantedAuthority securityUserGrantedAuthority = new SecurityUserGrantedAuthority();
		securityUserGrantedAuthority.setGrantedPermissionRole(userGrantedAuthority.getGrantedPermissionRole());
		securityUserGrantedAuthority.setGrantedPermission(userGrantedAuthority.getGrantedPermission());
		return securityUserGrantedAuthority;
	}


	public static List<SecurityUserGrantedAuthority> create(List<UserGrantedAuthority> userGrantedAuthorities) {
		if (CollectionUtil.isEmpty(userGrantedAuthorities)) {
			return Collections.emptyList();
		}
		return userGrantedAuthorities.stream()
				.map(userGrantedAuthority ->
						SecurityUserGrantedAuthority.create(userGrantedAuthority))
				.collect(Collectors.toList());
	}
}
