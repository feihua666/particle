package com.particle.user.domain.login;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 用户登录记录 领域模型id
 * </p>
 *
 * @author yw
 * @since 2022-11-26
 */
public class UserLoginRecordId extends Id {

	public UserLoginRecordId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 用户登录记录 领域模型id
	 * @param id
	 * @return
	 */
	public static UserLoginRecordId of(Long id){
		return new UserLoginRecordId(id);
	}
}
