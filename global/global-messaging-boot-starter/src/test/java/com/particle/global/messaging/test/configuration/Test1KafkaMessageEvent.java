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
public class Test1KafkaMessageEvent extends AbstractMessageEvent<String> {

	public Test1KafkaMessageEvent(String identifier, String data, String mq) {
		super(identifier, data, mq);
	}

	public static Test1KafkaMessageEvent create(String mq, String msgData) {
		Test1KafkaMessageEvent test1RabbitMessageEvent = new Test1KafkaMessageEvent("test", msgData,mq);
		return test1RabbitMessageEvent;
	}
}
