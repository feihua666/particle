package com.particle.global.security.security.config;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

/**
 * <p>
 * 发现一个问题：在创建 {@link WebSecurityConfig#defaultSecurityFilterChain(org.springframework.security.config.annotation.web.builders.HttpSecurity, org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder)}
 * 时，比如添加一个filter，而这个filter又需要 AuthenticationManager，当前可以提前创建，但如果提前创建会导致其它配置无法添加 AuthenticationProvider（如：{@link OAuth2ResourceServerConfigurer}）
 * 所以这里用的时候再加载
 * </p>
 *
 * @author yangwei
 * @since 2023-08-14 16:17
 */
public class LazyResolveAuthenticationManager implements AuthenticationManager {

	private HttpSecurity http;

	private AuthenticationManager authenticationManager;

	public LazyResolveAuthenticationManager(HttpSecurity http) {
		this.http = http;
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		if (authenticationManager == null) {
			authenticationManager = http.getSharedObject(AuthenticationManager.class);
		}
		return authenticationManager.authenticate(authentication);
	}
}
