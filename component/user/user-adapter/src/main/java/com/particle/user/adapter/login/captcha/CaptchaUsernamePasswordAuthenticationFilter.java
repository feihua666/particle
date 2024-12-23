package com.particle.user.adapter.login.captcha;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.particle.global.captcha.endpoint.CaptchaVerifyCommand.captchaUniqueIdentifierFieldName;

/**
 * <p>
 * 验证码登录过滤器
 * </p>
 *
 * @author yangwei
 * @since 2023-05-24 17:35
 */
public class CaptchaUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private boolean postOnly = true;
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		if ( this.postOnly && !"POST".equals(request.getMethod())) {
			throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
		}
		String username = obtainUsername(request);
		username = (username != null) ? username : "";
		username = username.trim();
		String password = obtainPassword(request);
		password = (password != null) ? password : "";

		CaptchaUsernamePasswordAuthenticationToken authRequest = new CaptchaUsernamePasswordAuthenticationToken(username, password);
		authRequest.setCaptchaUniqueIdentifier(request.getParameter(captchaUniqueIdentifierFieldName));
		// Allow subclasses to set the "details" property
		setDetails(request, authRequest);
		return this.getAuthenticationManager().authenticate(authRequest);
	}
	@Override
	public void setPostOnly(boolean postOnly) {
		super.setPostOnly(postOnly);
		this.postOnly = postOnly;
	}
}
