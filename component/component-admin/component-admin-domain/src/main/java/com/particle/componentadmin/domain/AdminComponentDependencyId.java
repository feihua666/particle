package com.particle.componentadmin.domain;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 组件依赖关系 领域模型id
 * </p>
 *
 * @author yw
 * @since 2025-12-30 13:22:06
 */
public class AdminComponentDependencyId extends Id {

	public AdminComponentDependencyId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 组件依赖关系 领域模型id
	 * @param id
	 * @return
	 */
	public static AdminComponentDependencyId of(Long id){
		return new AdminComponentDependencyId(id);
	}
}
