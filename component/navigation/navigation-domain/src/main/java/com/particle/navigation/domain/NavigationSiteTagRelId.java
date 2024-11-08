package com.particle.navigation.domain;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 导航网站标签关系 领域模型id
 * </p>
 *
 * @author yw
 * @since 2024-11-07 09:39:14
 */
public class NavigationSiteTagRelId extends Id {

	public NavigationSiteTagRelId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 导航网站标签关系 领域模型id
	 * @param id
	 * @return
	 */
	public static NavigationSiteTagRelId of(Long id){
		return new NavigationSiteTagRelId(id);
	}
}
