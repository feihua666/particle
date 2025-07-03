package com.particle.cms.domain;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 内容访问记录 领域模型id
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:15:38
 */
public class CmsContentViewRecordId extends Id {

	public CmsContentViewRecordId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 内容访问记录 领域模型id
	 * @param id
	 * @return
	 */
	public static CmsContentViewRecordId of(Long id){
		return new CmsContentViewRecordId(id);
	}
}
