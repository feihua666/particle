package com.particle.global.messaging.test.configuration;

import com.particle.global.dto.messaging.event.AbstractMessageEvent;

/**
 * <p>
 * 消息事件内容数据为 字符串 测试
 * </p>
 *
 * @author yangwei
 * @since 2022-10-11 17:18
 */

public class MessageStringDataTestEvent extends AbstractMessageEvent<String> {

	public MessageStringDataTestEvent(String identifier, String data, String mq) {
		super(identifier, data, mq);
	}

	public static MessageStringDataTestEvent create(String mq, String msgData) {
		MessageStringDataTestEvent messageStringDataTestEvent = new MessageStringDataTestEvent(MessageStringDataTestEvent.class.getSimpleName(), msgData,mq);
		return messageStringDataTestEvent;
	}
}
