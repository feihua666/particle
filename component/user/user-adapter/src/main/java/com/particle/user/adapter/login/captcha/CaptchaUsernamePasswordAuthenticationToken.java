package com.particle.user.adapter.login.captcha;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * <p>
 * 验证码登录token
 * </p>
 *
 * @author yangwei
 * @since 2023-05-24 17:37
 */
public class CaptchaUsernamePasswordAuthenticationToken extends UsernamePasswordAuthenticationToken {
	public CaptchaUsernamePasswordAuthenticationToken(Object principal, Object credentials) {
		super(principal, credentials);
	}

	public CaptchaUsernamePasswordAuthenticationToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
		super(principal, credentials, authorities);
	}
}
