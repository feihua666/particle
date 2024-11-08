package com.particle.navigation.domain;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 导航网站标签 领域模型id
 * </p>
 *
 * @author yw
 * @since 2024-11-07 09:38:23
 */
public class NavigationSiteTagId extends Id {

	public NavigationSiteTagId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 导航网站标签 领域模型id
	 * @param id
	 * @return
	 */
	public static NavigationSiteTagId of(Long id){
		return new NavigationSiteTagId(id);
	}
}
