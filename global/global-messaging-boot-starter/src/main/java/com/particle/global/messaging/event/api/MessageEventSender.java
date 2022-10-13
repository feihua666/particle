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
     */
    void send(AbstractMessageEvent event);

    /**
     * 指发送
     * @param events
     */
    void sendBatch(List<AbstractMessageEvent> events);
}
