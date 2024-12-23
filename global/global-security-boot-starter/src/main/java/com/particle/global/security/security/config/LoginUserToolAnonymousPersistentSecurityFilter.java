package com.particle.global.security.security.config;

import com.particle.global.security.security.login.LoginUserTool;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;
import java.util.Optional;

/**
 * <p>
 * 持久化线程变量，方便后面获取，主要解决匿名登录时设置匿名变量到{@link com.particle.global.security.security.login.LoginUserTool}
 * </p>
 *
 * @author yangwei
 * @since 2023-07-19 18:25:25
 */
@Slf4j
public class LoginUserToolAnonymousPersistentSecurityFilter extends GenericFilterBean {
	/**
	 * 匿名用户的 principal
	 * 来源于 {@link AnonymousAuthenticationFilter}
	 */
	private static String ANONYMOUS_USER_PRINCIPAL = "anonymousUser";

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		try {

			Object principal = Optional.ofNullable(SecurityContextHolder.getContext())
					.map(securityContext -> securityContext.getAuthentication())
					.map(authentication -> authentication.getPrincipal()).orElse(null);
			String userInfo = "";
			if (principal != null) {
				if ((principal instanceof String)) {
					userInfo = ((String) principal);
					if (ANONYMOUS_USER_PRINCIPAL.equals(userInfo)) {
						LoginUserTool.setAnonymous(true);
						log.info("当前登录用户: loginUser={}", userInfo);
					}
				}
			}
			chain.doFilter(request, response);
		} finally {
			LoginUserTool.clear();

		}
	}

}
