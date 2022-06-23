package com.particle.global.web.filter;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>
 * request body 输入流只能读取一次，这里做一下封装，可重复读取
 * 因为在后续的filter中可能会用到
 * </p>
 *
 * @author yangwei
 * @since 2021-08-04 13:57
 */
@Data
public class RequestBodyReadableFilter extends OncePerRequestFilter {

	// 是否开启
	@Value("${particle.web.filter.body-readable:#{true}}")
	private Boolean enable;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		HttpServletRequest requestToWrapper = request;
		if (enable) {
			requestToWrapper = new BodyReaderHttpServletRequestWrapper(request);
		}
		filterChain.doFilter(requestToWrapper,response);
	}
}
