package com.particle.global.security.security.config;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;

/**
 * <p>
 * 自定义，主要是为了解决在继承 {@link DaoAuthenticationProvider} 时会有多个 provider验证的情况
 * </p>
 *
 * @author yangwei
 * @since 2023-05-25 21:48
 */
public class CustomDaoAuthenticationProvider extends DaoAuthenticationProvider {

	@Override
	public boolean supports(Class<?> authentication) {
		return (UsernamePasswordAuthenticationToken.class
				.isAssignableFrom(authentication)) && UsernamePasswordAuthenticationToken.class.getName().equals(authentication.getName());
	}
}
