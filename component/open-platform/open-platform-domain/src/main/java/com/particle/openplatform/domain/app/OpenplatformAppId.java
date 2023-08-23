package com.particle.openplatform.domain.app;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 开放平台应用 领域模型id
 * </p>
 *
 * @author yw
 * @since 2023-08-05 11:40:15
 */
public class OpenplatformAppId extends Id {

	public OpenplatformAppId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 开放平台应用 领域模型id
	 * @param id
	 * @return
	 */
	public static OpenplatformAppId of(Long id){
		return new OpenplatformAppId(id);
	}
}
