package com.particle.openplatform.app;

import com.particle.global.concurrency.threadpool.CustomExecutors;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * <p>
 * 主要配置线程池
 * </p>
 *
 * @author yangwei
 * @since 2024/9/22 11:00
 */
@Configuration(proxyBeanMethods = false)
public class OpenplatformAppConfiguration {

    public static final String openplatformAppBatchQueryExecutor = "openplatformAppBatchQueryExecutor";
    /**
     * 事件发送线程池
     * @param beanFactory
     * @return
     */
    @Bean(name = openplatformAppBatchQueryExecutor, destroyMethod = "shutdown")
    public ExecutorService globalMessageEventExecutor(BeanFactory beanFactory) {
        return CustomExecutors.newExecutorService(beanFactory,
                openplatformAppBatchQueryExecutor,
                5,
                10,
                900 * 60,
                new LinkedBlockingQueue<>(1000),
                // 如果拒绝自己执行
                new ThreadPoolExecutor.CallerRunsPolicy(),
                false);
    }
}
