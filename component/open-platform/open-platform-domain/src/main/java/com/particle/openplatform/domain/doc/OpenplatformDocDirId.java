package com.particle.openplatform.domain.doc;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 开放接口目录 领域模型id
 * </p>
 *
 * @author yw
 * @since 2024-03-14 14:55:42
 */
public class OpenplatformDocDirId extends Id {

	public OpenplatformDocDirId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 开放接口目录 领域模型id
	 * @param id
	 * @return
	 */
	public static OpenplatformDocDirId of(Long id){
		return new OpenplatformDocDirId(id);
	}
}
