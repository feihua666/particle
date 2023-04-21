package com.particle.global.messaging.event.messaging;

import com.particle.global.dto.messaging.event.AbstractMessageEvent;

/**
 * <p>
 * 本地消息消费监听器
 * </p>
 *
 * @author yangwei
 * @since 2023-04-20 14:52
 */
public interface LocalMessageListener {

	public void onMessage(AbstractMessageEvent event);
}
