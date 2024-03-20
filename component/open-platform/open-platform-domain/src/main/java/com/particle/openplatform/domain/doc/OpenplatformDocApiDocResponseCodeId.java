package com.particle.openplatform.domain.doc;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 开放接口文档响应码 领域模型id
 * </p>
 *
 * @author yw
 * @since 2024-03-18 14:44:43
 */
public class OpenplatformDocApiDocResponseCodeId extends Id {

	public OpenplatformDocApiDocResponseCodeId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 开放接口文档响应码 领域模型id
	 * @param id
	 * @return
	 */
	public static OpenplatformDocApiDocResponseCodeId of(Long id){
		return new OpenplatformDocApiDocResponseCodeId(id);
	}
}
