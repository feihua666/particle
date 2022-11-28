package com.particle.global.security.security.login;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 授权的权限
 * </p>
 *
 * @author yangwei
 * @since 2022-11-28 09:54
 */
@Data
@Builder
@ApiModel("授权的权限")
public class GrantedPermission implements Serializable {
	// 一般登录登录成功后就有user权限
	public static String userGrantedPermissionCode = "user";

	@ApiModelProperty("permission id")
	private Long id;
	@ApiModelProperty("permission code")
	private String permission;
	@ApiModelProperty("permission name")
	private String name;
	@ApiModelProperty("permission type")
	private String type;

	@ApiModelProperty("permission source")
	private String source;

	/**
	 * 如果 source为role则sourceId为roleId
	 */
	@ApiModelProperty("permission sourceId")
	private Long sourceId;
	/**
	 * 来源
	 */
	public enum Source{
		// 来源于角色
		role,
		// 来源于系统
		sys,
		// 来源于用户
		user,
		// 来源于用户组
		user_group,
		// 来源于部门
		dept,
		// 来源于公司
		comp,
		// 来源于其它
		other;
	}

	/**
	 * 创建
	 * @param source
	 * @return
	 */
	public static GrantedPermission.GrantedPermissionBuilder create(Source source){
		return GrantedPermission.builder().source(source.name());
	}

}
