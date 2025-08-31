package com.particle.user.domain;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 用户扩展信息 领域模型id
 * </p>
 *
 * @author yw
 * @since 2025-08-30 23:39:50
 */
public class UserExtraInfoId extends Id {

	public UserExtraInfoId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 用户扩展信息 领域模型id
	 * @param id
	 * @return
	 */
	public static UserExtraInfoId of(Long id){
		return new UserExtraInfoId(id);
	}
}
