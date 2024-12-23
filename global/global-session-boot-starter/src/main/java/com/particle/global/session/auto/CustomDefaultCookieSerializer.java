package com.particle.global.session.auto;

import com.particle.global.swagger.SwaggerInfo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.session.web.http.DefaultCookieSerializer;

/**
 * <p>
 * 自定义默认cookie序列化器，主要是解决cookieName默认值的问题
 * </p>
 *
 * @author yangwei
 * @since 2022-07-29 14:56
 */
public class CustomDefaultCookieSerializer extends DefaultCookieSerializer {

	/**
	 * 注意在{@link com.particle.global.security.security.login.DefaultAuthenticationSuccessHandler#onAuthenticationSuccess(HttpServletRequest, HttpServletResponse, org.springframework.security.core.Authentication)} 也使用了该默认变量
	 */
	private String cookieName = SwaggerInfo.token;

	public CustomDefaultCookieSerializer() {
		setCookieName(SwaggerInfo.token);
	}

	@Override
	public void setCookieName(String cookieName) {
		super.setCookieName(cookieName);
		this.cookieName = cookieName;
	}

	public String getCookieName() {
		return cookieName;
	}
}
