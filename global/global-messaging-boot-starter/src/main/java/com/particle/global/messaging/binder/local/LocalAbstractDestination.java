package com.particle.global.messaging.binder.local;

import org.springframework.integration.channel.AbstractSubscribableChannel;
import org.springframework.messaging.SubscribableChannel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * <p>
 * https://github.com/spring-cloud/spring-cloud-stream/blob/v3.2.8/core/spring-cloud-stream/src/test/java/org/springframework/cloud/stream/binder/test/AbstractDestination.java
 * </p>
 *
 * @author yangwei
 * @since 2023-05-22 10:19
 */
public abstract class LocalAbstractDestination {

	private final List<AbstractSubscribableChannel> channels = new ArrayList<>();

	SubscribableChannel getChannel(int index) {
		return this.channels.get(index);
	}

	void setChannel(SubscribableChannel channel) {
		this.channels.add((AbstractSubscribableChannel) channel);
		this.afterChannelIsSet(this.channels.size() - 1, ((AbstractSubscribableChannel) channel).getBeanName());
	}

	void afterChannelIsSet(int channelIndex, String name) {
		// noop
	}

	SubscribableChannel getChannelByName(String name) {
		name = name.endsWith(".destination") ? name : name + ".destination";
		for (AbstractSubscribableChannel subscribableChannel : channels) {
			if (subscribableChannel.getBeanName().equals(name)) {
				return subscribableChannel;
			}
		}
		return null;
	}
}
