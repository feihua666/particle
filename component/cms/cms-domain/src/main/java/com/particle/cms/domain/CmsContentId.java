package com.particle.cms.domain;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 内容 领域模型id
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:14:16
 */
public class CmsContentId extends Id {

	public CmsContentId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 内容 领域模型id
	 * @param id
	 * @return
	 */
	public static CmsContentId of(Long id){
		return new CmsContentId(id);
	}
}
