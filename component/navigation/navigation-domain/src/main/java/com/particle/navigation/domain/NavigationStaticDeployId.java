package com.particle.navigation.domain;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 导航网站静态部署 领域模型id
 * </p>
 *
 * @author yw
 * @since 2024-11-01 10:02:52
 */
public class NavigationStaticDeployId extends Id {

	public NavigationStaticDeployId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 导航网站静态部署 领域模型id
	 * @param id
	 * @return
	 */
	public static NavigationStaticDeployId of(Long id){
		return new NavigationStaticDeployId(id);
	}
}
