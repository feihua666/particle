package com.particle.openplatform.domain.openapi;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 开放平台开放接口费用 领域模型id
 * </p>
 *
 * @author yw
 * @since 2023-08-05 11:59:32
 */
public class OpenplatformOpenapiFeeId extends Id {

	public OpenplatformOpenapiFeeId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 开放平台开放接口费用 领域模型id
	 * @param id
	 * @return
	 */
	public static OpenplatformOpenapiFeeId of(Long id){
		return new OpenplatformOpenapiFeeId(id);
	}
}
