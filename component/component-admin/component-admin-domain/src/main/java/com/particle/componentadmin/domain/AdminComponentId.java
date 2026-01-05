package com.particle.componentadmin.domain;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 组件 领域模型id
 * </p>
 *
 * @author yw
 * @since 2025-12-30 13:21:31
 */
public class AdminComponentId extends Id {

	public AdminComponentId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 组件 领域模型id
	 * @param id
	 * @return
	 */
	public static AdminComponentId of(Long id){
		return new AdminComponentId(id);
	}
}
