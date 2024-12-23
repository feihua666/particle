package com.particle.global.ratelimit.local;

import com.google.common.util.concurrent.RateLimiter;
import com.particle.global.ratelimit.RateLimitInterceptService;
import com.particle.global.ratelimit.RateLimiterNamedWrapper;
import com.particle.global.tool.thread.ThreadContextTool;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>
 * 根据 threadLocal 获取配置，然后使用 Guava 限流器，更加灵活的可编程配置
 * </p>
 *
 * @author yangwei
 * @since 2021-08-02 20:21
 */
@Slf4j
@Order
@Component
@ConfigurationProperties(prefix = "particle.rate-limit.local.threadlocal")
public class DefaultThreadLocalRateLimitInterceptServiceImpl implements RateLimitInterceptService {

	public static final String theadLocalRateLimitConfigKey = "theadLocalRateLimitConfigKey";

	/**
	 * 是否启用
	 * 添加 Setter 主要是为了 configs 属性可以配置，否则配置不进来
	 */
	@Setter
	private boolean enabled = true;
	/**
	 * 限流器们
	 */
	private Map<String,RateLimiterNamedWrapper> rateLimiterNamedWrapperMap = new ConcurrentHashMap<>();


	@Override
	public List<RateLimiterNamedWrapper> getRateLimiter(HttpServletRequest request, HttpServletResponse response, Object handler) {

		// 如果没有配置不处理
		if (!enabled) {
			return null;
		}

		Object o = ThreadContextTool.get(theadLocalRateLimitConfigKey);
		if (o == null) {
			return null;
		}
		List<Config> configs = null;
		if (o instanceof Config) {
			configs = new ArrayList<>(1);
			configs.add((Config) o);
		} else if (o instanceof List) {
			configs = (List<Config>) o;
		}
		if (configs == null) {
			return null;
		}
		List<RateLimiterNamedWrapper> rateLimiterNamedWrappers = new ArrayList<>(configs.size());
		for (Config config : configs) {
			RateLimiterNamedWrapper rateLimiterNamedWrapper = getRateLimiterNamedWrapper(request,
					config.getKey(),
					config.getName(),
					config.getCode(),
					config.getRate(),
					config.getIsReset());
			rateLimiterNamedWrappers.add(rateLimiterNamedWrapper);
		}

		return rateLimiterNamedWrappers;
	}
	/**
	 * 获取限流器
	 * @param request
	 * @param key
	 * @param name
	 * @param code
	 * @param permitsPerSecond
	 * @return
	 */
	private RateLimiterNamedWrapper getRateLimiterNamedWrapper(HttpServletRequest request,
																	 String key,
																	 String name,
																	 String code,
																	 double permitsPerSecond,
																	 Boolean isReset){

		if (isReset != null && isReset) {
			rateLimiterNamedWrapperMap.remove(key);
		}
		RateLimiterNamedWrapper rateLimiterNamedWrapper = rateLimiterNamedWrapperMap.computeIfAbsent(key,
				(k) -> new RateLimiterNamedWrapper(name, code, RateLimiter.create(permitsPerSecond)));
		return rateLimiterNamedWrapper;
	}


	@Data
	public static class Config {
		private String key;
		private String name;
		private String code;
		private double rate;
		/**
		 * 是否重置已有的限流器，考虑在限流变动时可以重置
		 */
		private Boolean isReset;

		public static Config create(String key, String name, String code, double rate,Boolean isReset) {
			Config config = new Config();
			config.key = key;
			config.name = name;
			config.code = code;
			config.rate = rate;
			config.isReset = isReset;
			return config;
		}
	}
}
