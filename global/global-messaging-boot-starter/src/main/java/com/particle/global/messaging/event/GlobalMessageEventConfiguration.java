package com.particle.global.messaging.event;

import com.particle.global.concurrency.lock.LockExecutor;
import com.particle.global.messaging.event.api.MessageEventConsumeRecorder;
import com.particle.global.messaging.event.api.MessageEventPublisher;
import com.particle.global.messaging.event.api.MessageEventRepository;
import com.particle.global.messaging.event.api.MessageEventSender;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * 全局消息事件配置类
 * </p>
 *
 * @author yangwei
 * @since 2022-09-20 13:10
 */

@Configuration
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

}
