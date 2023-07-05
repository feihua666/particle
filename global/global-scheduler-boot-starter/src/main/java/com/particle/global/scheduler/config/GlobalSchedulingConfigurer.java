package com.particle.global.scheduler.config;

import com.particle.global.concurrency.threadpool.CustomExecutors;
import com.particle.global.light.share.concurrency.ConcurrencyConstants;
import com.particle.global.light.share.scheduler.SchedulerConstants;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * <p>
 * 主要是为了自定义任务计划线程池
 * </p>
 *
 * @author yangwei
 * @since 2023-06-09 15:18
 */
@Configuration
public class GlobalSchedulingConfigurer implements SchedulingConfigurer {

	@Autowired
	private BeanFactory beanFactory;

	private ScheduledTaskRegistrar taskRegistrar;


	@Override
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
		taskRegistrar.setScheduler(globalScheduledTaskExecutor());
		this.taskRegistrar = taskRegistrar;
	}


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

	public ScheduledTaskRegistrar getTaskRegistrar() {
		return taskRegistrar;
	}
}
