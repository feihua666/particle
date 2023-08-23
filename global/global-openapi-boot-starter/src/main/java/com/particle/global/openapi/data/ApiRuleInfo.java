package com.particle.global.openapi.data;

import com.particle.global.dto.basic.DTO;
import lombok.Data;

/**
 * <p>
 * 接口调用规则配置信息
 * 该信息一般包括在调用接口时提供判断依据
 * </p>
 *
 * @author yangwei
 * @since 2023-08-17 15:38
 */
@Data
public class ApiRuleInfo extends DTO {

	/**
	 * 供应商编码
	 * 如果有值，将按指定的供应商调用
	 */
	private String providerIdentifier;

	public static ApiRuleInfo create(String providerIdentifier) {
		ApiRuleInfo apiRuleInfo = new ApiRuleInfo();
		apiRuleInfo.providerIdentifier = providerIdentifier;

		return apiRuleInfo;
	}
}
