package com.particle.global.web.filter;

import brave.Span;
import brave.Tracer;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
	private static String RESPONSE_TRACE_ID_KEY = "trace-id";


	private Tracer tracer;
	public ResponseTraceIdFilter (Tracer tracer){
		this.tracer = tracer;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		Span currentSpan = this.tracer.currentSpan();
		response.addHeader(RESPONSE_TRACE_ID_KEY, currentSpan.context().traceIdString());
		filterChain.doFilter(request,response);
	}
}
