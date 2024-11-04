package com.particle.navigation.domain;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 导航提交 领域模型id
 * </p>
 *
 * @author yw
 * @since 2024-11-03 11:09:19
 */
public class NavigationSubmitId extends Id {

	public NavigationSubmitId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 导航提交 领域模型id
	 * @param id
	 * @return
	 */
	public static NavigationSubmitId of(Long id){
		return new NavigationSubmitId(id);
	}
}
