package com.particle.global.messaging.event.api;

import com.particle.global.dto.messaging.event.AbstractMessageEvent;

import java.util.List;

/**
 * <p>
 * 消息消费记录器
 * </p>
 *
 * @author yangwei
 * @since 2022-09-16 11:30
 */
public interface MessageEventRepository {
    /**
     * 保存
     * @param messages
     */
    void save(List<AbstractMessageEvent> messages);

    /**
     * 删除
     * @param messageId
     */
    void delete(String messageId);

    /**
     * 获取
     * @param messageId
     * @return
     */
    AbstractMessageEvent get(String messageId);

    /**
     * 获取下一个发布批次
     * @param size
     * @return
     */
    List<AbstractMessageEvent> nextPublishBatch(int size);

    /**
     * 标记为已发布
     * @param messageId
     */
    void markAsPublished(String messageId);

    /**
     * 标记为发布失败
     * @param messageId
     */
    void markAsPublishFailed(String messageId);

    /**
     * 全部删除
     */
    void deleteAll();

}
