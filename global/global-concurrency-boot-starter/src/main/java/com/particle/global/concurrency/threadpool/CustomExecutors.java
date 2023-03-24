package com.particle.global.concurrency.threadpool;

import cn.hutool.core.thread.ThreadFactoryBuilder;
import cn.hutool.core.util.ClassLoaderUtil;
import com.particle.global.light.share.constant.ClassAdapterConstants;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;

import java.util.concurrent.*;

/**
 * <p>
 * 自定义线程池工厂类
 * </p>
 *
 * @author yangwei
 * @since 2021-06-13 18:55
 */
@Slf4j
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
													 boolean preStartCoreThread) {

		ExecutorService executorService = doNewExecutorService(beanFactory,
				threadPoolName,
				corePoolSize,
				maximumPoolSize,
				keepAliveTime,
				workQueue,
				handler,
				preStartCoreThread,false);
		return executorService;
	}

	/**
	 * 任务计划线程池
	 * @param beanFactory
	 * @param threadPoolName
	 * @param corePoolSize
	 * @param handler
	 * @param preStartCoreThread
	 * @return
	 */
	public static ScheduledExecutorService newScheduledExecutorService(BeanFactory beanFactory,
																String threadPoolName,
																int corePoolSize,
																RejectedExecutionHandler handler,
																boolean preStartCoreThread) {
		ExecutorService executorService = doNewExecutorService(beanFactory,
				threadPoolName,
				corePoolSize,
				0,
				0,
				null,
				handler,
				preStartCoreThread,true);
		return ((ScheduledExecutorService) executorService);
	}

	private static ExecutorService doNewExecutorService(BeanFactory beanFactory,
													 String threadPoolName,
													 int corePoolSize,
													 int maximumPoolSize,
													 long keepAliveTime,
													 BlockingQueue<Runnable> workQueue,
													 RejectedExecutionHandler handler,
													 boolean preStartCoreThread,boolean scheduled) {


		MeterRegistry meterRegistry = null;
		try {
			if (ClassLoaderUtil.isPresent(ClassAdapterConstants.METER_REGISTRY_CLASS_NAME)) {
				meterRegistry = beanFactory.getBean(MeterRegistry.class);
			}
		} catch (BeansException e) {
			log.warn("MeterRegistry bean not found in spring ignored when creating executorService named " + threadPoolName);
		}

		// 线程工厂
		ThreadFactory threadFactory = ThreadFactoryBuilder.create().setNamePrefix(threadPoolName).setUncaughtExceptionHandler(new CustomDefaultUncaughtExceptionHandler()).build();

		ThreadPoolExecutor threadPoolExecutor = newThreadPoolExecutor( threadFactory , corePoolSize, maximumPoolSize, keepAliveTime, workQueue, handler, scheduled);


		// 预启动核心线程
		if (preStartCoreThread) {
			threadPoolExecutor.prestartAllCoreThreads();
		}
		ExecutorService executorService = threadPoolExecutor;
		if (ClassLoaderUtil.isPresent(ClassAdapterConstants.EXECUTOR_SERVICE_METRICS_CLASS_NAME)) {
			if (scheduled) {
				executorService = io.micrometer.core.instrument.binder.jvm.ExecutorServiceMetrics.monitor(meterRegistry, ((CustomScheduledThreadPoolExecutor) threadPoolExecutor), threadPoolName);
			}else{
				executorService = io.micrometer.core.instrument.binder.jvm.ExecutorServiceMetrics.monitor(meterRegistry, threadPoolExecutor, threadPoolName);
			}
		}
		if (ClassLoaderUtil.isPresent(ClassAdapterConstants.TRACEABLE_EXECUTOR_SERVICE_CLASS_NAME)) {
			if (scheduled) {
				// 异步链路追踪
				executorService = new org.springframework.cloud.sleuth.instrument.async.TraceableScheduledExecutorService(beanFactory, executorService, threadPoolName);
			}else {
				// 异步链路追踪
				executorService = new org.springframework.cloud.sleuth.instrument.async.TraceableExecutorService(beanFactory, executorService, threadPoolName);
			}
			
		}
		return executorService;
	}

	/**
	 * 创建threadPool
	 * @param threadFactory
	 * @param corePoolSize
	 * @param maximumPoolSize
	 * @param keepAliveTime
	 * @param workQueue
	 * @param handler
	 * @param scheduled
	 * @return
	 */
	private static ThreadPoolExecutor newThreadPoolExecutor(ThreadFactory threadFactory ,
													 int corePoolSize,
													 int maximumPoolSize,
													 long keepAliveTime,
													 BlockingQueue<Runnable> workQueue,
													 RejectedExecutionHandler handler,
													 boolean scheduled){
		// 线程池，自定义
		if (scheduled) {
			return new CustomScheduledThreadPoolExecutor(corePoolSize, threadFactory, handler);
		}
		// 线程池，自定义
		return new CustomThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.MILLISECONDS, workQueue, threadFactory, handler);
	}

}
