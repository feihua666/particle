package com.particle.global.messaging.event.messaging;

import java.util.function.Consumer;

/**
 * <p>
 * 消息消费接口，可以实现该接口以作为一个消息消费者
 * </p>
 *
 * @author yangwei
 * @since 2022-10-13 14:32
 */
public interface CloudStreamConsumer<T> extends Consumer<T> ,MessageConsumer{

	@CloudStreamConsume
	@Override
	default public void accept(T t) {
		consume(t);
	}

	public void consume(T t);
}
