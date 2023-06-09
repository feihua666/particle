package com.particle.global.mybatis.plus.config;

import com.particle.global.concurrency.threadpool.CustomExecutors;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * <p>
 * 线程池配置
 * </p>
 *
 * @author yangwei
 * @since 2022-08-05 17:16
 */
@Configuration
public class GlobalMybatisExecutorsConfig {

	public static final String commonDbTaskExecutor = "commonDbTaskExecutor";

	/**
	 * 通用数据库查询线程池
	 * @param beanFactory
	 * @return
	 */
	@Bean(name = commonDbTaskExecutor, destroyMethod = "shutdown")
	public ExecutorService commonDbTaskExecutor(BeanFactory beanFactory) {

		return CustomExecutors.newExecutorService(beanFactory,
				commonDbTaskExecutor,
				5,
				100,
				1000 * 60,
				new LinkedBlockingQueue<>(1000),
				// 如果拒绝自己执行
				new ThreadPoolExecutor.CallerRunsPolicy(),
				false);
	}


}
