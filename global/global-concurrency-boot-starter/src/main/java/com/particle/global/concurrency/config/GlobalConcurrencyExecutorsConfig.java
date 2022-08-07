package com.particle.global.concurrency.config;

import cn.hutool.core.util.ClassLoaderUtil;
import com.particle.global.concurrency.threadpool.CustomExecutors;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * <p>
 * 通用的线程池配置类
 * </p>
 *
 * @author yangwei
 * @since 2021-06-13 21:14
 */
@Configuration
public class GlobalConcurrencyExecutorsConfig {

	/**
	 * 异步槽位线程池
	 * @param beanFactory
	 * @return
	 */
	@Bean(name = "asynSlotTaskExecutor", destroyMethod = "shutdown")
	public ExecutorService asynSlotTaskExecutor(BeanFactory beanFactory) {
		if (ClassLoaderUtil.isPresent("io.micrometer.core.instrument.MeterRegistry")) {
			return CustomExecutors.newExecutorService(beanFactory,
					"asynSlotTaskExecutor",
					5,
					100,
					1000,
					new LinkedBlockingQueue<>(1000),
					// 如果拒绝自己执行
					new ThreadPoolExecutor.CallerRunsPolicy(),
					true,beanFactory.getBean(MeterRegistry.class));
		}
		return CustomExecutors.newExecutorService(beanFactory,
				"asynSlotTaskExecutor",
				5,
				100,
				1000,
				new LinkedBlockingQueue<>(1000),
				// 如果拒绝自己执行
				new ThreadPoolExecutor.CallerRunsPolicy(),
				true,null);

	}
}
