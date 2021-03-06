package com.particle.global.security.security.config;

import cn.hutool.core.annotation.AnnotationUtil;
import com.particle.global.security.security.login.LoginUser;
import com.particle.global.security.security.login.LoginUserTool;
import com.particle.global.tool.json.JsonTool;
import com.particle.global.tool.thread.ThreadContextTool;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Optional;

/**
 * <p>
 * 持久化线程变量，方便后面获取
 * </p>
 *
 * @author yangwei
 * @since 2022-06-22 14:45
 */
@Slf4j
public class LoginUserToolPersistentSecurityFilter extends GenericFilterBean {
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
					.map(authentication -> authentication.getPrincipal()).orElse(null)
					;
			String userInfo = "";
			if (principal != null) {
				if (!(principal instanceof String)) {
					if (principal instanceof LoginUser) {
						LoginUserTool.saveToSession((LoginUser) principal, ((HttpServletRequest) request));
						LoginUserTool.setAnonymous(false);
					}
					userInfo = JsonTool.toJsonStr(principal);
				}
				if ((principal instanceof String)) {
					userInfo = ((String) principal);
					if (ANONYMOUS_USER_PRINCIPAL.equals(userInfo)) {
						LoginUserTool.setAnonymous(true);
					}
				}
			}
			log.info("当前登录用户: loginUser={}",userInfo);

			chain.doFilter(request,response);
		}finally {
			LoginUserTool.clear();
		}
	}
}
