package com.particle.global.tool.retry;

import org.springframework.retry.RetryCallback;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

/**
 * <p>
 * 重试工具
 * 重试主要分为两种处理思路：
 * 1. 重试策略 即：在什么情况下重试，一般我们想到的异常重试，那这个异常是如何定义呢？比如：http 调用，可以定义400时重试也要以定义500时不重试，重试多少次？
 * 2. 重试回退策略 即：已经决定要重试那么重试的时机是什么，固定2秒试一次？
 * </p>
 *
 * @author yangwei
 * @since 2023-06-09 17:53
 */
public class RetryTool {

	private volatile static RetryTemplate instance = null;


	private static RetryTemplate getInstance() {
		if (instance == null) {
			synchronized (RetryTemplate.class) {
				if (instance == null) {
					instance = newInstance();
				}
			}
		}
		return instance;
	}

	/**
	 * 新建一个新的实例
	 * @return
	 */
	public static RetryTemplate newInstance() {
		// 重试回退策略
		// 指数退避策略，需设置参数sleeper、initialInterval、maxInterval和multiplier，initialInterval指定初始休眠时间，默认100毫秒，maxInterval指定最大休眠时间，默认30秒，multiplier指定乘数，即下一次休眠时间为当前休眠时间*multiplier
		ExponentialBackOffPolicy policy = new ExponentialBackOffPolicy();
		// 第一次隔 500ms 重试，第二次隔 1000ms 重试，第三次隔 2000ms 重试 ，如果还有第4次 最大 2000ms
		policy.setInitialInterval(500);
		policy.setMaxInterval(2000);
		policy.setMultiplier(2.0);
		// 重试策略
		SimpleRetryPolicy retryPolicy = new SimpleRetryPolicy();
		// 重试三次
		retryPolicy.setMaxAttempts(3);

		RetryTemplate instance = new RetryTemplate();
		instance.setBackOffPolicy(policy);
		instance.setRetryPolicy(retryPolicy);

		return instance;
	}


	/**
	 * 默认重试三次
	 * @param retryCallback
	 * @param <T>
	 * @param <E>
	 * @return
	 * @throws E
	 */
	public static <T, E extends Throwable> T execute(RetryCallback<T, E> retryCallback) throws E{
		return getInstance().execute(retryCallback);
	}

}
