package com.particle.openplatform.domain.openapi;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 开放平台开放接口限制规则 领域模型id
 * </p>
 *
 * @author yw
 * @since 2024-10-14 11:03:30
 */
public class OpenplatformOpenapiLimitRuleId extends Id {

	public OpenplatformOpenapiLimitRuleId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 开放平台开放接口限制规则 领域模型id
	 * @param id
	 * @return
	 */
	public static OpenplatformOpenapiLimitRuleId of(Long id){
		return new OpenplatformOpenapiLimitRuleId(id);
	}
}
