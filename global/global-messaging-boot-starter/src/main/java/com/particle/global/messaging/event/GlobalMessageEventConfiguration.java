package com.particle.global.messaging.event;

import com.particle.global.concurrency.lock.distribute.DistributedShedLockExecutor;
import com.particle.global.messaging.event.api.MessageEventConsumeRecorder;
import com.particle.global.messaging.event.api.MessageEventPublisher;
import com.particle.global.messaging.event.api.MessageEventRepository;
import com.particle.global.messaging.event.api.MessageEventSender;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * <p>
 * 全局消息事件配置类
 * </p>
 *
 * @author yangwei
 * @since 2022-09-20 13:10
 */
@EnableScheduling
@Configuration
public class GlobalMessageEventConfiguration {

    @Bean
    public MessageEventPublisher messageEventPublisher(MessageEventRepository eventDao,
                                                      DistributedShedLockExecutor lockExecutor,
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

}