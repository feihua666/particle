package com.particle.global.security.security.login;


import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "授权的权限")
public class GrantedPermission implements Serializable {
	// 一般登录登录成功后就有user权限
	public static String userGrantedPermissionCode = "user";

	@Schema(description = "permission id")
	private Long id;
	@Schema(description = "permission code")
	private String permission;
	@Schema(description = "permission name")
	private String name;
	@Schema(description = "permission type")
	private String type;

	@Schema(description = "permission source")
	private String source;

	/**
	 * 如果 source为role则sourceId为roleId
	 */
	@Schema(description = "permission sourceId")
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

	public static GrantedPermission create(Long id,
										   String permission,
										   String name,
										   String type,
										   Source source,
										   Long sourceId
										   ) {
		return GrantedPermission.create(source)
				.sourceId(sourceId)
				.id(id)
				.permission(permission)
				.name(name)
				//	类型暂不添加
				.type(type)
				.build();
	}
}
