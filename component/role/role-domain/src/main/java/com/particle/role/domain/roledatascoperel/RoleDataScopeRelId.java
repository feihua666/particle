package com.particle.role.domain.roledatascoperel;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 角色数据范围关系 领域模型id
 * </p>
 *
 * @author yw
 * @since 2024-07-01 16:45:06
 */
public class RoleDataScopeRelId extends Id {

	public RoleDataScopeRelId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 角色数据范围关系 领域模型id
	 * @param id
	 * @return
	 */
	public static RoleDataScopeRelId of(Long id){
		return new RoleDataScopeRelId(id);
	}
}
