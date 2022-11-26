package com.particle.user.domain.identifier;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 用户登录标识 领域模型id
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
public class UserIdentifierId extends Id {

	public UserIdentifierId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 用户登录标识 领域模型id
	 * @param id
	 * @return
	 */
	public static UserIdentifierId of(Long id){
		return new UserIdentifierId(id);
	}
}
