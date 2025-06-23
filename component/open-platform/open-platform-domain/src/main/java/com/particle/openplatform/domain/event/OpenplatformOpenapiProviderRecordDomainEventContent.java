package com.particle.openplatform.domain.event;

import com.particle.global.dto.basic.DTO;
import lombok.Data;

import java.util.List;

/**
 * <p>
 * 事件内容
 * </p>
 *
 * @author yangwei
 * @since 2025-06-23 14:16:07
 */
@Data
public class OpenplatformOpenapiProviderRecordDomainEventContent extends DTO {
	/**
	 * 供应商请求调用记录
	 */
	private List<OpenplatformOpenapiRecordDomainEventContentProviderRecord> providerRecords;

	public static OpenplatformOpenapiProviderRecordDomainEventContent create(List<OpenplatformOpenapiRecordDomainEventContentProviderRecord> providerRecords
															   ) {
		OpenplatformOpenapiProviderRecordDomainEventContent openplatformOpenapiRecordDomainEventContent = new OpenplatformOpenapiProviderRecordDomainEventContent();
		openplatformOpenapiRecordDomainEventContent.providerRecords = providerRecords;

		return openplatformOpenapiRecordDomainEventContent;
	}
}
