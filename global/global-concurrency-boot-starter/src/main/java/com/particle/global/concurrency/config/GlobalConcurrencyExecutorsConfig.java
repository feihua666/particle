package com.particle.global.concurrency.config;

import com.particle.global.concurrency.threadpool.CustomExecutors;
import com.particle.global.light.share.concurrency.ConcurrencyConstants;
import org.springframework.beans.factory.BeanFactory;
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
@Configuration(proxyBeanMethods = false)
public class GlobalConcurrencyExecutorsConfig {

	/**
	 * 异步槽位线程池
	 * @param beanFactory
	 * @return
	 */
	@Bean(name = ConcurrencyConstants.default_global_asyn_slot_task_executor, destroyMethod = "shutdown")
	public ExecutorService asynSlotTaskExecutor(BeanFactory beanFactory) {
		return CustomExecutors.newExecutorService(beanFactory,
				ConcurrencyConstants.default_global_asyn_slot_task_executor,
				5,
				16,
				1000 * 60,
				new LinkedBlockingQueue<>(1000),
				// 如果拒绝自己执行
				new ThreadPoolExecutor.CallerRunsPolicy(),
				false);

	}

}
