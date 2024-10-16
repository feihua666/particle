package com.particle.global.openapi.data;

import com.particle.global.dto.basic.DTO;
import lombok.Data;

/**
 * <p>
 * 接口配置信息
 * </p>
 *
 * @author yangwei
 * @since 2023-08-17 15:09
 */
@Data
public class ApiInfo extends DTO {

	/**
	 * 请求的接口地址
	 */
	private String apiUrl;

	/**
	 * 请求的api编码，用于程序判断对应的提供商
	 */
	private String apiCode;

	/**
	 * 权限码
	 */
	private String permission;

	/**
	 * 接口调用规则信息
	 */
	private ApiRuleInfo apiRuleInfo;

	/**
	 * 接口调用限制规则配置信息，应用级
	 */
	private OpenapiLimitRuleInfo clientLimitRuleInfo;
	/**
	 * 接口调用限制规则配置信息，应用和接口级
	 */
	private OpenapiLimitRuleInfo clientAndOpenapiLimitRuleInfo;
	/**
	 * 接口费用规则信息
	 */
	private ApiFeeRuleInfo apiFeeRuleInfo;

	public static ApiInfo create(String apiUrl,
								 String apiCode,
								 String permission,
								 ApiRuleInfo apiRuleInfo,
								 ApiFeeRuleInfo apiFeeRuleInfo,
								 OpenapiLimitRuleInfo clientLimitRuleInfo,
								 OpenapiLimitRuleInfo clientAndOpenapiLimitRuleInfo) {
		ApiInfo apiInfo = new ApiInfo();
		apiInfo.apiUrl = apiUrl;
		apiInfo.apiCode = apiCode;
		apiInfo.permission = permission;
		apiInfo.apiRuleInfo = apiRuleInfo;
		apiInfo.apiFeeRuleInfo = apiFeeRuleInfo;
		apiInfo.clientLimitRuleInfo = clientLimitRuleInfo;
		apiInfo.clientAndOpenapiLimitRuleInfo = clientAndOpenapiLimitRuleInfo;
		return apiInfo;
	}
}
