package com.particle.common.domain.event;

import com.particle.global.dto.messaging.event.AbstractMessageEvent;

/**
 * <p>
 * 领域事件
 * 注意：在继承该类时需要保留{@link DomainEvent#DomainEvent(java.lang.String, java.lang.Object, java.lang.String)}的对应参数构造函数，或添加无参构造函数，否则在消费反序列化时会报错，反序列化失败
 * </p>
 *
 * @author yangwei
 * @since 2022-05-16 20:24
 */

public class DomainEvent<T> extends AbstractMessageEvent<T> {

	public DomainEvent(String identifier, T data, String mq) {
		super(identifier, data, mq);
	}
}
