package com.particle.openplatform.domain.event;

import com.particle.common.domain.event.DomainEvent;
import com.particle.component.light.share.message.MessageConstants;

/**
 * <p>
 * 开放平台调用记录领域事件
 * </p>
 *
 * @author yangwei
 * @since 2023-04-20 14:32
 */

public class OpenplatformOpenapiRecordDomainEvent extends DomainEvent<OpenplatformOpenapiRecordDomainEventContent> {

	public OpenplatformOpenapiRecordDomainEvent() {
	}

	public OpenplatformOpenapiRecordDomainEvent(String identifier, OpenplatformOpenapiRecordDomainEventContent data, String mq) {
		super(identifier, data, mq);
	}

	public OpenplatformOpenapiRecordDomainEvent(OpenplatformOpenapiRecordDomainEventContent data) {
		super("openplatformRecordDomainEvent", data, MessageConstants.Producer.openplatformOpenapiRecordProducerOutZeroBindingName);
	}
}
