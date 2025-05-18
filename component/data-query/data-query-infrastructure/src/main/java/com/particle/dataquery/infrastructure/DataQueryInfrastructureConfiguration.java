package com.particle.dataquery.infrastructure;

import com.particle.global.concurrency.threadpool.CustomExecutors;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * <p>
 *
 * </p>
 *
 * @author yangwei
 * @since 2023-03-24 00:13
 */
@Configuration(proxyBeanMethods = false)
public class DataQueryInfrastructureConfiguration {

	public static final String dataQueryDataApiExecutor = "dataQueryDataApiExecutor";
	/**
	 * 通用数据库查询线程池
	 * @param beanFactory
	 * @return
	 */
	@Bean(name = dataQueryDataApiExecutor, destroyMethod = "shutdown")
	public ExecutorService dataQueryDataApiExecutor(BeanFactory beanFactory) {
		return CustomExecutors.newExecutorService(beanFactory,
				dataQueryDataApiExecutor,
				15,
				24,
				1000 * 60,
				new LinkedBlockingQueue<>(1000),
				// 如果拒绝自己执行
				new ThreadPoolExecutor.CallerRunsPolicy(),
				false);
	}
}
