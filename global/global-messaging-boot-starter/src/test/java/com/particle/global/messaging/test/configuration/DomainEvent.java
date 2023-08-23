package com.particle.global.messaging.test.configuration;

import com.particle.global.dto.messaging.event.AbstractMessageEvent;

/**
 * <p>
 * 领域事件
 * </p>
 *
 * @author yangwei
 * @since 2022-05-16 20:24
 */

public class DomainEvent<T> extends AbstractMessageEvent<T> {

	private String testName = "testName";

	public DomainEvent(String identifier, T data, String mq) {
		super(identifier, data, mq);
	}
}
