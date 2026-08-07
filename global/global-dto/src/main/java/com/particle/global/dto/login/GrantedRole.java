package com.particle.global.dto.login;


import com.particle.global.dto.basic.DTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p>
 * 授权的角色
 * </p>
 *
 * @author yangwei
 * @since 2022-11-28 09:54
 */
@Data
@Schema(description = "授权的角色")
public class GrantedRole  extends DTO {

	@Schema(description = "role id")
	private Long id;

	@Schema(description = "role code")
	private String code;

	@Schema(description = "role name")
	private String name;

	@Schema(description = "是否超级管理员")
	private Boolean isSuperadmin;

	public static GrantedRole create(Long id,
									 String code,
									 String name,
									 Boolean isSuperadmin) {
		GrantedRole grantedRole = new GrantedRole();
		grantedRole.id = id;
		grantedRole.code = code;
		grantedRole.name = name;
		grantedRole.isSuperadmin = isSuperadmin;
		return grantedRole;
	}
}
