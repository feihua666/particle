package com.particle.cms.domain;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 模板 领域模型id
 * </p>
 *
 * @author yw
 * @since 2026-01-21 21:03:36
 */
public class CmsTemplateId extends Id {

	public CmsTemplateId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 模板 领域模型id
	 * @param id
	 * @return
	 */
	public static CmsTemplateId of(Long id){
		return new CmsTemplateId(id);
	}
}
