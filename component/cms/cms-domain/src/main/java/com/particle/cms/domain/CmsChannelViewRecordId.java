package com.particle.cms.domain;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 栏目访问记录 领域模型id
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:15:22
 */
public class CmsChannelViewRecordId extends Id {

	public CmsChannelViewRecordId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 栏目访问记录 领域模型id
	 * @param id
	 * @return
	 */
	public static CmsChannelViewRecordId of(Long id){
		return new CmsChannelViewRecordId(id);
	}
}
