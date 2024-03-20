package com.particle.openplatform.domain.doc;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 开放接口目录名称 领域模型id
 * </p>
 *
 * @author yw
 * @since 2024-03-14 14:53:48
 */
public class OpenplatformDocDirNameId extends Id {

	public OpenplatformDocDirNameId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 开放接口目录名称 领域模型id
	 * @param id
	 * @return
	 */
	public static OpenplatformDocDirNameId of(Long id){
		return new OpenplatformDocDirNameId(id);
	}
}
