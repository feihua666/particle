package com.particle.global.messaging.binder.local;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.integration.channel.AbstractSubscribableChannel;
import org.springframework.messaging.Message;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * https://github.com/spring-cloud/spring-cloud-stream/blob/v3.2.8/core/spring-cloud-stream/src/test/java/org/springframework/cloud/stream/binder/test/OutputDestination.java
 * </p>
 *
 * @author yangwei
 * @since 2023-05-22 10:22
 */
public class LocalOutputDestination extends LocalAbstractDestination {

	private final Log log = LogFactory.getLog(LocalOutputDestination.class);

	private final ConcurrentHashMap<String, BlockingQueue<Message<byte[]>>> messageQueues = new ConcurrentHashMap<>();

	public Message<byte[]> receive(long timeout, String bindingName) {
		try {
			bindingName = bindingName.endsWith(".destination") ? bindingName : bindingName + ".destination";
			return this.outputQueue(bindingName).poll(timeout, TimeUnit.MILLISECONDS);
		}
		catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		return null;
	}

	/**
	 * Will clear all output destinations.
	 *
	 * @since 3.0.6
	 */
	public void clear() {
		this.messageQueues.values().forEach(v -> v.clear());
	}

	/**
	 * Will clear output destination with specified name.
	 *
	 * @param destinationName the name of the output destination to be cleared.
	 * @return true if attempt to clear specific destination is successful otherwise false.
	 * @since 3.0.6
	 */
	public boolean clear(String destinationName) {
		String queueName = destinationName.endsWith(".destination") ? destinationName : destinationName + ".destination";
		if (StringUtils.hasText(destinationName) && this.messageQueues.containsKey(queueName)) {
			this.messageQueues.get(queueName).clear();
			return true;
		}
		return false;
	}
	/**
	 * Allows to access {@link Message}s received by this {@link OutputDestination}.
	 * @param timeout how long to wait before giving up
	 * @return received message
	 * @deprecated since 3.0.2 in favor of {@link #receive(long, String)} where you should use the actual binding name (e.g., "foo-in-0")
	 */
	@Deprecated
	public Message<byte[]> receive(long timeout, int bindingIndex) {
		log.warn("!!!While 'receive(long timeout, int bindingIndex)' method may still work it is deprecated no longer supported. "
				+ "It will be removed after 3.1.3 release. Please use 'receive(long timeout, String bindingName)'");
		try {
			BlockingQueue<Message<byte[]>> destinationQueue = (new ArrayList<>(this.messageQueues.values())).get(bindingIndex);
			return destinationQueue.poll(timeout, TimeUnit.MILLISECONDS);
		}
		catch (Exception e) {
			Thread.currentThread().interrupt();
		}
		return null;
	}

	/**
	 * Allows to access {@link Message}s received by this {@link OutputDestination}.
	 * @return received message
	 */
	public Message<byte[]> receive() {
		return this.receive(0, 0);
	}

	public Message<byte[]> receive(long timeout) {
		return this.receive(timeout, 0);
	}

	@SuppressWarnings("unchecked")
	@Override
	void afterChannelIsSet(int channelIndex, String bindingName) {
		if (((AbstractSubscribableChannel) this.getChannelByName(bindingName)).getSubscriberCount() < 1) {
			this.getChannelByName(bindingName).subscribe(message -> this.outputQueue(bindingName).offer((Message<byte[]>) message));
		}
	}

	private BlockingQueue<Message<byte[]>> outputQueue(String bindingName) {
		this.messageQueues.putIfAbsent(bindingName, new LinkedTransferQueue<>());
		return this.messageQueues.get(bindingName);
	}
}
