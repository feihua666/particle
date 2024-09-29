package com.particle.global.openapi.data;

import com.particle.global.dto.basic.DTO;
import com.particle.global.openapi.enums.FeeRuleDeduplicateType;
import com.particle.global.openapi.enums.FeeRuleFeeType;
import lombok.Data;

/**
 * <p>
 * 接口调用计费规则配置信息
 * 该信息一般包括在调用接口时提供计费依据
 * </p>
 *
 * @author yangwei
 * @since 2024-09-27 11:41:50
 */
@Data
public class ApiFeeRuleInfo extends DTO {

	/**
	 * 单价，分/条
	 */
	private Integer price;
	/**
	 * 计费类型
	 */
	private FeeRuleFeeType feeRuleFeeType;
	/**
	 * 去重方式
	 */
	private FeeRuleDeduplicateType feeRuleDeduplicateType;

	/**
	 * 去重条数，如果去重表示每多少条算一条
	 */
	private Integer deduplicateCount;


	/**
	 * 是否按请求参数去重，1=按参数去重，0=按接口去重
	 */
	private Boolean isDeduplicateByParameter;

	/**
	 * 是否检查是否返回值，1=检查，如果没有返回值不计费，0=不检查，直接计费
	 */
	private Boolean isCheckHasValue;

	/**
	 * 是否检查处理时长，1=检查，0=不检查
	 */
	private Boolean isCheckHandleDuration;

	/**
	 * 处理时长，单位毫秒，如果检查处理时长，超过该时长不计费
	 */
	private Integer handleDuration;
}
