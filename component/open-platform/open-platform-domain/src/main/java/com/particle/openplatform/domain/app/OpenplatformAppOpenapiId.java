package com.particle.openplatform.domain.app;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 开放平台应用与开放接口配置 领域模型id
 * </p>
 *
 * @author yw
 * @since 2023-08-05 11:49:06
 */
public class OpenplatformAppOpenapiId extends Id {

	public OpenplatformAppOpenapiId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 开放平台应用与开放接口配置 领域模型id
	 * @param id
	 * @return
	 */
	public static OpenplatformAppOpenapiId of(Long id){
		return new OpenplatformAppOpenapiId(id);
	}
}
