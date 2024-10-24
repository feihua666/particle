package com.particle.navigation.domain;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 导航分类 领域模型id
 * </p>
 *
 * @author yw
 * @since 2024-10-22 15:34:42
 */
public class NavigationCategoryId extends Id {

	public NavigationCategoryId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 导航分类 领域模型id
	 * @param id
	 * @return
	 */
	public static NavigationCategoryId of(Long id){
		return new NavigationCategoryId(id);
	}
}
