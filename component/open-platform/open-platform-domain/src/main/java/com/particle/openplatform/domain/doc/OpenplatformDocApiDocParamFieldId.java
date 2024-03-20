package com.particle.openplatform.domain.doc;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 开放接口文档参数字段 领域模型id
 * </p>
 *
 * @author yw
 * @since 2024-03-14 14:56:55
 */
public class OpenplatformDocApiDocParamFieldId extends Id {

	public OpenplatformDocApiDocParamFieldId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 开放接口文档参数字段 领域模型id
	 * @param id
	 * @return
	 */
	public static OpenplatformDocApiDocParamFieldId of(Long id){
		return new OpenplatformDocApiDocParamFieldId(id);
	}
}
