package com.particle.global.messaging.event;

import com.particle.global.concurrency.lock.LockExecutor;
import com.particle.global.concurrency.lock.distribute.DistributedShedLockExecutor;
import com.particle.global.dto.messaging.event.AbstractMessageEvent;
import com.particle.global.exception.biz.LockAlreadyOccupiedException;
import com.particle.global.messaging.event.api.MessageEventPublisher;
import com.particle.global.messaging.event.api.MessageEventRepository;
import com.particle.global.messaging.event.api.MessageEventSender;
import com.particle.global.tool.retry.RetryTool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 全局默认的消息事件发布器
 * </p>
 *
 * @author yangwei
 * @since 2022-09-20 13:10
 */
@Slf4j
public class GlobalDefaultMessageEventPublisher implements MessageEventPublisher {
    private final MessageEventRepository eventDao;
    private final LockExecutor lockExecutor;
    private final MessageEventSender sender;

    public GlobalDefaultMessageEventPublisher(MessageEventRepository eventDao,
                                              LockExecutor lockExecutor,
                                              MessageEventSender sender) {
        this.eventDao = eventDao;
        this.lockExecutor = lockExecutor;
        this.sender = sender;
    }


    @Override
    public void publishNextBatch() {
        publishNextBatch(100);
    }

    @Override
    public void publishNextBatch(int size) {
        try {
            RetryTool.execute(context -> {
                String lockKey = "default-message-event-publisher";
                try {
                    lockExecutor.execute(() -> doPublish(size), lockKey);
                } catch (LockAlreadyOccupiedException e) {
                    log.warn("{} LockAlreadyOccupied. ignored!",lockKey);
                    // 不用再重试了
                    context.setExhaustedOnly();
                }
                return null;
            });
        } catch (Throwable e) {
            log.error("Error while publish message events", e);
        }
    }


    private Void doPublish(int size) {
        List<AbstractMessageEvent> newestEvents = eventDao.nextPublishBatch(size);
        int toBePublishedSize = newestEvents.size();
        log.debug("to be Published message event size is {}.", toBePublishedSize);
        if (toBePublishedSize > 0) {
            log.debug("to be Published messageId is {}.", newestEvents.stream().map(item -> item.getMessageId()).collect(Collectors.toList()));
        }

        newestEvents.forEach(event -> {
            try {
               boolean flag = sender.send(event);
                if (flag) {
                    log.debug("Published success {}.", event);
                    eventDao.markAsPublished(event.getMessageId());
                }else {
                    log.debug("Published failed.you should forcePublish this event for retry {}.", event);
                    eventDao.markAsPublishFailed(event.getMessageId());
                }

            } catch (Throwable t) {
                log.error("Error while publish message event {}.", event, t);
                eventDao.markAsPublishFailed(event.getMessageId());
            }
        });
        return null;
    }

    @Override
    public void forcePublish(String eventId) {
        try {
            AbstractMessageEvent event = eventDao.get(eventId);
            sender.send(event);
            eventDao.markAsPublished(eventId);
        } catch (Throwable t) {
            eventDao.markAsPublishFailed(eventId);
            log.error("Error while force publish message event {}.", eventId, t);
        }
    }
}


