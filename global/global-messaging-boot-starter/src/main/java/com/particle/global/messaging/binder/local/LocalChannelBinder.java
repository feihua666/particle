package com.particle.global.messaging.binder.local;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.binder.AbstractMessageChannelBinder;
import org.springframework.cloud.stream.binder.ConsumerProperties;
import org.springframework.cloud.stream.binder.ProducerProperties;
import org.springframework.cloud.stream.provisioning.ConsumerDestination;
import org.springframework.cloud.stream.provisioning.ProducerDestination;
import org.springframework.core.AttributeAccessor;
import org.springframework.integration.IntegrationMessageHeaderAccessor;
import org.springframework.integration.acks.AcknowledgmentCallback;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.endpoint.MessageProducerSupport;
import org.springframework.integration.handler.BridgeHandler;
import org.springframework.integration.support.DefaultErrorMessageStrategy;
import org.springframework.integration.support.ErrorMessageStrategy;
import org.springframework.integration.support.MapBuilder;
import org.springframework.messaging.*;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.retry.RecoveryCallback;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.RetryListener;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.function.Consumer;

/**
 * <p>
 * https://github.com/spring-cloud/spring-cloud-stream/blob/v3.2.8/core/spring-cloud-stream/src/test/java/org/springframework/cloud/stream/binder/test/TestChannelBinder.java
 * </p>
 *
 * @author yangwei
 * @since 2023-05-22 10:41
 */
@Slf4j
public class LocalChannelBinder extends AbstractMessageChannelBinder<ConsumerProperties, ProducerProperties, LocalChannelBinderProvisioner> {

	@Autowired
	private BeanFactory beanFactory;

	private Message<?> lastError;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private MessageSource<?> messageSourceDelegate = () -> new GenericMessage<>(
			"polled data", new MapBuilder()
			.put(MessageHeaders.CONTENT_TYPE, "text/plain")
			.put(IntegrationMessageHeaderAccessor.ACKNOWLEDGMENT_CALLBACK, (AcknowledgmentCallback) status -> {
			}).get());

	public LocalChannelBinder(LocalChannelBinderProvisioner provisioningProvider) {
		super(new String[] {}, provisioningProvider);
	}

	/**
	 * Set a delegate {@link MessageSource} for pollable consumers.
	 * @param messageSourceDelegate the delegate.
	 */
	@Autowired(required = false)
	public void setMessageSourceDelegate(MessageSource<byte[]> messageSourceDelegate) {
		this.messageSourceDelegate = messageSourceDelegate;
	}

	public Message<?> getLastError() {
		return this.lastError;
	}

	public void resetLastError() {
		this.lastError = null;
	}

	@Override
	protected MessageHandler createProducerMessageHandler(ProducerDestination destination,
														  ProducerProperties producerProperties, MessageChannel errorChannel)
			throws Exception {
		BridgeHandler handler = new BridgeHandler();
		handler.setBeanFactory(this.beanFactory);
		handler.setOutputChannel(
				((LocalChannelBinderProvisioner.SpringIntegrationProducerDestination) destination).getChannel());
		return handler;
	}

	@Override
	protected MessageProducer createConsumerEndpoint(ConsumerDestination destination,
													 String group, ConsumerProperties properties) throws Exception {
		ErrorMessageStrategy errorMessageStrategy = new DefaultErrorMessageStrategy();
		SubscribableChannel siBinderInputChannel = ((LocalChannelBinderProvisioner.SpringIntegrationConsumerDestination) destination)
				.getChannel();

		IntegrationMessageListeningContainer messageListenerContainer = new IntegrationMessageListeningContainer();
		IntegrationBinderInboundChannelAdapter adapter = new IntegrationBinderInboundChannelAdapter(
				messageListenerContainer);

		String groupName = StringUtils.hasText(group) ? group : "anonymous";
		ErrorInfrastructure errorInfrastructure = registerErrorInfrastructure(destination,
				groupName, properties);
		if (properties.getMaxAttempts() > 1) {
			adapter.setRetryTemplate(buildRetryTemplate(properties));
			adapter.setRecoveryCallback(errorInfrastructure.getRecoverer());
		}
		else {
			adapter.setErrorMessageStrategy(errorMessageStrategy);
			adapter.setErrorChannel(errorInfrastructure.getErrorChannel());
		}

		siBinderInputChannel.subscribe(messageListenerContainer);

		return adapter;
	}

	@Override
	protected PolledConsumerResources createPolledConsumerResources(String name,
																	String group, ConsumerDestination destination,
																	ConsumerProperties consumerProperties) {
		return new PolledConsumerResources(this.messageSourceDelegate,
				registerErrorInfrastructure(destination, group, consumerProperties));
	}

	@Override
	protected MessageHandler getErrorMessageHandler(ConsumerDestination destination,
													String group, ConsumerProperties consumerProperties) {
		return m -> {
			log.debug("Error handled: " + m);
			this.lastError = m;
		};
	}

	/**
	 * Implementation of simple message listener container modeled after AMQP
	 * SimpleMessageListenerContainer.
	 */
	private static class IntegrationMessageListeningContainer implements MessageHandler {

		private Consumer<Message<?>> listener;

		@Override
		public void handleMessage(Message<?> message) throws MessagingException {
			this.listener.accept(message);
		}

		public void setMessageListener(Consumer<Message<?>> listener) {
			this.listener = listener;
		}

	}

	/**
	 * Implementation of inbound channel adapter modeled after AmqpInboundChannelAdapter.
	 */
	private static class IntegrationBinderInboundChannelAdapter
			extends MessageProducerSupport {

		private static final ThreadLocal<AttributeAccessor> attributesHolder = new ThreadLocal<AttributeAccessor>();

		private final IntegrationMessageListeningContainer listenerContainer;

		private RetryTemplate retryTemplate;

		private RecoveryCallback<? extends Object> recoveryCallback;

		IntegrationBinderInboundChannelAdapter(
				IntegrationMessageListeningContainer listenerContainer) {
			this.listenerContainer = listenerContainer;
		}

		// Temporarily unused until DLQ strategy for this binder becomes a requirement
		public void setRecoveryCallback(
				RecoveryCallback<? extends Object> recoveryCallback) {
			this.recoveryCallback = recoveryCallback;
		}

		public void setRetryTemplate(RetryTemplate retryTemplate) {
			this.retryTemplate = retryTemplate;
		}

		@Override
		protected void onInit() {
			if (this.retryTemplate != null) {
				Assert.state(getErrorChannel() == null,
						"Cannot have an 'errorChannel' property when a 'RetryTemplate' is "
								+ "provided; use an 'ErrorMessageSendingRecoverer' in the 'recoveryCallback' property to "
								+ "send an error message when retries are exhausted");
			}
			Listener messageListener = new Listener();
			if (this.retryTemplate != null) {
				this.retryTemplate.registerListener(messageListener);
			}
			this.listenerContainer.setMessageListener(messageListener);
		}

		protected class Listener implements RetryListener, Consumer<Message<?>> {

			@Override
			@SuppressWarnings("unchecked")
			public void accept(Message<?> message) {
				try {
					if (IntegrationBinderInboundChannelAdapter.this.retryTemplate == null) {
						try {
							processMessage(message);
						}
						finally {
							attributesHolder.remove();
						}
					}
					else {
						IntegrationBinderInboundChannelAdapter.this.retryTemplate
								.execute(context -> {
									processMessage(message);
									return null;
								}, (RecoveryCallback<Object>) IntegrationBinderInboundChannelAdapter.this.recoveryCallback);
					}
				}
				catch (RuntimeException e) {
					if (getErrorChannel() != null) {
						getMessagingTemplate()
								.send(getErrorChannel(),
										buildErrorMessage(null, new IllegalStateException(
												"Message conversion failed: " + message,
												e)));
					}
					else {
						throw e;
					}
				}
			}

			private void processMessage(Message<?> message) {
				sendMessage(message);
			}

			@Override
			public <T, E extends Throwable> boolean open(RetryContext context,
														 RetryCallback<T, E> callback) {
				if (IntegrationBinderInboundChannelAdapter.this.recoveryCallback != null) {
					attributesHolder.set(context);
				}
				return true;
			}

			@Override
			public <T, E extends Throwable> void close(RetryContext context,
													   RetryCallback<T, E> callback, Throwable throwable) {
				attributesHolder.remove();
			}

			@Override
			public <T, E extends Throwable> void onError(RetryContext context,
														 RetryCallback<T, E> callback, Throwable throwable) {
				// Empty
			}

		}

	}

}
