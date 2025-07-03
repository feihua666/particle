package com.particle.cms.domain;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 内容多媒体 领域模型id
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:14:28
 */
public class CmsContentMultimediaId extends Id {

	public CmsContentMultimediaId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 内容多媒体 领域模型id
	 * @param id
	 * @return
	 */
	public static CmsContentMultimediaId of(Long id){
		return new CmsContentMultimediaId(id);
	}
}
