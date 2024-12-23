package com.particle.global.openapi.data;

import com.particle.global.dto.basic.DTO;
import com.particle.global.openapi.enums.LimitRuleType;
import lombok.Data;

/**
 * <p>
 * 应用接口额度限制信息
 * 该信息一般包括在调用接口时提供应用额度限制依据
 * </p>
 *
 * @author yangwei
 * @since 2024-10-16 13:06:53
 */
@Data
public class OpenapiAppQuotaLimitInfo extends DTO {
	/**
	 * 应用（客户端）id
	 */
	private String clientId;

	/**
	 * 限制方式，如：按条限制，按金额费用限制，不限制有等
	 */
	private LimitRuleType limitRuleType;

	/**
	 * 限制条数
	 */
	private Integer limitCount;

	/**
	 * 限制金额费用，单位分
	 */
	private Integer limitFee;

	public void updateValue(OpenapiAppQuotaLimitInfo limitInfo) {
		this.limitRuleType = limitInfo == null ? null :limitInfo.getLimitRuleType();
		this.limitCount = limitInfo == null ? null :limitInfo.getLimitCount();
		this.limitFee = limitInfo == null ? null :limitInfo.getLimitFee();
	}
	public static OpenapiAppQuotaLimitInfo createForInit(String clientId) {
		OpenapiAppQuotaLimitInfo openapiAppQuotaLimitInfo = new OpenapiAppQuotaLimitInfo();
		openapiAppQuotaLimitInfo.clientId = clientId;
		return openapiAppQuotaLimitInfo;

	}
	public static OpenapiAppQuotaLimitInfo createForValue(String clientId,LimitRuleType limitRuleType, Integer limitCount,Integer limitFee) {
		OpenapiAppQuotaLimitInfo openapiAppQuotaLimitInfo = new OpenapiAppQuotaLimitInfo();
		openapiAppQuotaLimitInfo.clientId = clientId;
		openapiAppQuotaLimitInfo.limitRuleType = limitRuleType;
		openapiAppQuotaLimitInfo.limitCount = limitCount;
		openapiAppQuotaLimitInfo.limitFee = limitFee;
		return openapiAppQuotaLimitInfo;

	}
	/**
	 * 是否已经超限
	 * @return
	 */
	public Boolean hasExceedLimit() {
		if (limitRuleType == null) {
			return false;
		}
		switch (limitRuleType) {
			case count_limit:
			case count_fee_limit:
				return limitCount <= 0;
			case fee_limit:
				return limitFee <= 0;
			default:
				return false;
		}
	}
}
