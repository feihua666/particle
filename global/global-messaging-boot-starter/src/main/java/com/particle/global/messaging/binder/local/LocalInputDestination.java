package com.particle.global.messaging.binder.local;

import org.springframework.messaging.Message;

/**
 * <p>
 * https://github.com/spring-cloud/spring-cloud-stream/blob/main/core/spring-cloud-stream-test-binder/src/main/java/org/springframework/cloud/stream/binder/test/InputDestination.java
 * </p>
 *
 * @author yangwei
 * @since 2023-05-22 10:20
 */
public class LocalInputDestination extends LocalAbstractDestination {

	/**
	 * Allows the {@link Message} to be sent to a Binder to be delegated to a default binding
	 * destination (e.g., "function-in-0" for cases where you only have a single function with the name 'function').
	 * @param message message to send
	 */
	public void send(Message<?> message) {
		this.getChannel(0).send(message);
	}

	/**
	 * @param message message to send
	 * @param inputIndex input index
	 * @deprecated since 3.0.2 in favor of {@link #receive(long, String)} where you should use the actual binding name (e.g., "foo-in-0")
	 */
	@Deprecated
	public void send(Message<?> message, int inputIndex) {
		this.getChannel(inputIndex).send(message);
	}

	/**
	 * Allows the {@link Message} to be sent to a Binder's destination.<br>
	 * This needs a bit of clarification. Just like with any binder, 'destination'
	 * name and 'binding' name are usually the same unless additional configuration
	 * is provided. For example; Assume you have a function 'uppercase'. The
	 * 'binding' names for this function would be 'uppercase-in-0' (for input) and
	 * 'uppercase-out-0' (for output). The 'destination' names would match as well
	 * unless you decide to provide something like
	 * 'spring.cloud.stream.bindings.uppercase-in-0.destination=upper' at which
	 * point the binding names and destination names are different. <br>
	 * <br>
	 * So, it is important to remember that since this binder's goal is to emulate
	 * real binders and real messaging systems you are sending TO and receiving FROM
	 * destination (as if it was real broker destination) and binder will map and
	 * delegate what you send to the binder's destination to individual bindings as
	 * would the real binder.
	 *
	 * *
	 *
	 * <pre>
	 *
	 * // assume the following properties
	 * "--spring.cloud.function.definition=uppercase",
	 * "--spring.cloud.stream.bindings.uppercase-in-0.destination=upper",
	 * "--spring.cloud.stream.bindings.uppercase-out-0.destination=upperout"
	 *
	 * // send/receive
	 * inputDestination.send(message, "upper");
	 * Message&lt;byte[]&gt; resultMessage = outputDestination.receive(1000, "upperout");
	 *
	 * // if 'destination' property is not provided for both input and output
	 * inputDestination.send(message, "uppercase-in-0");
	 * Message&lt;byte[]&gt; resultMessage = outputDestination.receive(1000, "uppercase-out-0");
	 * </pre>
	 *
	 * @param message    message to send
	 * @param destinationName the name of the destination
	 */
	public void send(Message<?> message, String destinationName) {
		this.getChannelByName(destinationName).send(message);
	}

}
