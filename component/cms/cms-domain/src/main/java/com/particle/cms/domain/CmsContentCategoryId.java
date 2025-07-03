package com.particle.cms.domain;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 内容分类 领域模型id
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:14:40
 */
public class CmsContentCategoryId extends Id {

	public CmsContentCategoryId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 内容分类 领域模型id
	 * @param id
	 * @return
	 */
	public static CmsContentCategoryId of(Long id){
		return new CmsContentCategoryId(id);
	}
}
