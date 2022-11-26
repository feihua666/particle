package com.particle.user.domain.identifier;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 用户密码 领域模型id
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
public class UserIdentifierPwdId extends Id {

	public UserIdentifierPwdId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 用户密码 领域模型id
	 * @param id
	 * @return
	 */
	public static UserIdentifierPwdId of(Long id){
		return new UserIdentifierPwdId(id);
	}
}
