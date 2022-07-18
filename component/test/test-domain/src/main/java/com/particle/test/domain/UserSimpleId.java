package com.particle.test.domain;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 简单用户 领域模型id
 * </p>
 *
 * @author yw
 * @since 2022-07-15
 */
public class UserSimpleId extends Id {

	public UserSimpleId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 简单用户 领域模型id
	 * @param id
	 * @return
	 */
	public static UserSimpleId of(Long id){
		return new UserSimpleId(id);
	}
}
