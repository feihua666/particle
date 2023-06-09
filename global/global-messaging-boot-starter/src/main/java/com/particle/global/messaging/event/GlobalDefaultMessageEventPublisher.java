package com.particle.global.messaging.event;

import com.particle.global.concurrency.lock.LockExecutor;
import com.particle.global.concurrency.lock.distribute.DistributedShedLockExecutor;
import com.particle.global.dto.messaging.event.AbstractMessageEvent;
import com.particle.global.messaging.event.api.MessageEventPublisher;
import com.particle.global.messaging.event.api.MessageEventRepository;
import com.particle.global.messaging.event.api.MessageEventSender;
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
    private final RetryTemplate retryTemplate;

    public GlobalDefaultMessageEventPublisher(MessageEventRepository eventDao,
                                              LockExecutor lockExecutor,
                                              MessageEventSender sender) {
        this.eventDao = eventDao;
        this.lockExecutor = lockExecutor;
        this.sender = sender;
        this.retryTemplate = retryTemplate();
    }

    private RetryTemplate retryTemplate() {
        RetryTemplate retryTemplate = new RetryTemplate();

        ExponentialBackOffPolicy policy = new ExponentialBackOffPolicy();
        policy.setInitialInterval(200);
        policy.setMaxInterval(2000);
        policy.setMultiplier(2.0);
        retryTemplate.setBackOffPolicy(policy);
        SimpleRetryPolicy retryPolicy = new SimpleRetryPolicy();
        retryPolicy.setMaxAttempts(3);
        retryTemplate.setRetryPolicy(retryPolicy);

        return retryTemplate;
    }

    @Override
    public void publishNextBatch() {
        publishNextBatch(100);
    }

    @Override
    public void publishNextBatch(int size) {
        try {
            retryTemplate.execute(context -> {
                lockExecutor.execute(() -> doPublish(size), "default-message-event-publisher");
                return null;
            });
        } catch (Throwable e) {
            log.error("Error while publish message events", e);
        }
    }


    private Void doPublish(int size) {
        List<AbstractMessageEvent> newestEvents = eventDao.nextPublishBatch(size);
        int toBePublishedSize = newestEvents.size();
        log.info("to be Published message event size is {}.", toBePublishedSize);
        if (toBePublishedSize > 0) {
            log.debug("to be Published messageId is {}.", newestEvents.stream().map(item -> item.getMessageId()).collect(Collectors.toList()));
        }

        newestEvents.forEach(event -> {
            try {
               boolean flag = sender.send(event);
                if (flag) {
                    log.info("Published success {}.", event);
                    eventDao.markAsPublished(event.getMessageId());
                }else {
                    log.info("Published failed.you should forcePublish this event for retry {}.", event);
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


