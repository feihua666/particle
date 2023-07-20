package com.particle.global.security.security.login;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 授权的角色
 * </p>
 *
 * @author yangwei
 * @since 2022-11-28 09:54
 */
@Data
@Builder
@Schema(description = "授权的角色")
public class GrantedRole implements Serializable {

	@Schema(description = "role id")
	private Long id;
	@Schema(description = "role code")
	private String code;
	@Schema(description = "role name")
	private String name;
	@Schema(description = "是否超级管理员")
	private Boolean isSuperadmin;

	public static GrantedRole create(Long id, String code, String name,Boolean isSuperadmin) {
		return GrantedRole.builder().id(id)
				.code(code)
				.name(name)
				.isSuperadmin(isSuperadmin)
				.build();
	}
}
