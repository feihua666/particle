package com.particle.global.security.security.login;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel("授权的角色")
public class GrantedRole implements Serializable {

	@ApiModelProperty("role id")
	private Long id;
	@ApiModelProperty("role code")
	private String code;
	@ApiModelProperty("role name")
	private String name;
	@ApiModelProperty(value = "是否超级管理员")
	private Boolean isSuperadmin;

	public static GrantedRole create(Long id, String code, String name,Boolean isSuperadmin) {
		return GrantedRole.builder().id(id)
				.code(code)
				.name(name)
				.isSuperadmin(isSuperadmin)
				.build();
	}
}
