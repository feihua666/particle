package com.particle.global.messaging.event.messaging;

import java.util.function.Consumer;

/**
 * <p>
 *
 * </p>
 *
 * @author yangwei
 * @since 2022-10-13 14:32
 */
public interface CloudStreamConsumer<T> extends Consumer<T> {

	@CloudStreamConsume
	@Override
	default public void accept(T t) {
	}
}
