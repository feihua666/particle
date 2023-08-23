package com.particle.openplatform.domain.provider;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 开放平台开放接口供应商 领域模型id
 * </p>
 *
 * @author yw
 * @since 2023-08-16 16:15:58
 */
public class OpenplatformProviderId extends Id {

	public OpenplatformProviderId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 开放平台开放接口供应商 领域模型id
	 * @param id
	 * @return
	 */
	public static OpenplatformProviderId of(Long id){
		return new OpenplatformProviderId(id);
	}
}
