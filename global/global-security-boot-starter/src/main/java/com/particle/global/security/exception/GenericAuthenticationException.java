package com.particle.global.security.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * <p>
 * 通用的认证异常
 * </p>
 *
 * @author yangwei
 * @since 2023-05-24 18:23
 */
public class GenericAuthenticationException extends AuthenticationException {
	public GenericAuthenticationException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public GenericAuthenticationException(String msg) {
		super(msg);
	}
}
