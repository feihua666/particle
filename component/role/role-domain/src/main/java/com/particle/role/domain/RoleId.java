package com.particle.role.domain;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 角色 领域模型id
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
public class RoleId extends Id {

	public RoleId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 角色 领域模型id
	 * @param id
	 * @return
	 */
	public static RoleId of(Long id){
		return new RoleId(id);
	}
}
