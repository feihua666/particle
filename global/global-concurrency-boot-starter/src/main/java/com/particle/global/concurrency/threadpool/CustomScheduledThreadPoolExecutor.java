package com.particle.global.concurrency.threadpool;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;

/**
 * <p>
 * 自定义线程池，方便以后扩展
 * </p>
 *
 * @author yangwei
 * @since 2022-09-26 17:12
 */
public class CustomScheduledThreadPoolExecutor extends ScheduledThreadPoolExecutor {

	public CustomScheduledThreadPoolExecutor(int corePoolSize) {
		super(corePoolSize);
	}

	public CustomScheduledThreadPoolExecutor(int corePoolSize, ThreadFactory threadFactory) {
		super(corePoolSize, threadFactory);
	}

	public CustomScheduledThreadPoolExecutor(int corePoolSize, RejectedExecutionHandler handler) {
		super(corePoolSize, handler);
	}

	public CustomScheduledThreadPoolExecutor(int corePoolSize, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
		super(corePoolSize, threadFactory, handler);
	}
}
