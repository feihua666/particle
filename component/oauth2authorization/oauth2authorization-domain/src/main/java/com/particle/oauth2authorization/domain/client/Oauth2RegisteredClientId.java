package com.particle.oauth2authorization.domain.client;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * oauth2客户端 领域模型id
 * </p>
 *
 * @author yw
 * @since 2023-07-25 17:03:38
 */
public class Oauth2RegisteredClientId extends Id {

	public Oauth2RegisteredClientId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 oauth2客户端 领域模型id
	 * @param id
	 * @return
	 */
	public static Oauth2RegisteredClientId of(Long id){
		return new Oauth2RegisteredClientId(id);
	}
}
