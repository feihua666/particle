package com.particle.user.adapter.login.captcha;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * <p>
 * 验证码登录使用
 * </p>
 *
 * @author yangwei
 * @since 2023-05-24 17:07
 */
public class CaptchaDaoAuthenticationProvider extends DaoAuthenticationProvider {

	@Override
	public boolean supports(Class<?> authentication) {
		return (CaptchaUsernamePasswordAuthenticationToken.class
				.isAssignableFrom(authentication));
	}

	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
		// 这里不需要校验密码
		//	需要配置 验证码
	}
}
