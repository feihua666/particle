package com.particle.role.domain.roleuserrel;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 角色用户关系 领域模型id
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
public class RoleUserRelId extends Id {

	public RoleUserRelId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 角色用户关系 领域模型id
	 * @param id
	 * @return
	 */
	public static RoleUserRelId of(Long id){
		return new RoleUserRelId(id);
	}
}
