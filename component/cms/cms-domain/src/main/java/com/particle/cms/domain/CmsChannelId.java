package com.particle.cms.domain;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 栏目 领域模型id
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:14:55
 */
public class CmsChannelId extends Id {

	public CmsChannelId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 栏目 领域模型id
	 * @param id
	 * @return
	 */
	public static CmsChannelId of(Long id){
		return new CmsChannelId(id);
	}
}
