package com.particle.global.web.filter;

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
		try {
			filterChain.doFilter(request,response);
		} finally {
			ThreadContextTool.remove();
		}

	}
}
