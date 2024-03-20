package com.particle.openplatform.domain.doc;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 开放接口文档模板响应码 领域模型id
 * </p>
 *
 * @author yw
 * @since 2024-03-18 16:49:10
 */
public class OpenplatformDocApiDocTemplateResponseCodeId extends Id {

	public OpenplatformDocApiDocTemplateResponseCodeId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 开放接口文档模板响应码 领域模型id
	 * @param id
	 * @return
	 */
	public static OpenplatformDocApiDocTemplateResponseCodeId of(Long id){
		return new OpenplatformDocApiDocTemplateResponseCodeId(id);
	}
}
