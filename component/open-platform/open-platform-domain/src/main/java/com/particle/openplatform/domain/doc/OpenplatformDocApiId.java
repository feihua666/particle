package com.particle.openplatform.domain.doc;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 开放接口文档接口 领域模型id
 * </p>
 *
 * @author yw
 * @since 2024-03-14 14:56:01
 */
public class OpenplatformDocApiId extends Id {

	public OpenplatformDocApiId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 开放接口文档接口 领域模型id
	 * @param id
	 * @return
	 */
	public static OpenplatformDocApiId of(Long id){
		return new OpenplatformDocApiId(id);
	}
}
