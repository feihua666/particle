package com.particle.openplatform.domain.doc;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 开放接口文档 领域模型id
 * </p>
 *
 * @author yw
 * @since 2024-03-14 14:56:37
 */
public class OpenplatformDocApiDocId extends Id {

	public OpenplatformDocApiDocId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 开放接口文档 领域模型id
	 * @param id
	 * @return
	 */
	public static OpenplatformDocApiDocId of(Long id){
		return new OpenplatformDocApiDocId(id);
	}
}
