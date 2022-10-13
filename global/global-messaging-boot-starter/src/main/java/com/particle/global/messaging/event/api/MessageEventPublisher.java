package com.particle.global.messaging.event.api;


/**
 * <p>
 * 消息发布器
 * </p>
 *
 * @author yangwei
 * @since 2022-09-16 11:30
 */
public interface MessageEventPublisher {
    /**
     * 发布下一个批次
     */
    void publishNextBatch();

    /**
     * 发布下一个批次
     * @param size 批次大小
     */
    void publishNextBatch(int size);

    /**
     * 强制发布
     * @param messageId 消息id
     */
    void forcePublish(String messageId);
}
