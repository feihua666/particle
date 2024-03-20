package com.particle.openplatform.domain.doc;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 开放接口文档模板参数字段 领域模型id
 * </p>
 *
 * @author yw
 * @since 2024-03-18 16:48:56
 */
public class OpenplatformDocApiDocTemplateParamFieldId extends Id {

	public OpenplatformDocApiDocTemplateParamFieldId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 开放接口文档模板参数字段 领域模型id
	 * @param id
	 * @return
	 */
	public static OpenplatformDocApiDocTemplateParamFieldId of(Long id){
		return new OpenplatformDocApiDocTemplateParamFieldId(id);
	}
}
