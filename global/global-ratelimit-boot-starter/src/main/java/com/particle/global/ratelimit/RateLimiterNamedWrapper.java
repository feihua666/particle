package com.particle.global.ratelimit;

import com.google.common.util.concurrent.RateLimiter;
import lombok.Getter;

/**
 * <p>
 * 限流器名称包装
 * </p>
 *
 * @author yangwei
 * @since 2021-08-02 20:07
 */

public class RateLimiterNamedWrapper {
	/**
	 * 限流器名称
	 */
	@Getter
	private String name;
	/**
	 * 限流器编码
	 */
	@Getter
	private String code;


	/**
	 * 限流器
	 */
	@Getter
	private RateLimiter rateLimiter;
	/**
	 * 创建时间
	 */
	private Long createAt;
	/**
	 * 是否已经延迟
	 */
	private boolean hasDelay = false;


	public RateLimiterNamedWrapper(String name,String code,RateLimiter rateLimiter){
		this.name = name;
		this.code = code;
		this.rateLimiter = rateLimiter;
		this.createAt = System.currentTimeMillis();
	}

	/**
	 * 延迟获取限流器
	 * 如果 SmoothBursty限流器，在初始化的令牌桶为0，如果直接使用后面请求会被导致限流，需要等待一时间将令牌填满
	 * @param delayMs 延迟毫秒数，如果没有达到则直接返回空
	 * @return
	 */
	public RateLimiter getRateLimiter(Long delayMs){
		// 为为避免频繁获取系统时间，加一个标识
		if (hasDelay) {
			return rateLimiter;
		}
		if (System.currentTimeMillis() - createAt >= delayMs) {
			hasDelay = true;
			return rateLimiter;
		}
		return null;
	}
}
