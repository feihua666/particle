package com.particle.global.concurrency.threadpool;

import cn.hutool.core.thread.ThreadFactoryBuilder;
import cn.hutool.core.util.ClassLoaderUtil;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.binder.jvm.ExecutorServiceMetrics;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.cloud.sleuth.instrument.async.TraceableExecutorService;

import java.util.concurrent.*;

/**
 * <p>
 * 自定义线程池工厂类
 * </p>
 *
 * @author yangwei
 * @since 2021-06-13 18:55
 */
public class CustomExecutors{


	/**
	 * 自定义线程池，带链路追踪，带监控
	 * @param beanFactory
	 * @param threadPoolName
	 * @param corePoolSize
	 * @param maximumPoolSize
	 * @param keepAliveTime 单位毫秒
	 * @param workQueue
	 * @param handler
	 * @param preStartCoreThread
	 * @return
	 */
	public static ExecutorService newExecutorService(BeanFactory beanFactory,
													 String threadPoolName,
													 int corePoolSize,
													 int maximumPoolSize,
													 long keepAliveTime,
													 BlockingQueue<Runnable> workQueue,
													 RejectedExecutionHandler handler,
													 boolean preStartCoreThread, MeterRegistry meterRegistry) {

		// 线程工厂
		ThreadFactory threadFactory = ThreadFactoryBuilder.create()
				.setNamePrefix(threadPoolName)
				.setUncaughtExceptionHandler(new CustomDefaultUncaughtExceptionHandler()).build();

		// 线程池，自定义
		ThreadPoolExecutor threadPoolExecutor =
				new CustomThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.MILLISECONDS, workQueue,
						threadFactory, handler);

		// 预启动核心线程
		if (preStartCoreThread) {
			threadPoolExecutor.prestartAllCoreThreads();
		}
		ExecutorService executorService = threadPoolExecutor;
		if (ClassLoaderUtil.isPresent("io.micrometer.core.instrument.binder.jvm.ExecutorServiceMetrics")) {
			executorService = ExecutorServiceMetrics.monitor(meterRegistry, threadPoolExecutor, threadPoolName);
		}
		if (ClassLoaderUtil.isPresent("org.springframework.cloud.sleuth.instrument.async.TraceableExecutorService")) {
			// 异步链路追踪
			executorService = new TraceableExecutorService(beanFactory,
					// 线程池监控
					executorService, threadPoolName);
		}
		return executorService;
	}
}
