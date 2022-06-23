package com.particle.global.security.security.login;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>
 * 自定义认证处理器，主要是处理登录失败或成功之后的逻辑
 * 主要是为了方便以后扩展，避免直接实现  {@link AuthenticationFailureHandler} 或 {@link AuthenticationSuccessHandler}
 * 直接实现该接口即可
 * </p>
 *
 * @author yangwei
 * @since 2021-09-28 09:55
 */
public interface IAuthenticationResultService {
	/**
	 * 认证成功调用
	 * 注意：使用默认的 {@link DefaultAuthenticationSuccessHandler} 有效
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @param authentication
	 * @throws IOException
	 */
	public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException;

	/**
	 * 认证失败调用
	 * 注意：使用默认的 {@link DefaultAuthenticationFailureHandler} 有效
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @param e
	 * @throws IOException
	 */
	public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException;
}
