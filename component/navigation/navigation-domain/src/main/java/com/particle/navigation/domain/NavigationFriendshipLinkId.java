package com.particle.navigation.domain;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 导航友情链接 领域模型id
 * </p>
 *
 * @author yw
 * @since 2024-11-03 11:09:01
 */
public class NavigationFriendshipLinkId extends Id {

	public NavigationFriendshipLinkId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 导航友情链接 领域模型id
	 * @param id
	 * @return
	 */
	public static NavigationFriendshipLinkId of(Long id){
		return new NavigationFriendshipLinkId(id);
	}
}
