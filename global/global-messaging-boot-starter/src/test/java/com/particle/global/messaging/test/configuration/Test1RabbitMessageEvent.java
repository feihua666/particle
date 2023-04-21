package com.particle.global.messaging.test.configuration;

import com.particle.global.dto.messaging.event.AbstractMessageEvent;
import lombok.Data;

/**
 * <p>
 *
 * </p>
 *
 * @author yangwei
 * @since 2022-10-11 17:18
 */
@Data
public class Test1RabbitMessageEvent extends AbstractMessageEvent<String> {

	public Test1RabbitMessageEvent(String identifier, String data, String mq) {
		super(identifier, data, mq);
	}

	public static Test1RabbitMessageEvent create(String mq, String msgData) {
		Test1RabbitMessageEvent test1RabbitMessageEvent = new Test1RabbitMessageEvent("Test1RabbitMessageEvent",msgData, mq);
		return test1RabbitMessageEvent;
	}
}
