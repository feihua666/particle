package com.particle.global.scheduler;

import com.particle.global.concurrency.threadpool.CustomExecutors;
import com.particle.global.light.share.scheduler.SchedulerConstants;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * <p>
 * 默认添加一个全局定时任务线程池
 * </p>
 *
 * @author yangwei
 * @since 2023-07-21 18:31
 */
@Configuration(proxyBeanMethods = false)
public class GlobalSchedulerBeanAutoConfiguration {

	@Autowired
	private BeanFactory beanFactory;
	/**
	 * 延迟/任务计划执行线程池
	 * @return
	 */
	@Bean(name = SchedulerConstants.default_global_scheduled_task_executor, destroyMethod = "shutdown")
	public ScheduledExecutorService globalScheduledTaskExecutor() {
		return CustomExecutors.newScheduledExecutorService(beanFactory,
				SchedulerConstants.default_global_scheduled_task_executor,
				5,
				// 如果拒绝自己执行
				new ThreadPoolExecutor.CallerRunsPolicy(),
				false);

	}
}
