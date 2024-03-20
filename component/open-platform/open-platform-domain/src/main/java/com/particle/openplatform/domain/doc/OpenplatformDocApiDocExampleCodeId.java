package com.particle.openplatform.domain.doc;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 开放接口文档示例代码 领域模型id
 * </p>
 *
 * @author yw
 * @since 2024-03-18 17:04:11
 */
public class OpenplatformDocApiDocExampleCodeId extends Id {

	public OpenplatformDocApiDocExampleCodeId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 开放接口文档示例代码 领域模型id
	 * @param id
	 * @return
	 */
	public static OpenplatformDocApiDocExampleCodeId of(Long id){
		return new OpenplatformDocApiDocExampleCodeId(id);
	}
}
