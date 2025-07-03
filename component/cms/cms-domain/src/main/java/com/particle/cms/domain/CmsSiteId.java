package com.particle.cms.domain;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 站点 领域模型id
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:14:04
 */
public class CmsSiteId extends Id {

	public CmsSiteId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 站点 领域模型id
	 * @param id
	 * @return
	 */
	public static CmsSiteId of(Long id){
		return new CmsSiteId(id);
	}
}
