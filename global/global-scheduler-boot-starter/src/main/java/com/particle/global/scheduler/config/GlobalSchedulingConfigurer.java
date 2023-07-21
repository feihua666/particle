package com.particle.global.scheduler.config;

import com.particle.global.concurrency.threadpool.CustomExecutors;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

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

	private static final String globalAnnotationScheduledTaskExecutor = "globalAnnotationScheduledTaskExecutor";

	@Autowired
	private BeanFactory beanFactory;

	private ScheduledTaskRegistrar taskRegistrar;


	@Override
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
		taskRegistrar.setScheduler(globalAnnotationScheduledTaskExecutor());
		this.taskRegistrar = taskRegistrar;
	}



	/**
	 * 延迟/任务计划执行线程池
	 * 主要用来使用 {@link Scheduled} 的时候使用的线程池，建议不要使用 {@link Bean} 注解，否则导致 traceid重复（在使用cron指定定时条件时，主traceid不变）
	 * @return
	 */
	private ScheduledExecutorService globalAnnotationScheduledTaskExecutor() {
		return CustomExecutors.newScheduledExecutorService(beanFactory,
				globalAnnotationScheduledTaskExecutor,
				5,
				// 如果拒绝自己执行
				new ThreadPoolExecutor.CallerRunsPolicy(),
				false);

	}


	public ScheduledTaskRegistrar getTaskRegistrar() {
		return taskRegistrar;
	}
}
