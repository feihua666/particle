package com.particle.global.messaging.event.messaging;

import com.particle.global.messaging.event.MessageEventConsumeWrapper;
import com.particle.global.messaging.event.api.MessageEventSender;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * CloudStreamConfiguration
 * </p>
 *
 * @author yangwei
 * @since 2022-10-12 18:04
 */
@Configuration
public class CloudStreamConfiguration {

	@Bean
	public MessageEventSender globalCloudStreamMessageEventSender(){
		GlobalCloudStreamMessageEventSender globalCloudStreamMessageEventSender = new GlobalCloudStreamMessageEventSender();
		return globalCloudStreamMessageEventSender;
	}

	@Bean
	public CloudStreamMessageEventConsumeAspect cloudStreamMessageEventConsumeAspect(MessageEventConsumeWrapper wrapper) {
		return new CloudStreamMessageEventConsumeAspect(wrapper);
	}
}
