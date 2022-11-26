package com.particle.role.domain.rolefuncrel;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 角色菜单功能关系 领域模型id
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
public class RoleFuncRelId extends Id {

	public RoleFuncRelId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 角色菜单功能关系 领域模型id
	 * @param id
	 * @return
	 */
	public static RoleFuncRelId of(Long id){
		return new RoleFuncRelId(id);
	}
}
