package com.particle.global.messaging.event;

import com.particle.global.messaging.event.api.MessageEventPublisher;
import com.particle.global.scheduler.IGlobalScheduler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;


/**
 * <p>
 * 消息事件发布任务计划
 * </p>
 *
 * @author yangwei
 * @since 2022-05-14 12:18
 */
@Slf4j
public class MessageEventBackupPublishScheduler implements IGlobalScheduler {

    private MessageEventPublisher publisher;

    public MessageEventBackupPublishScheduler(MessageEventPublisher publisher) {
        this.publisher = publisher;
    }

    /**
     * 按固定时间定时发布消息，单位默认20秒发送一次
     */
    @Scheduled(fixedDelay = 20000)
    public void run() {
        log.debug("Scheduled trigger message event backup publish process.");
        publisher.publishNextBatch();
    }

}
