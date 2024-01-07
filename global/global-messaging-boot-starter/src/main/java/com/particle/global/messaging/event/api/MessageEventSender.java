package com.particle.global.messaging.event.api;

import com.particle.global.dto.messaging.event.AbstractMessageEvent;

import java.util.List;

/**
 * <p>
 * 消息发送器
 * </p>
 *
 * @author yangwei
 * @since 2022-09-16 11:30
 */
public interface MessageEventSender {
    /**
     * 发送消息
     * @param event
     * @return true=发送成功，false=发送失败，发送失败可以 使用 {@link MessageEventPublisher#forcePublish(java.lang.String)} 重发
     */
    boolean send(AbstractMessageEvent event);

    /**
     * 批量发送
     * @param events 发送结束后，可以通过查看 events status 来获取发送状态
     */
    default void sendBatch(List<AbstractMessageEvent> events){
        events.forEach(this::send);
    }

    /**
     * 异步发送消息
     * @param event
     * @return true=发送成功，false=发送失败，发送失败可以 使用 {@link MessageEventPublisher#forcePublish(java.lang.String)} 重发
     */
    boolean sendAsync(AbstractMessageEvent event);

    /**
     * 异步批量发送
     * @param events 发送结束后，可以通过查看 events status 来获取发送状态
     */
    default void sendBatchAsync(List<AbstractMessageEvent> events){
        events.forEach(this::sendAsync);
    }


}
