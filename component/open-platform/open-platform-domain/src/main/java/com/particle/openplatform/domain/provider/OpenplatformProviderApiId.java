package com.particle.openplatform.domain.provider;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 开放平台供应商接口 领域模型id
 * </p>
 *
 * @author yw
 * @since 2024-09-12 10:53:52
 */
public class OpenplatformProviderApiId extends Id {

	public OpenplatformProviderApiId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 开放平台供应商接口 领域模型id
	 * @param id
	 * @return
	 */
	public static OpenplatformProviderApiId of(Long id){
		return new OpenplatformProviderApiId(id);
	}
}
