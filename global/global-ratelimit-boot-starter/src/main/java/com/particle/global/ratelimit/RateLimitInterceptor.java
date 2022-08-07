package com.particle.global.ratelimit;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ClassLoaderUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.servlet.ServletUtil;
import cn.hutool.http.ContentType;
import cn.hutool.http.HttpStatus;
import cn.hutool.json.JSONUtil;
import com.google.common.util.concurrent.RateLimiter;
import com.particle.global.actuator.monitor.MonitorTool;
import com.particle.global.dto.response.Response;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.global.notification.notify.NotifyParam;
import com.particle.global.notification.notify.NotifyTool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.AsyncHandlerInterceptor;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 * 限流拦截器
 * </p>
 *
 * @author yangwei
 * @since 2021-08-02 19:55
 */
@Component
@Slf4j
public class RateLimitInterceptor implements AsyncHandlerInterceptor {

	// 限流器延迟毫秒数
	private static Long delayMs = 1000L;

	@Autowired
	private List<RateLimitInterceptService> rateLimitInterceptServices;


	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		for (RateLimitInterceptService rateLimitInterceptService : rateLimitInterceptServices) {
			List<RateLimiterNamedWrapper> rateLimiters = rateLimitInterceptService.getRateLimiter(request,response,handler);
			if (!CollectionUtil.isEmpty(rateLimiters)) {
				for (RateLimiterNamedWrapper limiterNamedWrapper : rateLimiters) {
					RateLimiter rateLimiter = limiterNamedWrapper.getRateLimiter(delayMs);
					//  为空说明滑动一定时间窗口再获取，以保证有充足的令牌
					if (rateLimiter == null) {
						log.debug("限流器延时中，name={},code={}",limiterNamedWrapper.getName(),limiterNamedWrapper.getCode());
						continue;
					}
					// 如果没有成功，则被限流了
					if (!rateLimiter.tryAcquire()) {
						log.warn("超过限流速率，name={}，code={},rate={},url={}",limiterNamedWrapper.getName(),limiterNamedWrapper.getCode(),rateLimiter.getRate(),request.getRequestURI());


						if (ClassLoaderUtil.isPresent("com.particle.global.actuator.monitor.MonitorTool")) {
							MonitorTool.count("interceptor.ratelimit","限流","code",limiterNamedWrapper.getCode());
						}


						if (ClassLoaderUtil.isPresent("com.particle.global.notification.notify.NotifyTool")) {
							// 通知
							NotifyParam notifyParam = NotifyParam.system()
									.setTitle("interceptor.ratelimit 超过限流速率")
									.setContentType("interceptor.ratelimit")
									.setContent(StrUtil.format("超过限流速率，name={}，code={},rate={},url={}",limiterNamedWrapper.getName(),limiterNamedWrapper.getCode(),rateLimiter.getRate(),request.getRequestURI()));
							NotifyTool.notify(notifyParam);
						}


						response.setStatus(HttpStatus.HTTP_FORBIDDEN);
						Response errorResponse = Response.buildFailure(ErrorCodeGlobalEnum.NO_PRIVILEGE_RATELIMIT_ERROR, "超过限流速率[" + limiterNamedWrapper.getName() + "]");
						ServletUtil.write(response, JSONUtil.toJsonStr(errorResponse), ContentType.JSON.getValue());

						return false;
					}
				}
			}
		}

		return AsyncHandlerInterceptor.super.preHandle(request, response, handler);
	}
}
