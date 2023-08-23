package com.particle.global.openapi.data;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * <p>
 * 自定义基于 BasicHeaderDTO 的token，以认证使用
 * </p>
 *
 * @author yangwei
 * @since 2023-08-15 10:56
 */
public class GlobalOpenapiBasicHeaderAuthenticationToken extends AbstractAuthenticationToken {

	private BasicHeaderDTO basicHeaderDTO;


	public GlobalOpenapiBasicHeaderAuthenticationToken(BasicHeaderDTO basicHeaderDTO, Collection<? extends GrantedAuthority> authorities) {
		super(authorities);
		this.basicHeaderDTO = basicHeaderDTO;
	}
	/**
	 * 不支持
	 */
	@Override
	public Object getCredentials() {

		return null;
	}

	@Override
	public Object getPrincipal() {
		return basicHeaderDTO;
	}

}
