package com.particle.global.web.filter;

import cn.hutool.extra.servlet.JakartaServletUtil;
import com.particle.global.light.share.login.LoginConstants;
import com.particle.global.tool.login.TokenTool;
import com.particle.global.tool.thread.ThreadContextTool;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * <p>
 * 用来清除thread context
 * </p>
 *
 * @author yangwei
 * @since 2024-12-11 17:43:31
 */
public class ThreadContextFilter extends OncePerRequestFilter {
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		// 将 token 设置到线程变量中，主要目的是在 feign 调用时，自动传递下游的 token
		String token = JakartaServletUtil.getHeaderIgnoreCase(request, LoginConstants.header_c_token);
		TokenTool.setToken(token);

		try {
			filterChain.doFilter(request,response);
		} finally {
			ThreadContextTool.remove();
		}

	}
}
