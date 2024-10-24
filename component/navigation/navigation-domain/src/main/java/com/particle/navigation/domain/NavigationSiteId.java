package com.particle.navigation.domain;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 导航网站 领域模型id
 * </p>
 *
 * @author yw
 * @since 2024-10-22 15:34:56
 */
public class NavigationSiteId extends Id {

	public NavigationSiteId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 导航网站 领域模型id
	 * @param id
	 * @return
	 */
	public static NavigationSiteId of(Long id){
		return new NavigationSiteId(id);
	}
}
