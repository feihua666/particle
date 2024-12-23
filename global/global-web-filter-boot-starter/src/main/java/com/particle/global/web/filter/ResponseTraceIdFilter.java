package com.particle.global.web.filter;

import brave.Span;
import brave.Tracer;
import com.particle.global.tool.thread.ThreadContextTool;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * <p>
 * 返回 traceid
 * </p>
 *
 * @author yangwei
 * @since 2021-08-02 14:02
 */
public class ResponseTraceIdFilter extends OncePerRequestFilter {
	/**
	 * 遵循header名称大写惯例
	 */
	private static String RESPONSE_TRACE_ID_KEY = "Trace-Id";


	private Tracer tracer;
	public ResponseTraceIdFilter (Tracer tracer){
		this.tracer = tracer;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		Span currentSpan = this.tracer.currentSpan();
		response.addHeader(RESPONSE_TRACE_ID_KEY, currentSpan.context().traceIdString());
		// 在配置中该 filter排名第一位，直接在这里全局清除线程变量
		filterChain.doFilter(request,response);
	}
}
