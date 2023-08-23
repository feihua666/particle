package com.particle.openplatform.domain.openapi;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 开放平台开放接口 领域模型id
 * </p>
 *
 * @author yw
 * @since 2023-08-08 11:13:24
 */
public class OpenplatformOpenapiId extends Id {

	public OpenplatformOpenapiId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 开放平台开放接口 领域模型id
	 * @param id
	 * @return
	 */
	public static OpenplatformOpenapiId of(Long id){
		return new OpenplatformOpenapiId(id);
	}
}
