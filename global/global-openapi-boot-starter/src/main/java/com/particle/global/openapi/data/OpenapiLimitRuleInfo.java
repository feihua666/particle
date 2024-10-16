package com.particle.global.openapi.data;

import com.particle.global.dto.basic.DTO;
import com.particle.global.openapi.enums.*;
import lombok.Data;

/**
 * <p>
 * 接口调用限制规则配置信息
 * 该信息一般包括在调用接口时提供接口调用限制依据
 * </p>
 *
 * @author yangwei
 * @since 2024-10-14 13:20:10
 */
@Data
public class OpenapiLimitRuleInfo extends DTO {
	/**
	 * 限制名称
	 */
	private String name;

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

	/**
	 * 限制周期，按天限制、按周限制等
	 */
	private LimitRulePeriod limitRulePeriod;

	/**
	 * 限制速率，即qps，该项与上面的设置无关
	 */
	private Integer limitRate;

	/**
	 * 限制目标，如：接口，应用等
	 */
	private LimitRuleTarget limitRuleTarget;
}
