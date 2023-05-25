package com.particle.global.security.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * <p>
 * 验证码不正确异常，主要是用于动态验证码登录场景
 * </p>
 *
 * @author yangwei
 * @since 2023-05-24 18:23
 */
public class BadCaptchaAuthenticationException extends AuthenticationException {
	public BadCaptchaAuthenticationException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public BadCaptchaAuthenticationException(String msg) {
		super(msg);
	}
}
