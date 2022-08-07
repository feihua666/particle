package com.particle.global.ratelimit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 * 液限拦截服务
 * </p>
 *
 * @author yangwei
 * @since 2021-08-02 19:56
 */
public interface RateLimitInterceptService {


	/**
	 * 获取限流器
	 * @return
	 */
	public List<RateLimiterNamedWrapper> getRateLimiter(HttpServletRequest request, HttpServletResponse response, Object handler);
}
