package com.particle.user.domain;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 用户 领域模型id
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
public class UserId extends Id {

	public UserId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 用户 领域模型id
	 * @param id
	 * @return
	 */
	public static UserId of(Long id){
		return new UserId(id);
	}
}
