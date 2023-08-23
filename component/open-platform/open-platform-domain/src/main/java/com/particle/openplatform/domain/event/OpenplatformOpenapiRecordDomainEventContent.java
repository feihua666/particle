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
 * @since 2023-08-18 17:24
 */
@Data
public class OpenplatformOpenapiRecordDomainEventContent extends DTO {

	/**
	 * 请求调用记录
	 */
	private OpenplatformOpenapiRecordDomainEventContentRecord record;

	/**
	 * 供应商请求调用记录
	 */
	private List<OpenplatformOpenapiRecordDomainEventContentProviderRecord> providerRecords;

	public static OpenplatformOpenapiRecordDomainEventContent create(OpenplatformOpenapiRecordDomainEventContentRecord record,
																	 List<OpenplatformOpenapiRecordDomainEventContentProviderRecord> providerRecords
															   ) {
		OpenplatformOpenapiRecordDomainEventContent openplatformOpenapiRecordDomainEventContent = new OpenplatformOpenapiRecordDomainEventContent();
		openplatformOpenapiRecordDomainEventContent.record = record;
		openplatformOpenapiRecordDomainEventContent.providerRecords = providerRecords;

		return openplatformOpenapiRecordDomainEventContent;
	}
}
