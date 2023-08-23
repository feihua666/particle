package com.particle.openplatform.domain.event;

import com.particle.global.dto.basic.DTO;
import lombok.Data;

/**
 * <p>
 * 调用记录参数
 * </p>
 *
 * @author yangwei
 * @since 2023-08-18 17:24
 */
@Data
public class OpenplatformOpenapiRecordDomainEventContentRecordParam extends DTO {

	/**
	 * 请求参数
	 */
	private String requestParam;

	/**
	 * 响应结果
	 */
	private String responseResult;

	public static OpenplatformOpenapiRecordDomainEventContentRecordParam create(String requestParam,
																				String responseResult) {
		OpenplatformOpenapiRecordDomainEventContentRecordParam openplatformOpenapiRecordDomainEventContentRecordParam = new OpenplatformOpenapiRecordDomainEventContentRecordParam();
		openplatformOpenapiRecordDomainEventContentRecordParam.requestParam = requestParam;
		openplatformOpenapiRecordDomainEventContentRecordParam.responseResult = responseResult;

		return openplatformOpenapiRecordDomainEventContentRecordParam;
	}
}
