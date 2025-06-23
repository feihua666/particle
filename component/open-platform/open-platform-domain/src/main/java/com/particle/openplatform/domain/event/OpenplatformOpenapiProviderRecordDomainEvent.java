package com.particle.openplatform.domain.event;

import com.particle.common.domain.event.DomainEvent;
import com.particle.component.light.share.message.MessageConstants;

/**
 * <p>
 * 开放平台供应商调用记录领域事件
 * </p>
 *
 * @author yangwei
 * @since 2025-06-23 14:14:40
 */

public class OpenplatformOpenapiProviderRecordDomainEvent extends DomainEvent<OpenplatformOpenapiProviderRecordDomainEventContent> {

	public OpenplatformOpenapiProviderRecordDomainEvent() {
	}

	public OpenplatformOpenapiProviderRecordDomainEvent(String identifier, OpenplatformOpenapiProviderRecordDomainEventContent data, String mq) {
		super(identifier, data, mq);
	}

	public OpenplatformOpenapiProviderRecordDomainEvent(OpenplatformOpenapiProviderRecordDomainEventContent data) {
		super("openplatformProviderRecordDomainEvent", data, MessageConstants.Producer.openplatformOpenapiProviderRecordProducerOutZeroBindingName);
	}
}
