package com.particle.navigation.domain;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 导航网站分类关系 领域模型id
 * </p>
 *
 * @author yw
 * @since 2024-10-22 15:35:11
 */
public class NavigationSiteCategoryRelId extends Id {

	public NavigationSiteCategoryRelId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 导航网站分类关系 领域模型id
	 * @param id
	 * @return
	 */
	public static NavigationSiteCategoryRelId of(Long id){
		return new NavigationSiteCategoryRelId(id);
	}
}
