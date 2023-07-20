package com.particle.global.concurrency.threadpool;

import cn.hutool.core.thread.ThreadFactoryBuilder;
import cn.hutool.core.util.ClassLoaderUtil;
import com.alibaba.ttl.threadpool.TtlExecutors;
import com.particle.global.light.share.constant.ClassAdapterConstants;
import com.particle.global.tool.thread.ThreadContextTool;
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
	 * 自定义线程池，带链路追踪，带监控,完善支持使用 {@link ThreadContextTool} 变量继承
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
			if (beanFactory != null && ClassLoaderUtil.isPresent(ClassAdapterConstants.METER_REGISTRY_CLASS_NAME)) {
				meterRegistry = beanFactory.getBean(MeterRegistry.class);
			}
		} catch (BeansException e) {
			log.warn("MeterRegistry bean not found in spring ignored when creating executorService named " + threadPoolName);
		}

		// 线程工厂
		ThreadFactory threadFactory = ThreadFactoryBuilder.create().setNamePrefix(threadPoolName).setUncaughtExceptionHandler(new CustomDefaultUncaughtExceptionHandler()).build();

		ExecutorService threadPoolExecutor = newThreadPoolExecutor( threadFactory , corePoolSize, maximumPoolSize, keepAliveTime, workQueue, handler, scheduled,preStartCoreThread);

		ExecutorService executorService = threadPoolExecutor;

		// 不像 TraceableExecutorService ，目前好像没有找到自动添加监控的方式，这里手动添加
		if (meterRegistry != null && ClassLoaderUtil.isPresent(ClassAdapterConstants.EXECUTOR_SERVICE_METRICS_CLASS_NAME)) {
			if (scheduled) {
				executorService = io.micrometer.core.instrument.binder.jvm.ExecutorServiceMetrics.monitor(meterRegistry, ((ScheduledExecutorService) executorService), threadPoolName);
			}else{
				executorService = io.micrometer.core.instrument.binder.jvm.ExecutorServiceMetrics.monitor(meterRegistry, executorService, threadPoolName);
			}
		}

		/**
		 * 目前不用手动添加链路追踪包装了，当前spring 版本 2.7.14（这并不意味着当前版本以前就是需要手动添加，具体从哪个版本开始不用添加的，不太清楚目前）
		 * 因为在 sleuth 自动配置中已经通过aop处理了，但目前如果使用注解schedued注解时，且将ScheduledExecutorService注入到了spring中，那么在使用 cron时会导致traceid重复，
		 */
/*		if (ClassLoaderUtil.isPresent(ClassAdapterConstants.TRACEABLE_EXECUTOR_SERVICE_CLASS_NAME)) {
			if (scheduled) {
				// 异步链路追踪
				executorService = new org.springframework.cloud.sleuth.instrument.async.TraceableScheduledExecutorService(beanFactory, executorService, threadPoolName);
			}else {
				// 异步链路追踪
				executorService = new org.springframework.cloud.sleuth.instrument.async.TraceableExecutorService(beanFactory, executorService, threadPoolName);
			}

		}*/

		// ExecutorServiceMetrics 不支持ttl包括后的对象类型，放在最后面，否则会报警告：Failed to bind as com.alibaba.ttl.threadpool.ExecutorServiceTtlWrapper is unsupported.
		if (executorService instanceof ScheduledExecutorService) {
			executorService =  TtlExecutors.getTtlScheduledExecutorService(((ScheduledExecutorService) executorService));
		}else {
			executorService = TtlExecutors.getTtlExecutorService(executorService);
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
	public static ThreadPoolExecutor newThreadPoolExecutor(ThreadFactory threadFactory ,
													 int corePoolSize,
													 int maximumPoolSize,
													 long keepAliveTime,
													 BlockingQueue<Runnable> workQueue,
													 RejectedExecutionHandler handler,
													 boolean scheduled, boolean preStartCoreThread){
		// 线程池，自定义
		if (scheduled) {
			CustomScheduledThreadPoolExecutor customScheduledThreadPoolExecutor = new CustomScheduledThreadPoolExecutor(corePoolSize, threadFactory, handler);
			// 预启动核心线程
			if (preStartCoreThread) {
				customScheduledThreadPoolExecutor.prestartAllCoreThreads();
			}
			return customScheduledThreadPoolExecutor;

		}
		// 线程池，自定义
		CustomThreadPoolExecutor customThreadPoolExecutor = new CustomThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.MILLISECONDS, workQueue, threadFactory, handler);
		// 预启动核心线程
		if (preStartCoreThread) {
			customThreadPoolExecutor.prestartAllCoreThreads();
		}

		return customThreadPoolExecutor;
	}

}
