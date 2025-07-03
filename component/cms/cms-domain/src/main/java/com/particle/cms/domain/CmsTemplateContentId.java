package com.particle.cms.domain;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 模板内容 领域模型id
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:13:35
 */
public class CmsTemplateContentId extends Id {

	public CmsTemplateContentId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 模板内容 领域模型id
	 * @param id
	 * @return
	 */
	public static CmsTemplateContentId of(Long id){
		return new CmsTemplateContentId(id);
	}
}
