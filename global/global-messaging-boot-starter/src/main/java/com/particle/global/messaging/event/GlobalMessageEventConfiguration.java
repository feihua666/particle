package com.particle.global.messaging.event;

import com.particle.global.concurrency.lock.LockExecutor;
import com.particle.global.concurrency.threadpool.CustomExecutors;
import com.particle.global.messaging.event.api.MessageEventConsumeRecorder;
import com.particle.global.messaging.event.api.MessageEventPublisher;
import com.particle.global.messaging.event.api.MessageEventRepository;
import com.particle.global.messaging.event.api.MessageEventSender;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * <p>
 * 全局消息事件配置类
 * </p>
 *
 * @author yangwei
 * @since 2022-09-20 13:10
 */

@Configuration(proxyBeanMethods = false)
public class GlobalMessageEventConfiguration {

    @Bean
    public MessageEventPublisher messageEventPublisher(MessageEventRepository eventDao,
                                                       LockExecutor lockExecutor,
                                                      MessageEventSender eventSender) {
        return new GlobalDefaultMessageEventPublisher(eventDao,
                lockExecutor,
                eventSender);
    }

    @Bean
    public MessageEventBackupPublishScheduler messageEventBackupPublishScheduler(MessageEventPublisher eventPublisher) {
        return new MessageEventBackupPublishScheduler(eventPublisher);
    }

    @Bean
    public MessageEventConsumeWrapper messageEventConsumeWrapper(MessageEventConsumeRecorder eventRecorder) {
        return new MessageEventConsumeWrapper(eventRecorder);
    }

    public static final String  globalMessageEventExecutor = "globalMessageEventExecutor";
    /**
     * 事件发送线程池
     * @param beanFactory
     * @return
     */
    @Bean(name = globalMessageEventExecutor, destroyMethod = "shutdown")
    public ExecutorService globalMessageEventExecutor(BeanFactory beanFactory) {
        return CustomExecutors.newExecutorService(beanFactory,
                globalMessageEventExecutor,
                5,
                10,
                900 * 60,
                new LinkedBlockingQueue<>(1000),
                // 如果拒绝自己执行
                new ThreadPoolExecutor.CallerRunsPolicy(),
                false);
    }

}
