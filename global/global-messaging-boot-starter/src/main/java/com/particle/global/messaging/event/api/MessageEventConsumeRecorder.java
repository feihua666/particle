package com.particle.global.messaging.event.api;

import com.particle.global.dto.messaging.event.AbstractMessageEvent;


/**
 * <p>
 * 消息消费记录器
 * </p>
 *
 * @author yangwei
 * @since 2022-09-16 11:30
 */
public interface MessageEventConsumeRecorder {

    /**
     * 记录消息消费情况
     * @param messageEvent
     * @return
     */
    boolean record(AbstractMessageEvent messageEvent);

    /**
     * 删除所有消费记录
     */
    void deleteAll();
}
