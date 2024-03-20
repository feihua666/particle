package com.particle.openplatform.domain.doc;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 开放接口文档接口与目录关系 领域模型id
 * </p>
 *
 * @author yw
 * @since 2024-03-14 14:56:20
 */
public class OpenplatformDocApiDirRelId extends Id {

	public OpenplatformDocApiDirRelId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 开放接口文档接口与目录关系 领域模型id
	 * @param id
	 * @return
	 */
	public static OpenplatformDocApiDirRelId of(Long id){
		return new OpenplatformDocApiDirRelId(id);
	}
}
